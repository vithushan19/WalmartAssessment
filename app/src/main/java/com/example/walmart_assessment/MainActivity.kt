package com.example.walmart_assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart_assessment.adapter.CountriesAdapter
import com.example.walmart_assessment.api.CountriesAPI
import com.example.walmart_assessment.api.RetrofitHelper
import com.example.walmart_assessment.viewmodel.CountriesViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountriesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var loadingView: View
    private lateinit var errorView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.countries_recycler_view)
        loadingView = findViewById(R.id.loading_text_view)
        errorView = findViewById(R.id.error_text_view)

        // Create an instance of the ViewModel
        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)
        viewModel.countries.observe(this, Observer { data ->
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = CountriesAdapter(data)
        })

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                recyclerView.visibility = View.GONE
                loadingView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                loadingView.visibility = View.GONE
            }
        }
        viewModel.isError.observe(this) { isError ->
            if (isError) {
                recyclerView.visibility = View.GONE
                errorView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                errorView.visibility = View.GONE
            }
        }

        viewModel.fetchCountries()
    }
}