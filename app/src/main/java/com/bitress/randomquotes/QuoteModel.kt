package com.bitress.randomquotes

/**
 * Data class representing a quote with its text, author, and additional information.
 * @property q Quote text.
 * @property a Author of the quote.
 * @property h html the quote.
 */
data class QuoteModel(
    val q: String,
    val a: String,
    val h: String
)
