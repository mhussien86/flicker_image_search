package com.application.flickerimagesearch.data.api

import com.application.flickerimagesearch.data.model.SearchResponseWrapper
import retrofit2.Response

interface ApiHelper {

    suspend fun fetchImages(searchText: String): Response<SearchResponseWrapper>

}