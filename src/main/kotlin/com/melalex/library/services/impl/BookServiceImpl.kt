package com.melalex.library.services.impl

import com.melalex.library.domain.common.Page
import com.melalex.library.domain.common.Pageable
import com.melalex.library.domain.entity.BookEntity
import com.melalex.library.domain.entity.BookSource
import com.melalex.library.domain.exceptions.NotFoundException
import com.melalex.library.repositories.BookRepository
import com.melalex.library.services.BookService
import com.melalex.library.support.IdProvider
import com.melalex.library.support.Provider
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class BookServiceImpl(
        val repositoryProvider: Provider<BookSource, BookRepository>
) : BookService {

    override fun createBook(bookEntity: BookEntity, bookSource: BookSource): BookEntity {
        bookEntity.id = null

        return repositoryProvider.provide(bookSource)
                .save(bookEntity)
    }

    override fun updateBook(bookEntity: BookEntity, bookSource: BookSource): BookEntity {
        bookEntity.id ?: throw IllegalArgumentException("Id in book [ $bookEntity ] should be set")

        return repositoryProvider.provide(bookSource)
                .save(bookEntity)
    }

    override fun getById(id: Int, bookSource: BookSource): BookEntity {
        return repositoryProvider.provide(bookSource)
                .findById(id) ?: throw NotFoundException("Book with id [ $id ] not found in [ $bookSource ] source")
    }

    override fun getAll(pageable: Pageable, bookSource: BookSource): Page<BookEntity> {
        return repositoryProvider.provide(bookSource)
                .findAll(pageable)
    }

    override fun deleteById(id: Int, bookSource: BookSource) {
        repositoryProvider.provide(bookSource)
                .delete(id)
    }

    override fun deleteAll(bookSource: BookSource) {
        repositoryProvider.provide(bookSource)
                .deleteAll()
    }
}