package com.dkgtech.quoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dkgtech.quoteapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {

            getQuotes()

        }
    }

    private fun getQuotes() {
        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiInterface::class.java)

        retrofit.getQuotes().enqueue(object : Callback<QuoteModel> {
            override fun onResponse(call: Call<QuoteModel>, response: Response<QuoteModel>) {
                if (response.code() == 200) {
                    response.body().let {
                        for (quote in response.body()?.quotes!!) {
                            binding.rcViewQuotes.layoutManager =
                                LinearLayoutManager(this@MainActivity)
                            binding.rcViewQuotes.adapter =
                                RecyclerQuoteAdapter(this@MainActivity, response.body()!!.quotes)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<QuoteModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

}


