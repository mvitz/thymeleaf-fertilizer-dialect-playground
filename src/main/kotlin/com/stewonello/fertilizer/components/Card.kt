package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class Card(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {

    val href: String?
    var headingLevel: Int = 1

    init {
        href = componentContext.attributes["href"]?.toString()
        if (componentContext.attributes["heading-level"] is String) {
            headingLevel = (componentContext.attributes["heading-level"] as String).toInt()
        }
    }

}
