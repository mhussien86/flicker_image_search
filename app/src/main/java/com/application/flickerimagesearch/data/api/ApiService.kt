package com.application.flickerimagesearch.data.api

import com.application.flickerimagesearch.BuildConfig.API_KEY
import com.application.flickerimagesearch.data.model.PhotosSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&$API_KEY")
    suspend fun fetchImages(@Query(value = "text") searchText: String): Response<PhotosSearchResponse>
}