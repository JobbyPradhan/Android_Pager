package com.example.testpager3.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testpager3.data.Dog
import com.example.testpager3.data.network.ApiService
import retrofit2.HttpException
import java.io.IOException


class DogPagingSource constructor(private val apiService: ApiService) : PagingSource<Int,Dog>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dog> {
       val page = params.key?: 1
        return try{
            val response = apiService.getAllDogs(page,params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if(page == 1) null else page-1,
                nextKey = if(response.isEmpty()) null else page+1
            )

        }catch (e:IOException){
            LoadResult.Error(e)
            //Log.d("main", "load: ${e.message}")
        }catch (e:HttpException){
            //Log.d("main", "load: ${e.message}")
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Dog>): Int? {
        return null
    }
}