package com.example.coroutine.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutine.data.api.ApiHelper
import com.example.coroutine.data.api.RetrofitBuilder
import com.example.coroutine.data.model.User
import com.example.coroutine.ui.base.ViewModelFactory
import com.example.coroutine.ui.main.adapter.MainAdapter
import com.example.coroutine.ui.main.viewmodel.MainViewModel
import com.example.coroutine.utils.DiffUtils
import com.example.coroutine.utils.Status
import com.example.firebase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this) {
            it?.let { resource ->
                binding.apply {
                    when (resource.status) {
                        Status.SUCCESS -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            resource.data?.let { users -> retrieveList(users) }
                        }

                        Status.ERROR -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
                        }

                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[MainViewModel::class.java]
    }

    private fun setupUI() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(arrayListOf())
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            recyclerView.adapter = adapter
        }
    }

    private fun retrieveList(users: List<User>) {
        adapter.apply {
            val diffUtil = DiffUtils(this.users, users)
            val diffResult = DiffUtil.calculateDiff(diffUtil)
            addUsers(users)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}