package com.nextchapter.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nextchapter.app.model.Book
import com.nextchapter.app.model.User

class BookViewModel : ViewModel() {

    private val _books = MutableLiveData<MutableList<Book>>()
    val books: LiveData<MutableList<Book>> get() = _books

    private val _searchResults = MutableLiveData<List<Book>>()
    val searchResults: LiveData<List<Book>> get() = _searchResults

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> get() = _currentUser

    private val _inquirySent = MutableLiveData<Boolean>()
    val inquirySent: LiveData<Boolean> get() = _inquirySent

    init {
        loadSampleBooks()
    }

    private fun loadSampleBooks() {
        val sampleBooks = mutableListOf(
            Book(1, "Fundamentals of Accounting", "Smith & Jones", "ACC 101", 180.0, "Good", "Great condition, used for one semester only. All chapters intact, some highlights in Ch.3", "Thabo Mokoena", "Johannesburg Campus", "2 days ago"),
            Book(2, "Principles of Economics", "N. Gregory Mankiw", "ECO 201", 150.0, "Like New", "Barely used, no highlights or notes inside.", "Lerato Dlamini", "Soweto Campus", "3 days ago"),
            Book(3, "Introduction to Computer Science", "Sipho Ndlovu", "CSC 101", 200.0, "Fair", "Cover slightly worn but all pages intact.", "Sipho Ndlovu", "Pretoria Campus", "1 week ago"),
            Book(4, "Business Management 101", "Peter Drucker", "BUS 201", 120.0, "Good", "Second edition. Some notes in margins.", "Amara Osei", "Cape Town Campus", "5 days ago"),
            Book(5, "Marketing Principles", "Philip Kotler", "MKT 301", 250.0, "New", "Never used. Still in original packaging.", "Zanele Moyo", "Durban Campus", "1 day ago")
        )
        _books.value = sampleBooks
    }

    fun addBook(book: Book) {
        val currentList = _books.value ?: mutableListOf()
        currentList.add(0, book)
        _books.value = currentList
    }

    fun searchBooks(query: String) {
        val allBooks = _books.value ?: emptyList()
        if (query.isBlank()) {
            _searchResults.value = allBooks
            return
        }
        val lower = query.lowercase()
        _searchResults.value = allBooks.filter {
            it.title.lowercase().contains(lower) ||
                    it.courseName.lowercase().contains(lower) ||
                    it.author.lowercase().contains(lower) ||
                    it.description.lowercase().contains(lower)
        }
    }

    fun registerUser(user: User) {
        _currentUser.value = user
    }

    fun sendInquiry(message: String): Boolean {
        if (message.isBlank()) return false
        _inquirySent.value = true
        return true
    }

    fun resetInquiry() {
        _inquirySent.value = false
    }

    fun getBookById(id: Int): Book? {
        return _books.value?.find { it.id == id }
    }

    fun getNextId(): Int {
        return (_books.value?.maxOfOrNull { it.id } ?: 0) + 1
    }
}