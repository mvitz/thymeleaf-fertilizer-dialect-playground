package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class Badge(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {

    var type = componentContext.attributes["type"]?.toString() ?: "default"

    fun classNames() =
        "badge " + (if (this.type == "danger") "bg-danger" else "bg-secondary")
}
