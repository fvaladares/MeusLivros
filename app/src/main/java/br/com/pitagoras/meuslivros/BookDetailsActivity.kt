package br.com.pitagoras.meuslivros

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BookDetailsActivity : AppCompatActivity() {

    private val bookId: Long by lazy { intent.getLongExtra(EXTRA_BOOK_ID, -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        if (savedInstanceState == null) {
            showBookDetailsFragment()
        }

    }

    private fun showBookDetailsFragment() {
        val fragment = BookDetailsFragment.newInstance(bookId)

        supportFragmentManager.beginTransaction()
            .replace(R.id.details, fragment, BookDetailsFragment.TAG_DETAILS).commit()
    }

    companion object {
        private const val EXTRA_BOOK_ID = "book_id"

        fun open(context: Context, bookId: Long) {
            context.startActivity(Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_BOOK_ID, bookId)
            })
        }
    }
}