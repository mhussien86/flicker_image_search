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
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding?.searchButton?.setOnClickListener {
            if (binding?.searchView?.text?.isNotBlank() == true) {
                mainViewModel.fetchImages(binding?.searchView?.text.toString())
            }
        }
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding?.recyclerView?.addItemDecoration(
            DividerItemDecoration(
                binding?.recyclerView?.context,
                (binding?.recyclerView?.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding?.recyclerView?.adapter = adapter

    }

    private fun setupObserver() {
        mainViewModel.photos.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.visibility = View.GONE
                    it.data?.let { photos -> renderList(photos) }
                    binding?.recyclerView?.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                    binding?.recyclerView?.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding?.progressBar?.visibility = View.GONE
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}