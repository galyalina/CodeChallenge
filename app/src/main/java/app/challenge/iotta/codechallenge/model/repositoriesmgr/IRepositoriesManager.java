package app.challenge.iotta.codechallenge.model.repositoriesmgr;

import android.support.annotation.NonNull;

import java.util.List;

import app.challenge.iotta.codechallenge.model.pojo.Repository;

/**
 * Created by Galya on 05/07/2017.
 */

public interface IRepositoriesManager {

    interface IGetRepositoryCallback{

        void onSuccess(List<Repository> tasks);

        void onFailed();

    }

    void getRepositories(@NonNull final IGetRepositoryCallback callback);
    void refreshRepositories(@NonNull final IGetRepositoryCallback callback);

}
