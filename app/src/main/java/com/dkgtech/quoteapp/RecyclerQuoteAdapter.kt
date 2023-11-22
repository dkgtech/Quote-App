package com.dkgtech.quoteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dkgtech.quoteapp.databinding.QuoteRowBinding

class RecyclerQuoteAdapter(private val context: Context, private val arrQuotes: List<Quote>) :
    RecyclerView.Adapter<RecyclerQuoteAdapter.ViewHolder>() {
    class ViewHolder(val binding: QuoteRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(QuoteRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrQuotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val data = arrQuotes[position]
            txtQuote.text = data.quote
            "~ ${data.author}".also { txtAuthor.text = it }
        }
    }
}