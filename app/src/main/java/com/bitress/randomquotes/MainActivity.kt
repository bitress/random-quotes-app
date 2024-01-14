package com.bitress.randomquotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bitress.randomquotes.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
class MainActivity : AppCompatActivity() {

    // Late-initialized binding variable using View Binding
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding using the generated binding class
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set the content view to the root view of the binding
        setContentView(binding.root)

        // Fetch and display a quote when the activity is created
        getQuote()

        // Set up a click listener for the "Next" button to fetch and display a new quote
        binding.nextButton.setOnClickListener{
            getQuote()
        }
    }

    /**
     * Function to fetch a random quote using Retrofit and update the UI with the result.
     */
    private fun getQuote() {

        // Show progress UI while fetching a quote
        setInProgress(true)

        // Use Kotlin Coroutine (GlobalScope.launch) for asynchronous network call
        GlobalScope.launch {
            try {
                // Make the network request to get a random quote using Retrofit
                val response = RetrofitInstance.quoteApi.getRandomQuote()

                // Update the UI on the main thread
                runOnUiThread {
                    // Hide progress UI
                    setInProgress(false)

                    // Check if the response is successful and update the UI with the quote
                    response.body()?.first()?.let {
                        setUI(it)
                    }
                }
            } catch (e: Exception) {
                // Handle exceptions and display an error message
                runOnUiThread {
                    // Hide progress UI
                    setInProgress(false)

                    // Show a Toast message indicating that something went wrong
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * Function to update the UI with a given quote.
     */
    private fun setUI(quote : QuoteModel) {
        // Set the quote text and author text in the corresponding TextViews
        binding.quote.text = quote.q
        binding.author.text = quote.a
    }

    /**
     * Function to show or hide the progress bar and "Next" button based on the inProgress parameter.
     */
    private fun setInProgress(inProgress : Boolean) {
        if (inProgress) {
            // Show progress bar and hide the "Next" button
            binding.progressBar.visibility = View.VISIBLE
            binding.nextButton.visibility = View.GONE
        } else {
            // Hide progress bar and show the "Next" button
            binding.progressBar.visibility = View.GONE
            binding.nextButton.visibility = View.VISIBLE
        }
    }
}
