package com.application.flickerimagesearch.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.flickerimagesearch.data.model.PhotoResponse
import com.application.flickerimagesearch.databinding.ActivityMainBinding
import com.application.flickerimagesearch.ui.main.adapter.MainAdapter
import com.application.flickerimagesearch.ui.main.viewmodel.MainViewModel
import com.application.flickerimagesearch.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {

        binding.run {
            searchButton.setOnClickListener {
                if (searchView.text?.isNotBlank() == true) {
                    mainViewModel.fetchImages(searchView.text.toString())
                }
            }
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(arrayListOf())
            recyclerView.addItemDecoration(
                DividerItemDecoration(recyclerView.context, (recyclerView.layoutManager as LinearLayoutManager).orientation)
            )
            recyclerView.adapter = adapter
        }
    }

    private fun setupObserver() {
        mainViewModel.photos.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.run {
                        progressBar.visibility = View.GONE
                        it.data?.let { photos -> renderList(photos) }
                        recyclerView.visibility = View.VISIBLE
                    }
                }
                Status.LOADING -> {
                    binding.run {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.run {
                        progressBar.visibility = View.GONE
                    }
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(flickerPhotos: List<PhotoResponse>) {
        adapter.run {
            addData(flickerPhotos)
            notifyDataSetChanged()
        }
    }

}