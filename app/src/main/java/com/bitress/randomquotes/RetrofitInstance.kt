package com.bitress.randomquotes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object for creating and managing a Retrofit instance to interact with the ZenQuotes API.
 */
object RetrofitInstance {

    // Base URL for the ZenQuotes API
    private const val BASE_URL = "https://zenquotes.io/api/"

    /**
     * Function to create and return a Retrofit instance with the specified base URL and Gson converter factory.
     */
    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Exposed instance of the QuoteApi interface for making API calls
    val quoteApi: QuoteApi = getInstance().create(QuoteApi::class.java)
}
