/*
 * Created by Inuwa Ibrahim on 17/03/2022, 7:43 PM
 *     https://linktr.ee/Ibrajix
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.ibrajix.nftapp.data

import com.ibrajix.nftapp.network.ApiDataSource
import com.ibrajix.nftapp.network.SafeApiCall
import javax.inject.Inject

class NftRepository @Inject constructor(private val apiDataSource: ApiDataSource) : SafeApiCall {

    suspend fun getTopNft() = safeApiCall { apiDataSource.getTopNft() }
    suspend fun getTrendingNft() = safeApiCall { apiDataSource.getTrendingNft() }

}