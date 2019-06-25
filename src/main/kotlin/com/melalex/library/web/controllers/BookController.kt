package com.melalex.library.web.controllers

import com.melalex.library.domain.common.Page
import com.melalex.library.domain.common.Pageable
import com.melalex.library.domain.entity.BookSource
import com.melalex.library.services.BookService
import com.melalex.library.web.dto.BookDto
import com.melalex.library.web.mappers.BookMapper
import com.melalex.library.web.validation.Update
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/public/v1/book")
class BookController(
        private val bookService: BookService,
        private val bookMapper: BookMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create new book")
    fun createBook(
            @Validated @RequestBody bookDto: BookDto,
            @RequestParam(name = "source", required = true, defaultValue = "IN_MEMORY") bookSource: BookSource
    ): BookDto {
        val bookEntity = bookMapper.map(bookDto)

        return bookMapper.map(bookService.createBook(bookEntity, bookSource))
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Update existing book")
    fun updateBook(
            @Validated(Update::class) @RequestBody bookDto: BookDto,
            @RequestParam(name = "source", required = true, defaultValue = "IN_MEMORY") bookSource: BookSource
    ): BookDto {
        val bookEntity = bookMapper.map(bookDto)

        return bookMapper.map(bookService.updateBook(bookEntity, bookSource))
    }

    @GetMapping("/{id}")
    @ApiOperation("Get book by id")
    fun getBookById(
            @PathVariable("id") id: Int,
            @RequestParam(name = "source", required = true, defaultValue = "IN_MEMORY") bookSource: BookSource
    ): BookDto {
        return bookMapper.map(bookService.getById(id, bookSource))
    }

    @GetMapping
    @ApiOperation("Get all books")
    fun getAllBooks(
            @Valid pageable: Pageable,
            @RequestParam(name = "source", required = true, defaultValue = "IN_MEMORY") bookSource: BookSource
    ): Page<BookDto> {
        return bookService.getAll(pageable, bookSource).map { bookMapper.map(it) }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete book by id")
    fun deleteBookById(
            @PathVariable("id") id: Int,
            @RequestParam(name = "source", required = true, defaultValue = "IN_MEMORY") bookSource: BookSource
    ) {
        bookService.deleteById(id, bookSource)
    }

    @DeleteMapping
    @ApiOperation("Delete all books")
    fun deleteAllBooks(
            @RequestParam(name = "source", required = true, defaultValue = "IN_MEMORY") bookSource: BookSource
    ) {
        bookService.deleteAll(bookSource)
    }
}