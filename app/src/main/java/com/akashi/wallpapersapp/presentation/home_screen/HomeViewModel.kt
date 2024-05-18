package com.akashi.wallpapersapp.presentation.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashi.wallpapersapp.data.model.Image
import com.akashi.wallpapersapp.data.repository.PexelsRepository
import com.akashi.wallpapersapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val repository: PexelsRepository
) : ViewModel(){

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    var isLoading = mutableStateOf(false)

    init {
        getImages("purple aesthetic wallpaper")
    }

    fun getImages(query: String) {
        isLoading.value = true
        viewModelScope.launch {
            when(val result = repository.getImages(query)) {
                is Resource.Success -> {
                    isLoading.value = false
                    val images = result.data!!.photos
                    _state.value= state.value.copy(
                        images = images,
                        error = ""
                    )
                    println(result)
                }
                is Resource.Error -> {
                    isLoading.value = false
                   _state.value = state.value.copy(
                       error = result.message!!
                   )
                }
                is Resource.Loading -> {
                    isLoading.value = true
                }
            }
        }
    }
}

data class HomeState (
    val images: List<Image> = emptyList(),
    val error: String = ""
)