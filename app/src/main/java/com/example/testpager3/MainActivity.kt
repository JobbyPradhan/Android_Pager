package com.example.testpager3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testpager3.adapter.DogAdapter
import com.example.testpager3.adapter.LoadingStateAdapter
import com.example.testpager3.databinding.ActivityMainBinding
import com.example.testpager3.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()
    @Inject
    lateinit var dogAdapter: DogAdapter
  //  lateinit var loadingStateAdapter: LoadingStateAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        lifecycleScope.launchWhenStarted {
            mainViewModel.getAllDogs().collectLatest { response->
                binding.apply {
                    recyclerview.isVisible = true
                    progressBar.isVisible = false
                }
                dogAdapter.submitData(response)
            }
        }

    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@MainActivity,2)
                adapter = dogAdapter.withLoadStateHeaderAndFooter(
                    header = LoadingStateAdapter{dogAdapter.retry()},
                    footer = LoadingStateAdapter{dogAdapter.retry()}
                )
            }
        }
    }
}