package com.stewonello.fertilizer.components

import com.innoq.comptus.core.Component
import kotlin.jvm.optionals.getOrNull

class Button(componentContext: ComponentContext) : Component(componentContext) {

    val styling = stringAttribute("styling").getOrNull()
    val additionalClasses = stringAttribute("additional-classes").orElse("")

    fun classNames() = "btn ${if (this.styling == "cta") "btn-primary" else "btn-secondary"} ${this.additionalClasses}".trim()
}
