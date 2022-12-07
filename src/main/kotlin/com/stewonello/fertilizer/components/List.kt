package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class List(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {

    var children: Any?

    init {
        this.children = componentContext.attributes["children"]
    }
}
