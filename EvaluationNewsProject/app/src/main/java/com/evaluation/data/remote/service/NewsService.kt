package com.evaluation.data.remote.service

import com.evaluation.data.dto.news.News
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Rajesh
 */

interface NewsService {
    @GET("top-headlines?country=us&apiKey=d3b68e2fe5654c5983ee3a333b31dce1")
    suspend fun fetchNews(): Response<News>
}
