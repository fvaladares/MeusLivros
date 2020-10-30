package br.com.pitagoras.meuslivros

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), BookListFragment.OnBookClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("FGV", "MainActivity")
    }

    override fun onBookClick(book: Book) {
        if (isTablet()) {
            showDetailsFragment(book.id)
        } else {
            showDetailsActivity(book.id)
        }

    }

    private fun showDetailsFragment(bookId: Long) {
        val fragment = BookDetailsFragment.newInstance(bookId)

        supportFragmentManager.beginTransaction()
            .replace(R.id.details, fragment, BookDetailsFragment.TAG_DETAILS).commit()
    }

//    private fun isTablet() = findViewById<View>(R.id.details) != null

    private fun isTablet() = resources.getBoolean(R.bool.tablet)
    private fun isSmartphone() = resources.getBoolean(R.bool.smartphone)


    private fun showDetailsActivity(bookId: Long) {
        BookDetailsActivity.open(this, bookId = bookId)
    }
}