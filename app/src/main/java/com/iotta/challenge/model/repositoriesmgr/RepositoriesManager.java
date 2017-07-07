package com.iotta.challenge.model.repositoriesmgr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.iotta.challenge.model.repositoriesmgr.IRepositoriesManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.iotta.challenge.model.pojo.Owner;
import com.iotta.challenge.model.pojo.Repository;
import com.iotta.challenge.model.api.ApiClient;
import com.iotta.challenge.model.api.ApiInterface;
import com.iotta.challenge.utils.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Galya on 05/07/2017.
 */

public class RepositoriesManager implements IRepositoriesManager {

    private static RepositoriesManager INSTANCE;

    private HashMap<String, Repository> mRepositoriesHM;

    // Prevent direct instantiation.
    private RepositoriesManager(@NonNull Context context) {
        checkNotNull(context);
    }

    public static IRepositoriesManager getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RepositoriesManager(context);
        }
        return INSTANCE;
    }

    public void downloadRepositories(@NonNull final IGetRepositoryCallback repositoryLoadedCallback) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Repository>> callRepositories = apiService.getRepositories();
        callRepositories.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                // parse the response body
                if(response.isSuccessful()) {
                    List<Repository> list = response.body();

                    if (mRepositoriesHM == null) {
                        mRepositoriesHM = new HashMap<>();
                    }

                    if (list != null && !list.isEmpty()) {
                        for (Repository item : list) {
                            mRepositoriesHM.put(item.getId(), item);
                        }
                    }

                    repositoryLoadedCallback.onSuccess(list);
                }else{
                    Logger.debug(response.message());
                    JSONObject jObjError;
                    try {
                        if(!TextUtils.isEmpty(response.errorBody().string())){
                            jObjError = new JSONObject(response.errorBody().string());
                            String message = jObjError.getString("message");
                            repositoryLoadedCallback.onFailed(message);
                        }else{
                            repositoryLoadedCallback.onFailed(response.message());
                        }
                    } catch (JSONException e) {
                        Logger.error(e.getMessage(), e);
                        repositoryLoadedCallback.onFailed(response.message());
                    } catch (IOException e) {
                        Logger.error(e.getMessage(), e);
                        repositoryLoadedCallback.onFailed(response.message());
                    }



                }
            }

            @Override
            public void onFailure(@Nullable Call<List<Repository>> call, Throwable t) {
                repositoryLoadedCallback.onFailed(t.getMessage());
            }
        });
    }


    @Override
    public void getRepositoryDetails(final @NonNull String id, @NonNull final IGetRepositoryDetailsCallback callback){

        //Get basic info if exists owner and list of languages
        callback.onSuccess(mRepositoriesHM.get(id));

        //Don't update, more logic should be added
        if(mRepositoriesHM.get(id).getOwner().isUpdated() && !mRepositoriesHM.get(id).getLanguages().isEmpty()){
             return;
        }

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Owner> callOwner = apiService.getOwner(mRepositoriesHM.get(id).getOwner().getmBlogUrl());
        callOwner.enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                // parse the response body …
                if(response.isSuccessful()) {
                    Repository repository = mRepositoriesHM.get(id);
                    Owner owner = response.body();
                    repository.setOwner(owner);
                    callback.onSuccess(repository);
                }else{
                    callback.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });


        Call<HashMap<String, Long>> callLanguages = apiService.getListOfLanguages(mRepositoriesHM.get(id).getLanguagesListUrl());
        callLanguages.enqueue(new Callback<HashMap<String, Long>>() {
            @Override
            public void onResponse(Call<HashMap<String, Long>> call, Response<HashMap<String, Long>> response) {
                if(response.isSuccessful()) {
                    Repository repository = mRepositoriesHM.get(id);
                    repository.setLanguages(response.body());
                    callback.onSuccess(repository);
                }else{
                    callback.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, Long>> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }



    /**
     * return list of repositories if exists otherwise get from network
     * @param callback
     */
    @Override
    public void getRepositories(@NonNull final IGetRepositoryCallback callback){
        if( mRepositoriesHM == null || mRepositoriesHM.isEmpty()){
            downloadRepositories(callback);
        }else{
            callback.onSuccess(new ArrayList<>(mRepositoriesHM.values()));
        }
    }

    /**
     * return list of repositories from network
     * @param callback
     */
    @Override
    public void refreshRepositories(@NonNull final IGetRepositoryCallback callback) {
        downloadRepositories(callback);
    }
}
