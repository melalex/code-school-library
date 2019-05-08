package com.melalex.library.support.impl

import com.melalex.library.support.Provider
import org.springframework.beans.factory.BeanNameAware


class MapProvider<K, V>(
        private val map: Map<K, V>,
        private val defaultValue: V?
) : Provider<K, V>, BeanNameAware {

    private var beanName = "UNKNOWN"

    override fun setBeanName(beanName: String) {
        this.beanName = beanName
    }

    override fun provide(key: K): V {
        val value = map[key]

        return if (value == null && this.defaultValue != null) {
            LOG.warn("( {} ) has no value for key [ {} ]. Returning [ {} ].", this.beanName, key,
                    this.defaultValue)

            this.defaultValue
        } else value
                ?: throw IllegalArgumentException("[ " + this.beanName + " ] has no value for key [ " + key + " ]")
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(MapProvider::class.java)

        fun <K, V> builder(): MapProviderBuilder<K, V> {
            return MapProviderBuilder()
        }
    }
}

class MapProviderBuilder<K, V> {

    private val mapBuilder = mutableMapOf<K, V>()
    private var defaultValue: V? = null

    fun with(key: K, value: V): MapProviderBuilder<K, V> {
        this.mapBuilder[key] = value
        return this
    }

    fun with(map: Map<K, V>): MapProviderBuilder<K, V> {
        this.mapBuilder.putAll(map)
        return this
    }

    fun defaultValue(defaultValue: V): MapProviderBuilder<K, V> {
        this.defaultValue = defaultValue
        return this
    }

    fun build(): MapProvider<K, V> {
        return MapProvider(mapBuilder.toMap(), defaultValue)
    }
}

