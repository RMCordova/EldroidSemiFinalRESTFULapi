package com.cordova.cordovarestfulapi

import retrofit2.Call
import retrofit2.http.*

interface TweetApi {

    @GET("tweet/cordova")
    fun getTweets(@Path("cordova") lastname: String): Call<List<Tweet>>

    @GET("tweet/{cordova}/{tweet_id}")
    fun getTweet(@Path("cordova") lastname: String, @Path("tweet_id") tweetId: String): Call<Tweet>

    @GET("tweets/{tweetId}")
    fun getTweetById(@Path("tweetId") tweetId: String): Call<Tweet>

    @POST("tweet/cordova")
    fun createTweet(@Path("cordova") lastname: String, @Body tweet: Tweet): Call<TweetResponse>

    @PUT("tweet/cordova/{tweet_id}")
    fun updateTweet(
        @Path("cordova") lastname: String,
        @Path("tweet_id") tweetId: String,
        @Body tweet: Tweet
    ): Call<TweetResponse>

    @DELETE("tweet/cordova/{tweet_id}")
    fun deleteTweet(@Path("cordova") lastname: String, @Path("tweet_id") tweetId: String): Call<TweetResponse>
}

data class TweetResponse(val id: String, val message: String)