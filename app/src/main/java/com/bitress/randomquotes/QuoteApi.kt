package com.bitress.randomquotes

import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit interface for interacting with the external API to retrieve random quotes.
 */
interface QuoteApi {

    /**
     * Suspended function to make a GET request to the "random" endpoint.
     * @return Response containing a list of [QuoteModel] objects.
     */
    @GET("random")
    suspend fun getRandomQuote(): Response<List<QuoteModel>>
}
