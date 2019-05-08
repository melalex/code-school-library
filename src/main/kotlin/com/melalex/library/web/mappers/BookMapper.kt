package com.melalex.library.web.mappers

import com.melalex.library.domain.entity.BookEntity
import com.melalex.library.web.dto.BookDto
import org.springframework.stereotype.Component
import java.time.Year

@Component
class BookMapper {

    fun map(source: BookDto): BookEntity = BookEntity(
            source.id,
            source.isbn,
            source.title,
            source.author,
            Year.of(source.year)
    )


    fun map(source: BookEntity): BookDto = BookDto(
            source.id,
            source.isbn,
            source.title,
            source.author,
            source.year.value
    )
}