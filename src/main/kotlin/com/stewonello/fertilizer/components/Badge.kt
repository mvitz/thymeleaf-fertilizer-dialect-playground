package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent

class Badge(attributes: MutableMap<String, String>, slotNames: MutableSet<String>) : FertilizerComponent (attributes, slotNames) {

    var type: String?

    init {
        this.type = attributes["type"]
    }

    public fun classNames(): String {
        return "badge " + (if (this.type == "danger") "bg-danger" else "bg-secondary")
    }

}
