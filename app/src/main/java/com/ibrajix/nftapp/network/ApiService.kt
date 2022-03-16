package com.ibrajix.nftapp.network

import com.ibrajix.nftapp.data.NftData
import com.ibrajix.nftapp.utilis.EndPoints
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    //get top nft
    @GET(EndPoints.TOP_NFT)
    suspend fun getTopNft() : List<NftData.Top>

    //get trending nft
    @GET(EndPoints.TRENDING_NFT)
    suspend fun getTrendingNft() : List<NftData.Trending>

}