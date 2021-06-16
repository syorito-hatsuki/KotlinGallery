package dev.fstudio.kotlingallery.ui.fragments.nasa.impl

import dev.fstudio.kotlingallery.ui.fragments.nasa.model.ApodItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodAPI {

    //&count=100
    @GET("/planetary/apod")
    suspend fun getApods(
        @Query("count") count: Int
    ): List<ApodItem>
}