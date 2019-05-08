package com.melalex.library.domain.common

data class Page<out T>(
        val data: List<T>,
        val totalCount: Int,
        val pageSize: Int,
        val currentPage: Int
) {
    inline val pageCount: Int
        get() = (totalCount + pageSize - 1) / pageSize

    inline fun <R> map(mapper: (T) -> R) = Page(
            data.map(mapper),
            totalCount,
            pageSize,
            currentPage
    )
}