package com.melalex.library.support

interface Provider<K, V> {

    fun provide(key: K): V
}
