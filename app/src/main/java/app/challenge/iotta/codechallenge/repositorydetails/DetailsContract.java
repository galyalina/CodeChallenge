package app.challenge.iotta.codechallenge.repositorydetails;

import android.support.annotation.NonNull;

import java.util.List;

import app.challenge.iotta.codechallenge.BasePresenter;
import app.challenge.iotta.codechallenge.BaseView;
import app.challenge.iotta.codechallenge.model.pojo.Repository;
import app.challenge.iotta.codechallenge.repositorylist.RepositoryFilterType;

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