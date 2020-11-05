package br.com.pitagoras.meuslivros

interface BookFormView {
    // It shows of an existent hotel to be updated
    fun showBook(book: Book)

    // Incorrect book data
    fun errorInvalidBook()

    // It'll be called if there are an error when we try to save the book
    fun errorSaveBook()
}