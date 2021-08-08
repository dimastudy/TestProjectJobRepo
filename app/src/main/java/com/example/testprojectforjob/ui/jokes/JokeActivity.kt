package com.example.testprojectforjob.ui.jokes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testprojectforjob.adapters.JokesListAdapter
import com.example.testprojectforjob.databinding.ActivityJokesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class JokeActivity : AppCompatActivity() {


    private var _binding: ActivityJokesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var assistedFactory: JokeViewModel.AssistedFactory

    //Initialization viewModel with argument, came from categories
    private val viewModel: JokeViewModel by lazy {
        val arguments = intent.extras!!
        val category = (arguments?.get(CATEGORY_ARGUMENT)) as String
        val factory = JokeViewModel.provideFactory(assistedFactory, category)
        ViewModelProvider(this, factory).get(JokeViewModel::class.java)
    }


    companion object {
        const val CATEGORY_ARGUMENT = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = JokesListAdapter()

        viewModel.jokesList.observe(this) { listJokes ->
            if (listJokes != null) {
                binding.pbJokesLoading.visibility = View.GONE
                adapter.submitList(listJokes)
            }
        }

        binding.apply {
            rvJokesList.adapter = adapter
            rvJokesList.setHasFixedSize(true)
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        Log.i("JOKEACTIVITY", "ACTIVITY DESRTROYED")
        _binding = null
    }
}