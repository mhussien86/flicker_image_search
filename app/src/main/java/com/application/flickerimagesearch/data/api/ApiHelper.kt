package com.application.flickerimagesearch.data.api

import com.application.flickerimagesearch.data.model.PhotosSearchResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun fetchImages(searchText: String): Response<PhotosSearchResponse>
}