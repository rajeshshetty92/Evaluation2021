package com.evaluation.data.remote

import com.evaluation.data.Resource
import com.evaluation.data.dto.news.News

/**
 * Created by Rajesh
 */

internal interface RemoteDataSource {
    suspend fun requestNews(): Resource<News>
}
