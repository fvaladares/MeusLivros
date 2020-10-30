package br.com.pitagoras.meuslivros

/**
 * Coordinate the data persistence
 * this.remove can receive one or more Book objects (vararg)
 * this.bookById and this.search receives a higher-order function
 * read more:
 * https://kotlinlang.org/docs/reference/lambdas.html
 * https://medium.com/tompee/idiomatic-kotlin-higher-order-functions-and-function-types-adb59172796
 *
 *
 */
interface BookRepository {
    fun save(book: Book)
    fun remove(vararg books: Book)
    fun bookById(id: Long, callback: (Book?) -> Unit)
    fun search(term: String, callback: (List<Book>) -> Unit)
}