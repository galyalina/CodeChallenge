package app.challenge.iotta.codechallenge.model.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Galya on 05/07/2017.
 */

public class ApiClient {

    public static final String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
//
//
//package app.challenge.iotta.codechallenge.model.api;
//
//        import java.util.ArrayList;
//        import java.util.Date;
//
//        import app.challenge.iotta.codechallenge.model.pojo.Repository;
//        import retrofit2.Call;
//        import retrofit2.Callback;
//        import retrofit2.Response;
//        import retrofit2.Retrofit;
//        import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Created by Galya on 05/07/2017.
// */
//
//public class RESTClient {
//
//    public static final String BASE_URL = "https://api.github.com/users/heremaps/repos/";
//    private static Retrofit retrofit = null;
//    private ApiInterface service;
//    private static RESTClient INSTANCE = null;
//
//    private static Retrofit getClient() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
//
//    private RESTClient() {
//        service = getClient().create(ApiInterface.class);
//    }
//
//    public static RESTClient getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new RESTClient();
//        }
//        return INSTANCE;
//    }
//
//
//    void getRepositories() {
//
//        ApiInterface apiService = getClient().create(ApiInterface.class);
//        Call<Repository> call = apiService.getRepository();
//
//        call.enqueue(new Callback<Repository>() {
//            @Override
//            public void onResponse(Call<Repository> call, Response<Repository> response) {
//                //List<Movie> movies = response.body().getResults();
//                //Log.d(TAG, "Number of movies received: " + movies.size());
//            }
//
//            @Override
//            public void onFailure(Call<Repository> call, Throwable t) {
//            }
//        });
//    }
//}