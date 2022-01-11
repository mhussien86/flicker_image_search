package com.application.flickerimagesearch.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.flickerimagesearch.data.model.PhotoResponse
import com.application.flickerimagesearch.data.repository.PhotosRepository
import com.application.flickerimagesearch.utils.NetworkHelper
import com.application.flickerimagesearch.utils.Resource
import com.application.flickerimagesearch.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: PhotosRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow(Resource<List<PhotoResponse>>(Status.SUCCESS, emptyList(), null))
    val uiState: StateFlow<Resource<List<PhotoResponse>>> = _uiState

    fun fetchImages(searchText: String) {
        viewModelScope.launch {
            _uiState.value = Resource.loading(null)
            if (networkHelper.isNetworkConnected()) {
                mainRepository.fetchImages(searchText).let { responseFlow ->
                    responseFlow.collect { response ->
                        if (response.isSuccessful) {
                            _uiState.value = Resource.success(response.body()?.photos?.photo)
                        } else _uiState.value = Resource.error(response.errorBody().toString(), null)
                    }
                }
            } else _uiState.value = Resource.error("No internet connection", null)
        }
    }
}
