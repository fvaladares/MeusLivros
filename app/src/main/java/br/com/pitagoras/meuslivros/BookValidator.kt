package br.com.pitagoras.meuslivros

/**
 * This class validate the book data
 */
class BookValidator {
    fun validate(info: Book) = with(info) {
        checkBookTitle(title) && checkAuthorName(author)
    }

    private fun checkAuthorName(address: String) = address.length in 4..40

    private fun checkBookTitle(name: String) = name.length in 2..20
}