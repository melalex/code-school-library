package com.melalex.library.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("library")
class LibraryProperties {

    var mock: Mock = Mock()

    class Mock {

         var booksCount: Int = DEFAULT_BOOKS_COUNT
    }
}