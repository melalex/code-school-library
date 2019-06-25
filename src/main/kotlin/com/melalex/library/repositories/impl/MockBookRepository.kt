package com.melalex.library.repositories.impl

import com.melalex.library.config.LibraryProperties
import com.melalex.library.domain.common.Page
import com.melalex.library.domain.common.Pageable
import com.melalex.library.domain.entity.BookEntity
import com.melalex.library.domain.factory.BookFactory
import com.melalex.library.repositories.BookRepository
import com.melalex.library.util.lower
import org.springframework.stereotype.Repository

@Repository
class MockBookRepository(
        private val bookFactory: BookFactory,
        private val libraryProperties: LibraryProperties
) : BookRepository {

    override fun save(bookEntity: BookEntity): BookEntity = bookEntity

    override fun findById(id: Int): BookEntity? = bookFactory.createFake(id)


    override fun findAll(pageable: Pageable): Page<BookEntity> {
        val totalCount = libraryProperties.mock.booksCount
        val upperBound = pageable.offset + pageable.size

        val books = IntRange(lower(pageable.offset, totalCount), lower(upperBound, totalCount))
                .asSequence()
                .map { bookFactory.createFake(it) }
                .toList()

        return Page(books, totalCount, pageable.size, pageable.page)
    }

    override fun delete(id: Int) {

    }

    override fun deleteAll() {

    }
}