package com.ibrajix.nftapp.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ibrajix.nftapp.R
import com.ibrajix.nftapp.data.NftData
import com.ibrajix.nftapp.databinding.RcvLytFeaturedBinding
import com.ibrajix.nftapp.databinding.RcvLytTitleBinding
import com.ibrajix.nftapp.databinding.RcvLytTopPicksBinding
import com.ibrajix.nftapp.databinding.RcvLytTrendingBinding
import java.lang.IllegalArgumentException

class NftAdapter : ListAdapter<NftData, NftViewHolder>(NftDiffCallBack()) {

    var itemClickListener: ((view: View, item: NftData, position: Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NftViewHolder {
       return when(viewType){
            R.layout.rcv_lyt_title -> NftViewHolder.TitleViewHolder(
                RcvLytTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false
                )
            )
           R.layout.rcv_lyt_featured -> NftViewHolder.FeaturedViewHolder(
               RcvLytFeaturedBinding.inflate(LayoutInflater.from(parent.context), parent, false
               )
           )
           R.layout.rcv_lyt_top_picks -> NftViewHolder.TopPicksViewHolder(
               RcvLytTopPicksBinding.inflate(LayoutInflater.from(parent.context), parent, false
               )
           )
           R.layout.rcv_lyt_trending -> NftViewHolder.TrendingViewHolder(
               RcvLytTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false
               )
           )
           else -> throw IllegalArgumentException("Invalid view type")
       }
    }

    override fun onBindViewHolder(holder: NftViewHolder, position: Int) {

        holder.itemClickListener = itemClickListener

        val item = getItem(position)
        when(holder){
            is NftViewHolder.FeaturedViewHolder -> holder.bind(item as NftData.Featured)
            is NftViewHolder.TitleViewHolder -> holder.bind(item as NftData.Title)
            is NftViewHolder.TopPicksViewHolder -> holder.bind(item as NftData.Top)
            is NftViewHolder.TrendingViewHolder -> holder.bind(item as NftData.Trending)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is NftData.Title -> R.layout.rcv_lyt_title
            is NftData.Featured -> R.layout.rcv_lyt_featured
            is NftData.Top -> R.layout.rcv_lyt_top_picks
            is NftData.Trending -> R.layout.rcv_lyt_trending
        }
    }

    class NftDiffCallBack : DiffUtil.ItemCallback<NftData>(){

        override fun areItemsTheSame(oldItem: NftData, newItem: NftData): Boolean {
            return when {
                oldItem is NftData.Top && newItem is NftData.Top -> {
                    oldItem.id == newItem.id
                }
                oldItem is NftData.Trending && newItem is NftData.Trending -> {
                    oldItem.id == newItem.id
                }
                else -> {
                    false
                }
            }
        }

        override fun areContentsTheSame(oldItem: NftData, newItem: NftData): Boolean {
            return when {
                oldItem is NftData.Top && newItem is NftData.Top -> {
                    oldItem == newItem
                }
                oldItem is NftData.Trending && newItem is NftData.Trending -> {
                    oldItem == newItem
                }
                else -> {
                    false
                }
            }
        }

    }


}