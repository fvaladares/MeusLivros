package br.com.pitagoras.meuslivros

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity(), BookListFragment.OnBookClickListener,
    SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener,
    BookFormFragment.OnBookSavedListener {

    private var lastSearchTerm: String = ""
    private var searchView: androidx.appcompat.widget.SearchView? = null

    private val listFragment: BookListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentList) as BookListFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("FGV", "MainActivity")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putString(EXTRA_SEARCH_TERM, lastSearchTerm)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lastSearchTerm = savedInstanceState?.getString(EXTRA_SEARCH_TERM) ?: ""
    }

    override fun onBookClick(book: Book) {
        if (isTablet()) {
            showDetailsFragment(book.id)
        } else {
            showDetailsActivity(book.id)
        }
    }

    private fun isTablet() = resources.getBoolean(R.bool.tablet)
    private fun isSmartphone() = resources.getBoolean(R.bool.smartphone)

    // Exibe os detalhes do livro na mesma activity (table)
    private fun showDetailsFragment(bookId: Long) {
        val fragment = BookDetailsFragment.newInstance(bookId)

        searchView?.setOnQueryTextListener(null)

        supportFragmentManager.beginTransaction()
            .replace(R.id.details, fragment, BookDetailsFragment.TAG_DETAILS).commit()
    }

    // Exibe os detalhes do livro em uma nova janela (Smartphone)
    private fun showDetailsActivity(bookId: Long) {
        BookDetailsActivity.open(this, bookId = bookId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.book, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        searchItem?.setOnActionExpandListener(this)
        searchView = searchItem?.actionView as androidx.appcompat.widget.SearchView
        searchView?.queryHint = getString(R.string.hint_search)
        searchView?.setOnQueryTextListener(this)

        if (lastSearchTerm.isNotEmpty()) {
            Handler().post {
                val query = lastSearchTerm
                searchItem.expandActionView()
                searchView?.setQuery(query, true)
                searchView?.clearFocus()
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_info -> AboutDialogFragment().show(supportFragmentManager, "sobre")

            R.id.action_new -> BookFormFragment.newInstance().open(supportFragmentManager)
        }

        return super.onOptionsItemSelected(item)
    }


    // Métodos da action bar
    override fun onQueryTextSubmit(query: String?): Boolean = true

    override fun onQueryTextChange(newText: String?): Boolean {
        lastSearchTerm = newText ?: ""
        listFragment.search(lastSearchTerm)
        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean = true

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        lastSearchTerm = ""
        listFragment.clearSearch() // retorna para o valor normal
        return true
    }


    companion object {
        private const val EXTRA_SEARCH_TERM = "lastSearch"
    }

    override fun onBookSaved(book: Book) {
        listFragment.search(lastSearchTerm)
    }
}