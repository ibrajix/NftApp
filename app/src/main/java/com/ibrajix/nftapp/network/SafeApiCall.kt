/*
 * Created by Inuwa Ibrahim on 17/03/2022, 7:43 PM
 *     https://linktr.ee/Ibrajix
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.ibrajix.nftapp.network

import retrofit2.HttpException

interface SafeApiCall {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ) : Resource<T> {
        return try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when(throwable) {
                is HttpException -> {
                    Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                }
                else -> {
                    Resource.Failure(true, null, null)
                }
            }
        }
    }



}