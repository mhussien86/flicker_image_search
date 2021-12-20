package com.application.flickerimagesearch.data.api

import com.application.flickerimagesearch.data.model.PhotosSearchResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper{
    override suspend fun fetchImages(searchText: String): Response<PhotosSearchResponse> = apiService.fetchImages(searchText)
}