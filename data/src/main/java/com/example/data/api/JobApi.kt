package com.example.data.api

import com.example.domain.ApiAnswer
import retrofit2.http.GET

interface JobApi {
    @GET(DATA_URL)
    suspend fun getData(): ApiAnswer

    companion object {
        private const val DATA_URL = "/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download"
    }
}