package com.cordova.cordovarestfulapi


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cordova.cordovarestfulapi.databinding.ActivityApiMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiMainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityApiMainBinding
    private lateinit var tweetAdapter: TweetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fetchButton.setOnClickListener {
            fetchTweets()
        }

        binding.createButton.setOnClickListener {
            createTweet()
        }

        binding.updateButton.setOnClickListener {
            updateTweet()
        }

        binding.deleteButton.setOnClickListener {
            deleteTweet()
        }

        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        tweetAdapter = TweetAdapter()
        binding.tweetsRecyclerView.apply {
            adapter = tweetAdapter
            layoutManager = LinearLayoutManager(this@ApiMainActivity)
        }
    }

    private fun fetchTweets() {
        val lastName = binding.lastNameEditText.text.toString()
        val call = ApiClient.tweetApi.getTweets(lastName)
        call.enqueue(object : Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                val tweets = response.body()
                // Update the RecyclerView with the fetched tweets
                tweetAdapter.submitList(tweets)
                Toast.makeText(applicationContext, "Fetched ${tweets?.size} tweets", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                // Handle failure
                Toast.makeText(applicationContext, "Failed to fetch tweets", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun createTweet() {
        val lastName = binding.lastNameEditText.text.toString()
        val name = binding.nameEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()

        val tweet = Tweet("", name, description, Timestamp(0, 0))

        val call = ApiClient.tweetApi.createTweet(lastName, tweet)
        call.enqueue(object : Callback<TweetResponse> {
            override fun onResponse(call: Call<TweetResponse>, response: Response<TweetResponse>) {
                // Refresh the tweets list after creating a new tweet
                fetchTweets()
                Toast.makeText(applicationContext, "Tweet created successfully", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                // Handle failure
                Toast.makeText(applicationContext, "Failed to create tweet", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateTweet() {
        val lastName = binding.lastNameEditText.text.toString()
        val tweetId = "taXVegwpotXTrNuQ4Cw8" // Replace with the actual tweet ID you want to update
        val name = binding.nameEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()

        val updatedTweet = Tweet(tweetId, name, description, Timestamp(0, 0))

        val call = ApiClient.tweetApi.updateTweet(lastName, tweetId, updatedTweet)
        call.enqueue(object : Callback<TweetResponse> {
            override fun onResponse(call: Call<TweetResponse>, response: Response<TweetResponse>) {
                // Refresh the tweets list after updating the tweet
                fetchTweets()
                Toast.makeText(applicationContext, "Tweet updated successfully", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                // Handle failure
                Toast.makeText(applicationContext, "Failed to update tweet", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deleteTweet() {
        val lastName = binding.lastNameEditText.text.toString()
        val tweetId = "taXVegwpotXTrNuQ4Cw8" // Replace with the actual tweet ID you want to delete

        val call = ApiClient.tweetApi.deleteTweet(lastName, tweetId)
        call.enqueue(object : Callback<TweetResponse> {
            override fun onResponse(call: Call<TweetResponse>, response: Response<TweetResponse>) {
                // Refresh the tweets list after deleting the tweet
                fetchTweets()
                Toast.makeText(applicationContext, "Tweet deleted successfully", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                // Handle failure
                Toast.makeText(applicationContext, "Failed to delete tweet", Toast.LENGTH_SHORT).show()
            }
        })
    }
}