package br.com.pitagoras.meuslivros

import java.util.*


/**
 * This class is responsible by memory persistence
 * It is a singleton
 *
 */
object MemoryRepository : BookRepository {

    private var nextId = 1L
    private val bookList = mutableListOf<Book>()

    // Holds the initialization code (https://kotlinlang.org/docs/reference/classes.html#constructors)
    init {
        // Moke data
        save(Book(0, "O mundo assombrado pelos demônios", author = "Carl Sagan", 5.0f))
        save(Book(0, "Flutter na prática", author = "Frank Zammetti", 4.5f))
        save(Book(0, "Intermediate language practice", author = "Michael Vince", 4.5f))
        save(Book(0, "Algorithms: Theory and Practice", author = "Thomas H. Cormen et. al.", 5.0f))
    }


    override fun save(book: Book) {
        if (book.id == 0L) {
            book.id = nextId++
            bookList.add(book)
        } else {
            val index = bookList.indexOfFirst { it.id == book.id }
            if (index > -1) {
                bookList[index] = book
            } else {
                bookList.add(book)
            }
        }
    }

    override fun remove(vararg books: Book) {
        bookList.removeAll(books)
    }

    override fun bookById(id: Long, callback: (Book?) -> Unit) {
        callback(bookList.find { it.id == id })
    }

    override fun search(term: String, callback: (List<Book>) -> Unit) {
        callback(
            if (term.isEmpty()) bookList
            else bookList.filter {
                it.title.toUpperCase(Locale.getDefault())
                    .contains(term.toUpperCase(Locale.getDefault()))
            }
        )
    }
}


