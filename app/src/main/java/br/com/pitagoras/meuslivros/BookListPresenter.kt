package br.com.pitagoras.meuslivros

/***
 * This class receives the reference to BookListView and BookRepository
 * It gave the books list and capture the event witch shows the details of an specific book.
 */
class BookListPresenter(
    private val view: BookListView,
    private val repository: BookRepository
) {
    fun searchBook(term: String) {
        repository.search(term) { books ->
            view.showBooks(books)
        }
    }


    fun showBookDetails(book: Book) {
        view.showBookDetails(book)
    }
}