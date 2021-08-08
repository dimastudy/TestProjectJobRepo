package com.example.testprojectforjob.ui.categories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testprojectforjob.adapters.CategoriesListAdapter
import com.example.testprojectforjob.databinding.ActivityCategoryBinding
import com.example.testprojectforjob.ui.jokes.JokeActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {

    //Tag for logging
    private val TAG = "CategoryActivityTAG"

    private var _binding: ActivityCategoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CategoryViewModel by lazy {
        ViewModelProvider(this).get(CategoryViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CategoriesListAdapter(CategoriesListAdapter.ClickListener { category ->
            viewModel.selectCategory(category)
        })

        //Observing list of categories coming from internet

        viewModel.listCategories.observe(this) { networkResponse ->
            if (networkResponse != null) {
                val list = networkResponse.response
                adapter.list = list
                adapter.notifyDataSetChanged()
                binding.rvListCategories.visibility = View.VISIBLE
                binding.pbCategoriesLoading.visibility = View.GONE
            }
        }

        //Observing a selected category from list

        viewModel.selectedCategory.observe(this) { category ->
            if(category != null){
                val intent = Intent(this, JokeActivity::class.java).apply {
                    putExtra(JokeActivity.CATEGORY_ARGUMENT, category)
                }
                startActivity(intent)
                viewModel.selectingCategoryDone()
            }
        }

        binding.apply {
            rvListCategories.adapter = adapter
            rvListCategories.setHasFixedSize(true)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}