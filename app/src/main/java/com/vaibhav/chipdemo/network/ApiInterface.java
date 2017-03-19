package com.vaibhav.chipdemo.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vaibhav on 20/12/16.
 */
public interface ApiInterface {

    @GET("11f50j")
    Call<List<String>> getData();


}