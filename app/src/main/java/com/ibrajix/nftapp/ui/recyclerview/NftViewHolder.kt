package com.ibrajix.nftapp.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.ibrajix.nftapp.data.NftData
import com.ibrajix.nftapp.databinding.RcvLytFeaturedBinding
import com.ibrajix.nftapp.databinding.RcvLytTitleBinding
import com.ibrajix.nftapp.databinding.RcvLytTopPicksBinding
import com.ibrajix.nftapp.databinding.RcvLytTrendingBinding
import com.ibrajix.nftapp.utilis.Constants.FEATURED_IMAGE
import com.ibrajix.nftapp.utilis.Constants.FEATURED_IMAGE_TITLE

sealed class NftViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    var itemClickListener: ((view: View, item: NftData, position: Int) -> Unit)? = null

    class TitleViewHolder(private val binding: RcvLytTitleBinding) : NftViewHolder(binding){
        fun bind(title: NftData.Title) {
            binding.txtFeatured.text = title.title
            binding.txtViewAll.text = title.viewAll
            binding.txtViewAll.setOnClickListener {
                itemClickListener?.invoke(it, title, adapterPosition)
            }
        }
    }

    class FeaturedViewHolder(private val binding: RcvLytFeaturedBinding) : NftViewHolder(binding){
        fun bind(featured: NftData.Featured){
            binding.imgFeatured.load(FEATURED_IMAGE){
                crossfade(true)
                transformations(RoundedCornersTransformation(20F))
            }
            binding.imgFeatured.setOnClickListener {
                itemClickListener?.invoke(it, featured, adapterPosition)
            }
            binding.txtFeaturedTitle.text = FEATURED_IMAGE_TITLE
        }
    }

    class TopPicksViewHolder(private val binding: RcvLytTopPicksBinding) : NftViewHolder(binding){
        fun bind(topPicks: NftData.Top){
          binding.imgTopPicks.load(topPicks.image){
             crossfade(true)
             transformations(RoundedCornersTransformation(20F))
          }
          binding.imgTopPicks.setOnClickListener {
              itemClickListener?.invoke(it, topPicks, adapterPosition)
          }
        }
    }

    class TrendingViewHolder(private val binding: RcvLytTrendingBinding) : NftViewHolder(binding){
        fun bind(trending: NftData.Trending){
            binding.imgTrending.load(trending.image){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            binding.topNftContainer.setOnClickListener {
                itemClickListener?.invoke(it, trending, adapterPosition)
            }
            binding.txtNftTitle.text = trending.name
            binding.txtCategory.text = trending.category
        }
    }

}