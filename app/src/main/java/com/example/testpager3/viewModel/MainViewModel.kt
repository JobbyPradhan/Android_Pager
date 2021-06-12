package com.example.testpager3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testpager3.data.Dog
import com.example.testpager3.data.network.ApiService
import com.example.testpager3.data.repository.DogPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val apiService: ApiService): ViewModel() {

    fun getAllDogs() : Flow<PagingData<Dog>> = Pager(
        config = PagingConfig(20, enablePlaceholders = false),
    ){
        DogPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}