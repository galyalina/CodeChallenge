package com.iotta.challenge.repositorydetails;

import android.support.annotation.NonNull;

import java.util.List;

import com.iotta.challenge.BasePresenter;
import com.iotta.challenge.BaseView;
import com.iotta.challenge.model.pojo.Repository;
import com.iotta.challenge.repositorylist.RepositoryFilterType;

/**
 * Created by Galya on 05/07/2017.
 */

public class DetailsContract {

    /**
     * This specifies the contract between the view and the presenter.
     */
    interface View extends BaseView<Presenter> {

        boolean isActive();

        void setLoadingIndicator(boolean active);

        void showDetails(Repository repository);

        void showLoadingError(String errorMessage);

        void showNoOwnerData();

    }

    interface Presenter extends BasePresenter {
        void loadData();
    }

}