package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent

class List(attributes: Map<String, Any>, slotNames: MutableSet<String>) : FertilizerComponent (attributes, slotNames) {

    var children: Any?

    init {
        this.children = attributes["children"]
    }

}
