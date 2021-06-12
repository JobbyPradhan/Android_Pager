package com.example.testpager3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testpager3.data.Dog
import com.example.testpager3.databinding.EachRowBinding
import javax.inject.Inject

class DogAdapter
@Inject
constructor(): PagingDataAdapter<Dog,DogAdapter.DogViewHolder>(Diff) {

    class DogViewHolder(private val binding: EachRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(dog: Dog){
            binding.apply {
                image.load(dog.url)
            }
        }
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = getItem(position)
        if (dog != null) {
            holder.bind(dog)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    object Diff: DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean = oldItem.url == newItem.url


        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean = oldItem == newItem
    }

}