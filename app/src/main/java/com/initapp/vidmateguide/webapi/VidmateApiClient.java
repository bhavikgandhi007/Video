package com.initapp.vidmateguide.webapi;


import com.initapp.vidmateguide.model.SearchResult;
import com.initapp.vidmateguide.model.VideoResult;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface VidmateApiClient {

    @GET("/search")
    SearchResult getSearch(@Query("part") String part, @Query("maxResults") String maxresult, @Query("order") String order, @Query("q") String query, @Query("key") String key);

    @GET("/search?part={part}&key=AIzaSyBqvC-Gd9Fxb5opmnMZn2bzG9eh_ec2rGA")
    public SearchResult getSearch2(@Path("part") String part);

    @GET("/videos")
    VideoResult getLatestVideo(@Query("part") String part, @Query("maxResults") String maxresult, @Query("chart") String chart, @Query("key") String key);
    //chart="mostPopular"

    @GET("/search")
    SearchResult getSearchChannelID(@Query("part") String part, @Query("maxResults") String maxresult, @Query("order") String order, @Query("channelId") String chanelID, @Query("key") String key);
    //channelId="",order="date"

    @GET("/search")
    SearchResult getCategoryVideos(@Query("part") String part, @Query("maxResults") String maxresult, @Query("order") String order, @Query("videoCategoryId") String categoryID, @Query("type") String type, @Query("key") String key);
    //videoCategoryId="",type="video"

    @GET("/videos")
    VideoResult getVideoDetails(@Query("part") String part, @Query("id") String id, @Query("key") String key);
    //part=snippet,id=""

    @GET("/search")
    SearchResult getEpisodeByChannelID(@Query("part") String part, @Query("maxResults") String maxresult, @Query("order") String order, @Query("q") String query, @Query("channelId") String chanelID, @Query("key") String key);
}