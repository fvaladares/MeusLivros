package br.com.pitagoras.meuslivros

// Reference about data class https://kotlinlang.org/docs/reference/data-classes.html
data class Book(
    var id: Long = 0L,
    var title: String = "",
    var author: String = "",
    var rating: Float = 0.0f
) {
    override fun toString(): String = title
}