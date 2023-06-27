package com.stewonello.fertilizer.components

import com.innoq.comptus.core.Component

class List(componentContext: ComponentContext) : Component(componentContext) {

    val children: Any?

    init {
        this.children = componentContext.attributes["children"]
    }
}
