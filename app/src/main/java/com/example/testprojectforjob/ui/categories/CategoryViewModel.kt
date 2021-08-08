package com.example.testprojectforjob.ui.categories

import android.util.Log
import androidx.lifecycle.*
import com.example.testprojectforjob.JokesRepository
import com.example.testprojectforjob.network.CategoryNetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: JokesRepository
) : ViewModel() {


    //Live data for selected category (from list)
    private val _selectedCategory = MutableLiveData<String?>()
    val selectedCategory: LiveData<String?>
        get() = _selectedCategory


    //Live data for list categories to observe it changes
    private val _listCategories = MutableLiveData<CategoryNetworkResponse>()
    val listCategories: LiveData<CategoryNetworkResponse>
        get() = _listCategories


    init {
        initCategories()
    }

    //Select category from list and set value to LiveData
    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }


    fun selectingCategoryDone() {
        _selectedCategory.value = null
    }


    //Getting list of categories from internet
    private fun initCategories() {
        viewModelScope.launch {
            val networkResponse = CategoryNetworkResponse(repository.getCategoriesFromNetwork())
            _listCategories.value = networkResponse
        }
    }
}