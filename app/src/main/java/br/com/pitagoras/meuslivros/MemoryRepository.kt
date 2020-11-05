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
        save(Book(0, "O Conto de Aia", author = "Margaret Atwood", 5.0f))
        save(Book(0, " O Estrangeiro", author = "Albert Camus", 5.0f))
        save(Book(0, " O Mundo se Despedaça", author = "Chinua Achebe", 5.0f))
        save(Book(0, " O Amor nos Tempos do Cólera", author = "Gabriel García Márquez", 5.0f))
        save(Book(0, " Vidas secas", author = "Graciliano Ramos", 5.0f))
        save(Book(0, " Grande Sertão Veredas", author = "Guimarães Rosa", 5.0f))
        save(Book(0, " O Massacre da Candelária", author = "Geraldo Lopes", 5.0f))
        save(
            Book(
                0,
                " Sapiens Uma breve história da humanidade ",
                author = "Yuval Noah Harari",
                5.0f
            )
        )
        save(Book(0, " A máquina do ódio ", author = "Patrícia Campos Mello", 5.0f))
        save(Book(0, "O Grande Gatsby", author = "F. Scott Fitzgerald.", 5.0f))


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


