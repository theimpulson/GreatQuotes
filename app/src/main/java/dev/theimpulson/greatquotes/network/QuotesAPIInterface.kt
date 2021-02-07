package dev.theimpulson.greatquotes.network

import retrofit2.Response
import retrofit2.http.GET

interface QuotesAPIInterface {

    @GET("quotes")
    suspend fun queryAll() : Response<ArrayList<Quotes>>
}