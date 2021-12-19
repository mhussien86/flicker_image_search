package com.application.flickerimagesearch.data.repository

import com.application.flickerimagesearch.data.api.ApiHelper
import javax.inject.Inject

class ImagesRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun fetchImages() = apiHelper.fetchImages(searchText = "")

}