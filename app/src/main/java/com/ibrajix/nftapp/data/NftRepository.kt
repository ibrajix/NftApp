package com.ibrajix.nftapp.data

import com.ibrajix.nftapp.network.ApiDataSource
import com.ibrajix.nftapp.network.SafeApiCall
import javax.inject.Inject

class NftRepository @Inject constructor(private val apiDataSource: ApiDataSource) : SafeApiCall {

    suspend fun getTopNft() = safeApiCall { apiDataSource.getTopNft() }
    suspend fun getTrendingNft() = safeApiCall { apiDataSource.getTrendingNft() }

}