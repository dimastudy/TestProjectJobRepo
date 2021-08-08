package com.example.testprojectforjob.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojectforjob.databinding.ItemCategoryBinding

class CategoriesListAdapter(val onClickListener: ClickListener) :
    RecyclerView.Adapter<CategoriesListAdapter.CategoriesViewHolder>() {

    var list: List<String> = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    override fun getItemCount(): Int =
        list.size

    class CategoriesViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvCategoryName.text = item

        }

    }

    class ClickListener(val clickListener: (category: String) -> Unit){
        fun onClick(category: String) = clickListener(category)
    }
}




