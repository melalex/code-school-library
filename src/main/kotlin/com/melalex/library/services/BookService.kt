package com.melalex.library.services

import com.melalex.library.domain.common.Page
import com.melalex.library.domain.common.Pageable
import com.melalex.library.domain.entity.BookEntity
import com.melalex.library.domain.entity.BookSource

interface BookService {

    fun createBook(bookEntity: BookEntity, bookSource: BookSource): BookEntity

    fun updateBook(bookEntity: BookEntity, bookSource: BookSource): BookEntity

    fun getById(id: Int, bookSource: BookSource): BookEntity

    fun getAll(pageable: Pageable, bookSource: BookSource): Page<BookEntity>

    fun deleteById(id: Int, bookSource: BookSource)

    fun deleteAll(bookSource: BookSource)
}