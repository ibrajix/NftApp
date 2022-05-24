/*
 * Created by Inuwa Ibrahim on 17/03/2022, 7:43 PM
 *     https://linktr.ee/Ibrajix
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.ibrajix.nftapp.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.ibrajix.nftapp.R
import com.ibrajix.nftapp.data.NftData
import com.ibrajix.nftapp.databinding.ActivityMainBinding
import com.ibrajix.nftapp.network.Resource
import com.ibrajix.nftapp.ui.recyclerview.NftAdapter
import com.ibrajix.nftapp.ui.viewmodel.NftViewModel
import com.ibrajix.nftapp.utilis.Utility.changeVisibility
import com.ibrajix.nftapp.utilis.Utility.setTransparentStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val nftAdapter = NftAdapter()
    private val nftViewModel: NftViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAdapterAndGetData()
    }

    private fun setUpAdapterAndGetData() {

        //set up recycler view
        binding.rcvNft.apply {

            val gridLayoutManager = GridLayoutManager(this@MainActivity, 2)
            gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (nftAdapter.getItemViewType(position)) {
                        R.layout.rcv_lyt_title -> 2
                        R.layout.rcv_lyt_featured -> 2
                        R.layout.rcv_lyt_top_picks -> 1
                        R.layout.rcv_lyt_trending -> 2
                        else -> 1
                    }
                }
            }

            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = nftAdapter

        }

        //handle clicks
        nftAdapter.itemClickListener = { view, item, position ->

            when(item) {
                is NftData.Title -> Toast.makeText(this, "View all clicked", Toast.LENGTH_SHORT).show()
                is NftData.Featured -> Toast.makeText(this, "Featured nft clicked", Toast.LENGTH_SHORT).show()
                is NftData.Top -> Toast.makeText(this, "Top nft clicked", Toast.LENGTH_SHORT).show()
                is NftData.Trending -> Toast.makeText(this, "Trending nft clicked", Toast.LENGTH_SHORT).show()
            }

        }

        //best way to collect flows in UI layer
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                nftViewModel.nft.collect{ result ->
                    when (result) {
                        Resource.Loading ->  binding.loading.changeVisibility(View.VISIBLE)
                        is Resource.Failure -> {
                            binding.loading.changeVisibility(View.GONE)
                        }
                        is Resource.Success -> {
                            binding.loading.changeVisibility(View.GONE)
                            nftAdapter.submitList(result.value)
                        }
                    }

                }
            }
        }


    }

}
