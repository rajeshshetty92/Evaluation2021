package com.evaluation.data

import com.evaluation.data.dto.login.LoginRequest
import com.evaluation.data.dto.login.LoginResponse
import com.evaluation.data.dto.news.News
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rajesh
 */

interface DataRepositorySource {
    suspend fun requestNews(): Flow<Resource<News>>
    suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>
    suspend fun addToFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun removeFromFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun isFavourite(id: String): Flow<Resource<Boolean>>
}
