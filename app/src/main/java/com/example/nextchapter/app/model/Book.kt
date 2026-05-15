package com.nextchapter.app.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val courseName: String,
    val price: Double,
    val condition: String,
    val description: String,
    val sellerName: String,
    val location: String,
    val postedDate: String
)