package app.challenge.iotta.codechallenge.repositorylist;

import android.support.annotation.NonNull;

import java.util.List;

import app.challenge.iotta.codechallenge.BasePresenter;
import app.challenge.iotta.codechallenge.BaseView;
import app.challenge.iotta.codechallenge.model.pojo.Repository;

/**
 * Created by Galya on 05/07/2017.
 */

public class RepositoriesContract {

    /**
     * This specifies the contract between the view and the presenter.
     */
    interface View extends BaseView<Presenter> {

        boolean isActive();

        void setLoadingIndicator(boolean active);

        void showRepositories(List<Repository> repositories);

        void showRepositoryDetailsUi(@NonNull String repositoryId);

        void showLoadingTasksError();

        void showNoRepositories();

    }

    interface Presenter extends BasePresenter {

        void loadRepositories(final boolean forceUpdate);

        void openRepositoryDetails(@NonNull String requestedTask);

        void sortRepositories();

        void setFiltering(RepositoryFilterType requestType);
    }

}