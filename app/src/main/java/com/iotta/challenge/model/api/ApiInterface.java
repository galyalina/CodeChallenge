package com.iotta.challenge.model.api;

import java.util.HashMap;
import java.util.List;

import com.iotta.challenge.model.pojo.Owner;
import com.iotta.challenge.model.pojo.Repository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Galya on 07/07/2017.
 */

public interface ApiInterface {

    @GET("users/heremaps/repos")
    Call<List<Repository>> getRepositories();

    @GET
    Call<Owner> getOwner(@Url String url);

    @GET
    Call<HashMap<String, Long>> getListOfLanguages(@Url String url);
}