package dev.theimpulson.greatquotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.theimpulson.greatquotes.network.Quotes
import dev.theimpulson.greatquotes.repositories.QuotesRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    val quotesResponse: MutableLiveData<Response<ArrayList<Quotes>>> = MutableLiveData()

    fun fetchQuotes() {
        val quotesRepo = QuotesRepository()
        viewModelScope.launch {
            quotesResponse.value = quotesRepo.getQuotes()
        }
    }
}