package com.initapp.vidmateguide.webapi;


import com.initapp.vidmateguide.model.SearchResult;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface VidmateApiClient {

    @GET("/search")
    SearchResult getSearch(@Query("part") String part, @Query("maxResults") String maxresult,  @Query("order") String order, @Query("q") String query, @Query("key") String key);

    @GET("/search?part={part}&key=AIzaSyBqvC-Gd9Fxb5opmnMZn2bzG9eh_ec2rGA")
    public SearchResult getSearch2(@Path("part") String part);

}