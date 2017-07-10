package app.challenge.iotta.codechallenge.repositorydetails;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.challenge.iotta.codechallenge.model.pojo.Repository;
import app.challenge.iotta.codechallenge.model.repositoriesmgr.IRepositoriesManager;
import app.challenge.iotta.codechallenge.model.repositoriesmgr.RepositoriesManager;
import app.challenge.iotta.codechallenge.repositorylist.RepositoriesContract;
import app.challenge.iotta.codechallenge.repositorylist.RepositoryFilterType;
import app.challenge.iotta.codechallenge.repositorylist.RepositoryListActivity;

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

    @Nullable
    private String mRepositoryId;

    public DetailsPresenter(@Nullable String repositoryId, @NonNull IRepositoriesManager repositoriesManager, @NonNull DetailsContract.View repositoriesView) {
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
        mRepositoriesManager.getRepositoryDetails(mRepositoryId, callback);
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
