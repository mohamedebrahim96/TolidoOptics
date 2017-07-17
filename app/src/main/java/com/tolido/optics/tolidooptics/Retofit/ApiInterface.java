package com.tolido.optics.tolidooptics.Retofit;

import com.tolido.optics.tolidooptics.Model.Datum;
import com.tolido.optics.tolidooptics.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Home on 2017-07-16.
 */

public interface ApiInterface {
    /*@GET("movie/top_rated")
    Call<Post> getTopRatedMovies(@Query("api_key") String apiKey);*/



    @GET("tolido.net/feed")
    Call<Post> getposts2(@Query("access_token") String apiKey, @Query("fields") String fields);
}
