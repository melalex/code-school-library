package com.melalex.library.config

import com.github.javafaker.Faker
import com.melalex.library.domain.entity.BookSource
import com.melalex.library.repositories.BookRepository
import com.melalex.library.repositories.impl.InMemoryBookRepository
import com.melalex.library.repositories.impl.MockBookRepository
import com.melalex.library.support.Provider
import com.melalex.library.support.impl.MapProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LibraryConfig {

    @Bean
    fun bookRepositoryProvider(
            inMemoryBookRepository: InMemoryBookRepository,
            mockBookRepository: MockBookRepository
    ): Provider<BookSource, BookRepository> {
        return MapProvider.builder<BookSource, BookRepository>()
                .with(BookSource.IN_MEMORY, inMemoryBookRepository)
                .with(BookSource.MOCK, mockBookRepository)
                .build()
    }

    @Bean
    fun faker(): Faker = Faker.instance()!!
}
