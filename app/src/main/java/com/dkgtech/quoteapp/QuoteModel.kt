package com.dkgtech.quoteapp

data class QuoteModel(
    val limit: Int,
    val quotes: List<Quote>,
    val skip: Int,
    val total: Int
)