package com.hamza.payback.presentation.ui.features.feeds

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.hamza.payback.R
import com.hamza.payback.BR
import com.hamza.payback.data.feed.domain.Feeds
import javax.inject.Inject

class FeedsAdapter @Inject constructor(): RecyclerView.Adapter<FeedsAdapter.NewsViewHolder>() {

    private val feedsList = ArrayList<Feeds>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feeds, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = feedsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = feedsList[position]

        holder.binding?.setVariable(BR.model, news)
        holder.binding?.executePendingBindings()

        holder.itemView.setOnClickListener {
          //  Navigator(it.context).openNews(news)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFeedsList(list: List<Feeds>) {
        feedsList.clear()
        feedsList.addAll(list)
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var binding: ViewDataBinding?
        init {
            binding = DataBindingUtil.bind(itemView)
        }

    }
}