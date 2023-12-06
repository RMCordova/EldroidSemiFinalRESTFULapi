package com.cordova.cordovarestfulapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class TweetAdapter : ListAdapter<Tweet, TweetAdapter.TweetViewHolder>(TweetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tweet, parent, false)
        return TweetViewHolder(view)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val tweet = getItem(position)
        holder.bind(tweet)
    }

    class TweetViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        private val timestampTextView: TextView = view.findViewById(R.id.timestampTextView)

        fun bind(tweet: Tweet) {
            nameTextView.text = tweet.name
            descriptionTextView.text = tweet.description
            timestampTextView.text = "Timestamp: ${tweet.timestamp._seconds}"
        }
    }

    private class TweetDiffCallback : DiffUtil.ItemCallback<Tweet>() {

        override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
            return oldItem == newItem
        }
    }
}
