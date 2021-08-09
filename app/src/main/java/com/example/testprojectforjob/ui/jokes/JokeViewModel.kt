package com.example.testprojectforjob.ui.jokes

import android.util.Log
import androidx.lifecycle.*
import com.example.testprojectforjob.JokesRepository
import com.example.testprojectforjob.network.JokeNetwork
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


class JokeViewModel @AssistedInject constructor(
    private val repository: JokesRepository,
    @Assisted private val selectedCategory: String
) : ViewModel() {


    //LiveData for joke's list
    private val _jokesList = MutableLiveData<List<JokeNetwork>>()
    val jokesList: LiveData<List<JokeNetwork>>
        get() = _jokesList



    init {
        getJoke()
    }

    //Here i made a cycle for making list with 15 items
    private fun getJoke(){
        viewModelScope.launch {
            val jokes = ArrayList<JokeNetwork>()
            for (i in 0..14){
                val joke = repository.getJokeFromNetwork(selectedCategory)
                jokes.add(joke)
            }
            _jokesList.value = jokes
        }
    }



    companion object {
        //Providing Factory with argument (category)
        fun provideFactory(
            assistedFactory: AssistedFactory,
            category: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(category) as T
            }
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(category: String): JokeViewModel
    }

}
