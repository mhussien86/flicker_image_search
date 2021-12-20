package com.application.flickerimagesearch.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.flickerimagesearch.data.model.PhotoResponse
import com.application.flickerimagesearch.data.repository.PhotosRepository
import com.application.flickerimagesearch.utils.NetworkHelper
import com.application.flickerimagesearch.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: PhotosRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _photos = MutableLiveData<Resource<List<PhotoResponse>>>()
    val photos: LiveData<Resource<List<PhotoResponse>>>
        get() = _photos

    fun fetchImages(searchText: String) {
        viewModelScope.launch {
            _photos.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                val searchResponse = mainRepository.fetchImages(searchText)
                if (searchResponse.isSuccessful) {
                    _photos.postValue(Resource.success(searchResponse.body()?.photos?.photo))
                } else _photos.postValue(Resource.error(searchResponse.errorBody().toString(), null))
            } else _photos.postValue(Resource.error("No internet connection", null))
        }
    }

}


