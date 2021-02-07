package dev.theimpulson.greatquotes.repositories

import dev.theimpulson.greatquotes.network.QuotesAPIBuilder
import dev.theimpulson.greatquotes.network.Quotes
import retrofit2.Response

class QuotesRepository {

    suspend fun getQuotes(): Response<ArrayList<Quotes>> {
        return QuotesAPIBuilder.retrofit.queryAll()
    }
}