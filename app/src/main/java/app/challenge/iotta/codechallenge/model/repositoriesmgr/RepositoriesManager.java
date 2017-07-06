package app.challenge.iotta.codechallenge.model.repositoriesmgr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.challenge.iotta.codechallenge.model.pojo.Owner;
import app.challenge.iotta.codechallenge.model.pojo.Repository;
import app.challenge.iotta.codechallenge.model.api.ApiClient;
import app.challenge.iotta.codechallenge.model.api.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Galya on 05/07/2017.
 */

public class RepositoriesManager implements IRepositoriesManager {

    private static RepositoriesManager INSTANCE;

    private ArrayList<Repository> mRepositoriesList;

    // Prevent direct instantiation.
    private RepositoriesManager(@NonNull Context context) {
        checkNotNull(context);
    }

    public static IRepositoriesManager getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RepositoriesManager(context);
        }
        return INSTANCE;
    }

    public void downloadRepositories(@NonNull final IGetRepositoryCallback repositoryLoadedCallback) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Repository>> callRepositories = apiService.getRepositories();
        callRepositories.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                mRepositoriesList = (ArrayList<Repository>) response.body();
                repositoryLoadedCallback.onSuccess(mRepositoriesList);
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                // Log error here since request failed
                // Log.e(TAG, t.toString());
                repositoryLoadedCallback.onFailed();
            }
        });

        Call<Owner> callOwner = apiService.getOwner();
        callOwner.enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                Owner owner = response.body();
                Log.d("com.iotta.log","Owner "+owner.toString());
                //repositoryLoadedCallback.onSuccess(mRepositoriesList);
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                // Log error here since request failed
                // Log.e(TAG, t.toString());
                repositoryLoadedCallback.onFailed();
            }
        });

    }

    /**
     * return list of repositories if exists otherwise get from network
     * @param callback
     */
    @Override
    public void getRepositories(@NonNull final IGetRepositoryCallback callback){
        if( mRepositoriesList == null || mRepositoriesList.isEmpty()){
            downloadRepositories(callback);
        }else{
            callback.onSuccess(mRepositoriesList);
        }
    }

    /**
     * return list of repositories from network
     * @param callback
     */
    @Override
    public void refreshRepositories(@NonNull final IGetRepositoryCallback callback) {
        downloadRepositories(callback);
    }

}
