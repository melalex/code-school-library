package com.melalex.library.domain.factory

import com.github.javafaker.Faker
import com.melalex.library.domain.entity.BookEntity
import com.melalex.library.support.UidProvider
import org.springframework.stereotype.Component
import java.time.Year

@Component
class BookFactory(
        private val faker: Faker,
        private val uidProvider: UidProvider
) {

    fun createFake(id: Int): BookEntity = BookEntity(
            id,
            uidProvider.provide(),
            faker.book().title(),
            faker.artist().name(),
            Year.of(faker.random().nextInt(0, 2019))
    )
}