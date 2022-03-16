package com.ibrajix.nftapp.network

import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) {

    //get top nft
    suspend fun getTopNft() = apiService.getTopNft()

    //get trending nft
    suspend fun getTrendingNft() = apiService.getTrendingNft()

}