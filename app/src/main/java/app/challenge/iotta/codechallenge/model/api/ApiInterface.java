package app.challenge.iotta.codechallenge.model.api;

import java.util.List;

import app.challenge.iotta.codechallenge.model.pojo.Owner;
import app.challenge.iotta.codechallenge.model.pojo.Repository;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Galya on 05/07/2017.
 */

public interface ApiInterface {

    @GET("users/heremaps/repos")
    Call<List<Repository>> getRepositories(/*@Query("api_key") String apiKey*/);

    @GET("users/heremaps")
    Call<Owner> getOwner(/*@Path("id") int id, @Query("api_key") String apiKey*/);

}