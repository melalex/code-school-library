package com.melalex.library.support.impl

import com.melalex.library.support.UidProvider
import org.springframework.stereotype.Component
import java.util.*

@Component
class UuidProvider : UidProvider {

    override fun provide(): String = UUID.randomUUID().toString()
}