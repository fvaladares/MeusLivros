package br.com.pitagoras.meuslivros

interface BookDetailsView {
    fun showBookDetails(book: Book)
    fun errorBookNotFound()
}