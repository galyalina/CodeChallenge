package com.iotta.challenge.repositorylist;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.iotta.challenge.model.pojo.Repository;
import com.iotta.challenge.model.repositoriesmgr.IRepositoriesManager;
import com.iotta.challenge.model.repositoriesmgr.RepositoriesManager;

/**
 * Created by Galya on 05/07/2017.
 */

/**
 * Listens to user actions from the UI ({@link RepositoryListActivity}), retrieves the data and updates the
 * UI as required.
 */
public class RepositoriesPresenter implements RepositoriesContract.Presenter {

    private RepositoryFilterType mCurrentFiltering = RepositoryFilterType.UPDATE_DATE_DSC;

    //UI component
    private final RepositoriesContract.View mRepositoriesView;
    //DB
    private IRepositoriesManager mRepositoriesManager;


    public RepositoriesPresenter(@NonNull IRepositoriesManager repositoriesManager, @NonNull RepositoriesContract.View repositoriesView) {
        mRepositoriesView = repositoriesView;
        mRepositoriesView.setPresenter(this);
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
        mRepositoriesView.setLoadingIndicator(true);

        if (forceUpdate) {
            mRepositoriesManager.refreshRepositories(callback);
        }else{
            mRepositoriesManager.getRepositories(callback);
        }
    }


    public void sortRepositories(List<Repository> repositories){

        if(mCurrentFiltering == RepositoryFilterType.UPDATE_DATE_DSC){
            Collections.sort(repositories, new Comparator<Repository>() {
                @Override
                public int compare(Repository o1, Repository o2) {
                    return o1.getLastUpdate().compareTo(o2.getLastUpdate());
                }
            });

        }else{
            Collections.sort(repositories, new Comparator<Repository>() {
                @Override
                public int compare(Repository o1, Repository o2) {
                    return o2.getLastUpdate().compareTo(o1.getLastUpdate());
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
        mRepositoriesView.showRepositoryDetailsUi(repositoryId);
    }


    public class GetRepositoryCB implements IRepositoriesManager.IGetRepositoryCallback{
        @Override
        public void onSuccess(@NonNull List<Repository> repositories) {
            mRepositoriesView.setLoadingIndicator(false);
            if(repositories!=null && !repositories.isEmpty()) {

                sortRepositories(repositories);

                mRepositoriesView.showRepositories(repositories);
            }
            else{
                mRepositoriesView.showNoRepositories();
            }
        }

        @Override
        public void onFailed(String errorMessage) {
            mRepositoriesView.showLoadingError(errorMessage);
        }
    }

}
