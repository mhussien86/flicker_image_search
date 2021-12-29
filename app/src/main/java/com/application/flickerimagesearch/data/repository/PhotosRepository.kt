package com.application.flickerimagesearch.data.repository

import com.application.flickerimagesearch.data.api.ApiHelper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val apiHelper: ApiHelper) {
    fun fetchImages(searchText: String) = flow { emit(apiHelper.fetchImages(searchText)) }
}