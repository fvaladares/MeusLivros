package br.com.pitagoras.meuslivros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_book_form.*

class BookFormFragment : DialogFragment(), BookFormView {

    private val presenter = BookFormPresenter(this, MemoryRepository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = arguments?.getLong(EXTRA_BOOK_ID, 0) ?: 0
        presenter.loadBook(bookId)
        edtAuthor.setOnEditorActionListener { _, i, _ ->
            handleKeyboardEvent(i)
        }

        dialog?.setTitle(R.string.action_new_book)

        //Força a abertura do teclado virtual ao exibir o dialog
        dialog?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        )
    }


    private fun handleKeyboardEvent(actionId: Int): Boolean {
        if (EditorInfo.IME_ACTION_DONE == actionId) {

            val book = saveBook()

            if (book != null) {
                if (activity is OnBookSavedListener) {
                    val listener = activity as OnBookSavedListener
                    listener.onBookSaved(book)
                }
                dialog?.dismiss()
                return true
            }
        }
        return false
    }

    private fun saveBook(): Book? {
        val book = Book()
        val bookId = arguments?.getLong(EXTRA_BOOK_ID, 0) ?: 0
        book.id = bookId
        book.title = edtTitle.text.toString()
        book.author = edtAuthor.text.toString()
        book.rating = rtbRating.rating

        return if (presenter.saveBook(book)) {
            book
        } else {
            null
        }
    }

    fun open(fm: FragmentManager) {
        show(fm, DIALOG_TAG)
    }

    interface OnBookSavedListener {
        fun onBookSaved(book: Book)
    }

    override fun showBook(book: Book) {
        edtTitle.setText(book.title)
        edtAuthor.setText(book.author)
        rtbRating.rating = book.rating
    }

    override fun errorInvalidBook() {
        Toast.makeText(requireContext(), R.string.error_invalid_book, Toast.LENGTH_SHORT).show()
    }

    override fun errorSaveBook() {
        Toast.makeText(requireContext(), R.string.error_book_not_found, Toast.LENGTH_SHORT).show()
    }


    companion object {
        private const val EXTRA_BOOK_ID = "book_Id"
        private const val DIALOG_TAG = "editDialog"

        fun newInstance(bookId: Long = 0) = BookFormFragment().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_BOOK_ID, bookId)
            }
        }
    }
}