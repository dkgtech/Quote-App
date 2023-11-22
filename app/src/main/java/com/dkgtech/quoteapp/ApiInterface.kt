package com.dkgtech.quoteapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("quotes")
    fun getQuotes(
    ): Call<QuoteModel>
}