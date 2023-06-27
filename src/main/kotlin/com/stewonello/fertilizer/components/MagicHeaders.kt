package com.stewonello.fertilizer.components

import com.innoq.comptus.core.Component

class MagicHeaders(componentContext: ComponentContext) : Component(componentContext) {

    val parentMagicHeaders = outerVariable("parentMagicHeaders", MagicHeaders::class.java).orElseThrow()
    val level: Int = parentMagicHeaders?.level?.let { it + 1 } ?: 1

    init {
        setInnerVariable("parentMagicHeaders", this);
    }
}
