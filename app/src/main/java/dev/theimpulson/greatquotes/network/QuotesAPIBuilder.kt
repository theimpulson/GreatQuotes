package dev.theimpulson.greatquotes.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuotesAPIBuilder {

    val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("https://type.fit/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuotesAPIInterface::class.java)
    }
}