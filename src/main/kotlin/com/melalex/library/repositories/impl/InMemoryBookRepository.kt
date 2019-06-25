package com.melalex.library.repositories.impl

import com.melalex.library.domain.entity.BookEntity
import com.melalex.library.domain.common.Page
import com.melalex.library.domain.common.Pageable
import com.melalex.library.repositories.BookRepository
import com.melalex.library.support.IdProvider
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryBookRepository(
        val idProvider: IdProvider
) : BookRepository {

    private val data: MutableMap<Int, BookEntity> = ConcurrentHashMap();

    override fun save(bookEntity: BookEntity): BookEntity {
        if (bookEntity.id == null) {
            bookEntity.id = idProvider.provide()
        }

        data[bookEntity.id!!] = bookEntity

        return bookEntity
    }

    override fun findById(id: Int): BookEntity? = data[id]

    override fun findAll(pageable: Pageable): Page<BookEntity> {
        val content = data.asSequence()
                .map { it.value }
                .drop(pageable.offset)
                .take(pageable.size)
                .toList()

        return Page(content, data.size, pageable.size, pageable.page)
    }

    override fun delete(id: Int) {
        data.remove(id)
    }

    override fun deleteAll() {
        data.clear()
    }
}