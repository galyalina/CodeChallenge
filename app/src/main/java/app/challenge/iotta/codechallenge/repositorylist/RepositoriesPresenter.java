package app.challenge.iotta.codechallenge.repositorylist;

import android.support.annotation.NonNull;

import java.util.List;

import app.challenge.iotta.codechallenge.model.pojo.Repository;
import app.challenge.iotta.codechallenge.model.repositoriesmgr.IRepositoriesManager;
import app.challenge.iotta.codechallenge.model.repositoriesmgr.RepositoriesManager;

/**
 * Created by Galya on 05/07/2017.
 */

/**
 * Listens to user actions from the UI ({@link RepositoryListActivity}), retrieves the data and updates the
 * UI as required.
 */
public class RepositoriesPresenter implements RepositoriesContract.Presenter {

    private RepositoryFilterType mCurrentFiltering = RepositoryFilterType.NONE;

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

    @Override
    public void sortRepositories(){

    }
    @Override
    public void setFiltering(RepositoryFilterType requestType){

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
                mRepositoriesView.showRepositories(repositories);
            }
            else{
                mRepositoriesView.showNoRepositories();
            }
        }

        @Override
        public void onFailed() {
            mRepositoriesView.showLoadingTasksError();
        }
    }

}
