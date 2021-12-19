package com.application.flickerimagesearch.data.api

import com.application.flickerimagesearch.data.model.SearchResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=5a2cc90782760b3a6b3eca570dfaf5c3")
    suspend fun fetchImages(@Query(value = "text") searchText: String): Response<SearchResponseWrapper>

}