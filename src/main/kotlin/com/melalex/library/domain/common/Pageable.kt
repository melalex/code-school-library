package com.melalex.library.domain.common

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModelProperty
import springfox.documentation.annotations.ApiIgnore
import javax.validation.constraints.Min

data class Pageable(

        @Min(1)
        val page: Int = 1,

        @Min(1)
        val size: Int = 20
) {

    @get:ApiIgnore
    @get:JsonIgnore
    @get:ApiModelProperty(hidden = true)
    val offset: Int
        get() = (page - 1) * size
}