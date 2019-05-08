package com.melalex.library.web.dto

import com.melalex.library.web.validation.Update
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class BookDto(

        @NotNull(groups = [Update::class])
        var id: Int?,

        @NotEmpty
        var isbn: String,

        @NotEmpty
        var title: String,

        @NotEmpty
        var author: String,

        @NotNull
        var year: Int
)