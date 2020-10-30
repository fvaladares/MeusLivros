package br.com.pitagoras.meuslivros

/**
 * Interface for the VIEW of the MVP
 */
interface BookListView {
    fun showBooks(books: List<Book>)
    fun showBookDetails(book: Book)
}