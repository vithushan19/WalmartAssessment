package com.example.walmart_assessment.repository

import com.example.walmart_assessment.api.CountriesAPI
import com.example.walmart_assessment.api.RetrofitHelper
import com.example.walmart_assessment.data.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Sealed class to represent a successful or error result from an API
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

/**
 * Repository class that fetches country data from an API
 */
class CountriesRepository {
    // Function that makes the network request, blocking the current thread
    suspend fun makeCountriesRequest(
    ): Result<List<Country>> {
        return withContext(Dispatchers.IO) {
            val countriesApi = RetrofitHelper.getInstance().create(CountriesAPI::class.java)
            val countriesResponse = countriesApi.getCountries().body()
            if (countriesResponse != null) {
                return@withContext Result.Success(countriesResponse)
            } else {
                return@withContext Result.Error(java.lang.Exception("API Call Failed"))
            }
        }
    }
}
