package com.melalex.library.domain.entity

import java.time.Year

data class BookEntity(
        var id: Int?,
        var isbn: String,
        var title: String,
        var author: String,
        var year: Year
)