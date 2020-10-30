package br.com.pitagoras.meuslivros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_book_details.*

class BookDetailsFragment : Fragment(), BookDetailsView {

    private val presenter = BookDetailsPresenter(this, MemoryRepository)
    private var book: Book? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadBookDetails(arguments?.getLong(EXTRA_BOOK_ID, -1) ?: -1)
    }

    override fun showBookDetails(book: Book) {
        this.book = book
        txtTitle.text = book.title
        txtAuthor.text = book.author
        rtbRatting.rating = book.rating
    }

    override fun errorBookNotFound() {
        txtTitle.text = getString(R.string.book_not_found)
        txtAuthor.visibility = View.GONE
        rtbRatting.visibility = View.GONE
    }

    companion object {
        const val TAG_DETAILS = "tagDetalhe"
        private const val EXTRA_BOOK_ID = "bookID"

        // Cria uma nova instância do Livro, não devemos utilizar construtores com parâmetros para criar fragments.
        fun newInstance(id: Long) = BookDetailsFragment().apply {
            //Permite enviar parâmetros
            arguments = Bundle().apply {
                putLong(EXTRA_BOOK_ID, id)
            }
        }

    }
}




