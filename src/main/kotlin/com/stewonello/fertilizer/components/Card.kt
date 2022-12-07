package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class Card(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {

    val href: String?
    //TODO: heading-level won't be easy with Thymeleaf. Maybe introduce a custom processor like `<foo:element name="h1">...`

    init {
        href = componentContext.attributes["href"]?.toString()
    }

}
