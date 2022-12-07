package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent

class Card(attributes: Map<String, String>, slotNames: MutableSet<String>) : FertilizerComponent (attributes, slotNames) {

    val href: String?
    //TODO: heading-level won't be easy with Thymeleaf. Maybe introduce a custom processor like `<foo:element name="h1">...`

    init {
        href = attributes["href"]
    }

}
