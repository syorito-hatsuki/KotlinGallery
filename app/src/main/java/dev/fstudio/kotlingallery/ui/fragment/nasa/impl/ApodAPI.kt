package dev.fstudio.kotlingallery.ui.fragment.nasa.impl

import dev.fstudio.kotlingallery.ui.fragment.nasa.model.ApodItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodAPI {

    @GET("/planetary/apod")
    suspend fun getApods(
        @Query("count") count: Int
    ): List<ApodItem>
}