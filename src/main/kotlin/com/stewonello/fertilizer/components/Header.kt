package com.stewonello.fertilizer.components

import com.innoq.comptus.core.Component

class Header(componentContext: ComponentContext) : Component(componentContext) {

    val level = intAttribute("level")
            .orElseGet { outerVariable("parentMagicHeaders", MagicHeaders::class.java).map({ it.level }).orElse(1) }
}
