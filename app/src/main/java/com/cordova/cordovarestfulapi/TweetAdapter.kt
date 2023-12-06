package com.cordova.cordovarestfulapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class TweetAdapter : ListAdapter<Tweet, TweetViewHolder>(TweetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TweetViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val tweet = getItem(position)
        holder.bind(tweet)
    }

    fun addTweet(tweet: Tweet) {
        submitList(currentList.toMutableList().apply { add(tweet) })
    }
}

class TweetDiffCallback : DiffUtil.ItemCallback<Tweet>() {
    override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
        return oldItem == newItem
    }
}
