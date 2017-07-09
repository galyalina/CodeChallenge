package com.iotta.challenge.model.repositoriesmgr;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.iotta.challenge.model.api.IApiGetCB;
import com.iotta.challenge.model.repositoriesmgr.IRepositoriesManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.iotta.challenge.model.pojo.Owner;
import com.iotta.challenge.model.pojo.Repository;
import com.iotta.challenge.model.api.ApiClient;
import com.iotta.challenge.model.api.ApiInterface;
import com.iotta.challenge.utils.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.id;

/**
 * Created by Galya on 05/07/2017.
 */

public class RepositoriesManager implements IRepositoriesManager {

    private static RepositoriesManager INSTANCE;

    HashMap<String, Repository> mRepositoriesHM;

    // Prevent direct instantiation.
    private RepositoriesManager() {
    }

    public static IRepositoriesManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RepositoriesManager();
        }
        return INSTANCE;
    }

    private void downloadRepositories(@NonNull final IGetRepositoryCallback repositoryLoadedCallback) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Repository>> callRepositories = apiService.getRepositories();
        callRepositories.enqueue(new GetRepositoriesCB(repositoryLoadedCallback));
    }

    @Override
    public void getRepositoryDetails(final @NonNull String id, @NonNull final IGetRepositoryDetailsCallback callback) {

        Repository selectedRepository = mRepositoriesHM.get(id);

        //Get basic info if exists owner and list of languages
        callback.onSuccess(selectedRepository);

        //TODO update logic should be allayed, currently don't update if data exists
        if (mRepositoriesHM.get(id).getOwner().isUpdated() && !mRepositoriesHM.get(id).getLanguages().isEmpty()) {
            return;
        }

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Owner> callOwner = apiService.getOwner(selectedRepository.getOwner().getmBlogUrl());
        callOwner.enqueue(new GetOwnerDetailsCB(callback, selectedRepository));

        Call<HashMap<String, Long>> callLanguages = apiService.getListOfLanguages(selectedRepository.getLanguagesListUrl());

        callLanguages.enqueue(new GetLanguagesCB(callback, selectedRepository));
    }

    /**
     * return list of repositories if exists otherwise get from network
     *
     * @param callback
     */
    @Override
    public void getRepositories(@NonNull final IGetRepositoryCallback callback) {
        if (mRepositoriesHM == null || mRepositoriesHM.isEmpty()) {
            downloadRepositories(callback);
        } else {
            callback.onSuccess(new ArrayList<>(mRepositoriesHM.values()));
        }
    }

    /**
     * return list of repositories from network
     *
     * @param callback
     */
    @Override
    public void refreshRepositories(@NonNull final IGetRepositoryCallback callback) {
        downloadRepositories(callback);
    }


    class GetOwnerDetailsCB extends IApiGetCB<Owner> {

        IGetRepositoryDetailsCallback m_cb;
        Repository m_selectedRepository;

        public GetOwnerDetailsCB(IGetRepositoryDetailsCallback cb, Repository selectedRepository) {
            m_cb = cb;
            m_selectedRepository = selectedRepository;
        }

        @Override
        public void handleResponse(@NonNull Owner responseObject) {
            m_selectedRepository.setOwner(responseObject);
            m_cb.onSuccess(m_selectedRepository);
        }

        @Override
        public void handleError(@NonNull String message) {
            m_cb.onFailed(message);
        }
    }

    class GetLanguagesCB extends IApiGetCB<HashMap<String, Long>> {

        IRepositoriesManager.IGetRepositoryDetailsCallback m_cb;
        Repository m_selectedRepository;

        public GetLanguagesCB(IRepositoriesManager.IGetRepositoryDetailsCallback cb, Repository selectedRepository) {
            m_cb = cb;
            m_selectedRepository = selectedRepository;
        }

        @Override
        public void handleResponse(@NonNull HashMap<String, Long> responseObject) {
            m_selectedRepository.setLanguages(responseObject);
            m_cb.onSuccess(m_selectedRepository);
        }

        @Override
        public void handleError(@NonNull String message) {
            m_cb.onFailed(message);
        }
    }

    class GetRepositoriesCB extends IApiGetCB<List<Repository>> {

        IRepositoriesManager.IGetRepositoryCallback m_cb;

        GetRepositoriesCB(IRepositoriesManager.IGetRepositoryCallback repositoryLoadedCallback) {
            m_cb = repositoryLoadedCallback;
        }

        @Override
        public void handleResponse(@NonNull List<Repository> responseObject) {

            if (mRepositoriesHM == null) {
                mRepositoriesHM = new HashMap<>();
            }

            if (!responseObject.isEmpty()) {
                for (Repository item : responseObject) {
                    mRepositoriesHM.put(item.getId(), item);
                }
            }

            m_cb.onSuccess(responseObject);
        }

        @Override
        public void handleError(@NonNull String message) {
            m_cb.onFailed(message);
        }
    }
}

