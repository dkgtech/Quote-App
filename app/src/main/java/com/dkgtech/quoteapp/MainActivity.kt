package com.dkgtech.quoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.dkgtech.quoteapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var quotes: MutableList<Quote> = ArrayList()
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
        val response = retrofit.getQuotes().enqueue(object : Callback<List<QuoteModel>> {
            override fun onResponse(
                call: Call<List<QuoteModel>>,
                response: Response<List<QuoteModel>>
            ) {
                val responseBody = response.body().let {
                    if (it != null) {
                        quotes.add(it)
                        Log.d("main", it.toString())
                    }
                }
                if (response.isSuccessful) {
                    binding.rcViewQuotes.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rcViewQuotes.adapter =
                        RecyclerQuoteAdapter(this@MainActivity, arrQuotes = quotes)
                    Log.d("main", quotes.toString())
                }

            }

            override fun onFailure(call: Call<List<QuoteModel>>, t: Throwable) {
                Log.d("main", t.message.toString())
            }

        })

    }
}


