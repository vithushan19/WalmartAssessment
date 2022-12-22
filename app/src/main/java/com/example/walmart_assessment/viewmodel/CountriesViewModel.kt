package com.example.walmart_assessment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart_assessment.data.Country
import com.example.walmart_assessment.repository.CountriesRepository
import com.example.walmart_assessment.repository.Result
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel() {

    // Holds the list of countries to display
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    // Determines if we show an error state or not
    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    // Determines if we show a loading state or not
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val repo = CountriesRepository()

    fun fetchCountries() {
       viewModelScope.launch {
           _isLoading.value = true

           val result = repo.makeCountriesRequest()
           if (result is Result.Success) {
               _countries.value = result.data
           } else if (result is Result.Error) {
                _isError.value = true
           }
           _isLoading.value = false
       }
    }
}
