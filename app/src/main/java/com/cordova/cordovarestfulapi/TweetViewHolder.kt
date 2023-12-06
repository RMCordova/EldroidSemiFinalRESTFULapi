package com.cordova.cordovarestfulapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cordova.cordovarestfulapi.databinding.ItemTweetBinding

class TweetViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_tweet, parent, false)) {

    private val binding: ItemTweetBinding = ItemTweetBinding.bind(itemView)

    fun bind(tweet: Tweet) {
        binding.nameTextView.text = tweet.name
        binding.descriptionTextView.text = tweet.description

    }
}
