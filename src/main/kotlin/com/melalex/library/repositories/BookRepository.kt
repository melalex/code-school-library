package com.melalex.library.repositories

import com.melalex.library.domain.entity.BookEntity
import com.melalex.library.domain.common.Page
import com.melalex.library.domain.common.Pageable

interface BookRepository {

    fun save(bookEntity: BookEntity): BookEntity

    fun findById(id: Int): BookEntity?

    fun findAll(pageable: Pageable): Page<BookEntity>

    fun delete(id: Int)

    fun deleteAll()
}