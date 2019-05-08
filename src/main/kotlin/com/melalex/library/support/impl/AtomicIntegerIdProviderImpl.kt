package com.melalex.library.support.impl

import com.melalex.library.support.IdProvider
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicInteger

@Component
class AtomicIntegerIdProviderImpl : IdProvider {

    private val counter: AtomicInteger = AtomicInteger(0)

    override fun provide(): Int = counter.incrementAndGet()
}