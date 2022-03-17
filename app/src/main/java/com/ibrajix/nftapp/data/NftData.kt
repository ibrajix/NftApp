/*
 * Created by Inuwa Ibrahim on 17/03/2022, 7:43 PM
 *     https://linktr.ee/Ibrajix
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.ibrajix.nftapp.data


sealed class NftData {

    class Title(
        val id: Int,
        val title: String,
        val viewAll: String,
    ) : NftData()

    class Featured(
        val image: String,
        val title: String
    ) : NftData()

    class Top(
        val id: Int,
        val image: String
    ) : NftData()

    class Trending(
        val id: Int,
        val image: String,
        val name: String,
        val category: String
    ) : NftData()

}