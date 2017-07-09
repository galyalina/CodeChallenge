package com.iotta.challenge.viewpresenter.repositorylist;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.iotta.challenge.model.pojo.Repository;
import com.iotta.challenge.model.repositoriesmgr.IRepositoriesManager;
import com.iotta.challenge.model.repositoriesmgr.RepositoriesManager;
import com.iotta.challenge.utils.Logger;

/**
 * Created by Galya on 05/07/2017.
 */

/**
 * Listens to user actions from the UI ({@link RepositoryListActivity}), retrieves the data and updates the
 * UI as required.
 */
public class RepositoriesPresenter implements RepositoriesContract.IPresenter {

    private RepositoryFilterType mCurrentFiltering = RepositoryFilterType.UPDATE_DATE_DSC;

    //UI component
    private final RepositoriesContract.IView mRepositoriesIView;
    //DB
    private IRepositoriesManager mRepositoriesManager;


    public RepositoriesPresenter(@NonNull IRepositoriesManager repositoriesManager, @NonNull RepositoriesContract.IView repositoriesIView) {
        mRepositoriesIView = repositoriesIView;
        mRepositoriesIView.setPresenter(this);
        mRepositoriesManager = repositoriesManager;
    }

    @Override
    public void start() {
        loadRepositories(false);
    }

    /**
     * @param forceUpdate   Pass in true to refresh the data in the {@link RepositoriesManager}
     */
    @Override
    public void loadRepositories(final boolean forceUpdate) {

        GetRepositoryCB callback = new GetRepositoryCB();
        mRepositoriesIView.setLoadingIndicator(true);

        if (forceUpdate) {
            mRepositoriesManager.refreshRepositories(callback);
        }else{
            mRepositoriesManager.getRepositories(callback);
        }
    }


    private void sortRepositories(List<Repository> repositories){

        if(mCurrentFiltering == RepositoryFilterType.UPDATE_DATE_DSC){
            Collections.sort(repositories, new Comparator<Repository>() {
                @Override
                public int compare(Repository o1, Repository o2) {
                    return o2.compareTo(o1);
                }
            });

        }else{
            Collections.sort(repositories, new Comparator<Repository>() {
                @Override
                public int compare(Repository o1, Repository o2) {
                    return o1.compareTo(o2);
                }
            });
        }
    }

    @Override
    public void setFiltering(RepositoryFilterType requestType){
        if(mCurrentFiltering == requestType){
            return;
        }else{
            mCurrentFiltering = requestType;
        }

        loadRepositories(false);
    }

    @Override
    public void openRepositoryDetails(@NonNull String repositoryId) {
        mRepositoriesIView.showRepositoryDetailsUi(repositoryId);
    }


    public class GetRepositoryCB implements IRepositoriesManager.IGetRepositoryCallback{
        @Override
        public void onSuccess(@NonNull List<Repository> repositories) {

            Logger.warn(repositories.size()+" were downloaded from network");

            mRepositoriesIView.setLoadingIndicator(false);
            if(!repositories.isEmpty()) {
                sortRepositories(repositories);
                mRepositoriesIView.showRepositories(repositories);
            }
            else{
                mRepositoriesIView.showNoRepositories();
            }
        }

        @Override
        public void onFailed(String errorMessage) {
            mRepositoriesIView.showLoadingError(errorMessage);
        }
    }

}
