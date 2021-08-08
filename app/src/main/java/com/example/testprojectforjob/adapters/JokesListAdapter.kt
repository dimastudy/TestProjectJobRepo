package com.example.testprojectforjob.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojectforjob.databinding.ItemJokeBinding
import com.example.testprojectforjob.network.JokeNetwork

class JokesListAdapter :
    ListAdapter<JokeNetwork, JokesListAdapter.JokesViewHolder>(DiffCallback) {

    class JokesViewHolder(private val binding: ItemJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: JokeNetwork) {
            binding.tvJokeValue.text = joke.value
        }
    }


    object DiffCallback : DiffUtil.ItemCallback<JokeNetwork>() {
        override fun areItemsTheSame(oldItem: JokeNetwork, newItem: JokeNetwork): Boolean =
            oldItem.value == newItem.value

        override fun areContentsTheSame(oldItem: JokeNetwork, newItem: JokeNetwork): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val binding = ItemJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)
    }
}