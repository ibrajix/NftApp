/*
 * Created by Inuwa Ibrahim on 17/03/2022, 7:43 PM
 *     https://linktr.ee/Ibrajix
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.ibrajix.nftapp.network

import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) {

    //get top nft
    suspend fun getTopNft() = apiService.getTopNft()

    //get trending nft
    suspend fun getTrendingNft() = apiService.getTrendingNft()

}