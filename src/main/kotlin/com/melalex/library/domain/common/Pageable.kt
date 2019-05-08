package com.melalex.library.domain.common

import javax.validation.constraints.Min

data class Pageable(

        @Min(1)
        val page: Int,

        @Min(1)
        val size: Int
) {

    val offset: Int
        get() = (page - 1) * size
}