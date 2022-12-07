package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class Badge(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {

    var type: String?

    init {
        this.type = componentContext.attributes["type"].toString()
    }

    public fun classNames(): String {
        return "badge " + (if (this.type == "danger") "bg-danger" else "bg-secondary")
    }

}
