package com.evaluation.data.remote

import com.evaluation.data.Resource
import com.evaluation.data.dto.news.News
import com.evaluation.data.error.NETWORK_ERROR
import com.evaluation.data.error.NO_INTERNET_CONNECTION
import com.evaluation.data.remote.service.NewsService
import com.evaluation.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by Rajesh
 */

class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) : RemoteDataSource {
    override suspend fun requestNews(): Resource<News> {
        val recipesService = serviceGenerator.createService(NewsService::class.java)
        return when (val response = processCall(recipesService::fetchNews)) {
            is News -> {
                Resource.Success(data = response as News)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
