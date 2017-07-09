package com.iotta.challenge.repositorydetails;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.iotta.challenge.model.pojo.Repository;
import com.iotta.challenge.model.repositoriesmgr.IRepositoriesManager;
import com.iotta.challenge.model.repositoriesmgr.RepositoriesManager;
import com.iotta.challenge.repositorylist.RepositoriesContract;
import com.iotta.challenge.repositorylist.RepositoryFilterType;
import com.iotta.challenge.repositorylist.RepositoryListActivity;
import com.iotta.challenge.utils.Logger;

/**
 * Created by Galya on 05/07/2017.
 */

/**
 * Listens to user actions from the UI ({@link RepositoryListActivity}), retrieves the data and updates the
 * UI as required.
 */
public class DetailsPresenter implements DetailsContract.Presenter {


    //UI component
    private final DetailsContract.View mRepositoriesView;
    //DB
    private IRepositoriesManager mRepositoriesManager;

    private final String mRepositoryId;

    public DetailsPresenter(@NonNull String repositoryId, @NonNull IRepositoriesManager repositoriesManager, @NonNull DetailsContract.View repositoriesView) {
        mRepositoriesView = repositoriesView;
        mRepositoriesView.setPresenter(this);
        mRepositoriesManager = repositoriesManager;
        mRepositoryId = repositoryId;
    }

    @Override
    public void start(){
    }


    /**
     * GET detailed data {@link RepositoriesManager}
     */
    @Override
    public void loadData() {
        GetRepositoryDetailsCB callback = new GetRepositoryDetailsCB();
        mRepositoriesView.setLoadingIndicator(true);
        if(!TextUtils.isEmpty(mRepositoryId)) {
            mRepositoriesManager.getRepositoryDetails(mRepositoryId, callback);
        }else{
            //Do nothing
            Logger.error("Error: something bad happened, repository id is empty");
        }
    }


    public class GetRepositoryDetailsCB implements IRepositoriesManager.IGetRepositoryDetailsCallback{
        @Override
        public void onSuccess(@NonNull Repository repository) {
            mRepositoriesView.setLoadingIndicator(false);
            mRepositoriesView.showDetails(repository);
        }

        @Override
        public void onFailed(String errorMessage) {
            mRepositoriesView.showLoadingError(errorMessage);
        }
    }

}
