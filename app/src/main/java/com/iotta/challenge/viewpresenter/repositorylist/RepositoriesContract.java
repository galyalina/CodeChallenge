package com.iotta.challenge.viewpresenter.repositorylist;

import android.support.annotation.NonNull;

import java.util.List;

import com.iotta.challenge.BasePresenter;
import com.iotta.challenge.BaseView;
import com.iotta.challenge.model.pojo.Repository;

/**
 * Created by Galya on 05/07/2017.
 */
public class RepositoriesContract {

    /**
     * This specifies the contract between the view and the presenter.
     */
    interface IView extends BaseView<IPresenter> {

        boolean isActive();

        void setLoadingIndicator(boolean active);

        void showRepositories(List<Repository> repositories);

        void showRepositoryDetailsUi(@NonNull String repositoryId);

        void showLoadingError(String errorMessage);

        void showNoRepositories();

    }

    interface IPresenter extends BasePresenter {

        void loadRepositories(final boolean forceUpdate);

        void openRepositoryDetails(@NonNull String requestedTask);

        void setFiltering(RepositoryFilterType requestType);
    }

}