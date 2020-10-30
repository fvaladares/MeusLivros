package br.com.pitagoras.meuslivros

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment

class BookListFragment : ListFragment(), BookListView {

    private val presenter = BookListPresenter(this, MemoryRepository)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("FGV", "OnActivityCreated, before search")
        presenter.searchBook("")
        Log.e("FGV", "OnActivityCreated, after search")
    }

    override fun showBooks(books: List<Book>) {
        Log.e("FGV", "Show book BookListFragment")
        val adapter =
            ArrayAdapter<Book>(requireContext(), android.R.layout.simple_list_item_1, books)
        listAdapter = adapter
    }

    override fun showBookDetails(book: Book) {
        if (activity is OnBookClickListener) {
            val listener = activity as OnBookClickListener
            listener.onBookClick(book)
        }
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val book = l.getItemAtPosition(position) as Book
        presenter.showBookDetails(book)
    }

    interface OnBookClickListener {
        fun onBookClick(book: Book)
    }
}


