package com.dkgtech.quoteapp

import retrofit2.http.GET

interface ApiInterface {

    @GET("quotes")
    fun getQuotes(
    ): retrofit2.Call<List<QuoteModel>>
}