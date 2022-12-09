package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class Button(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {

    val restAttributes = componentContext.attributes.toMutableMap()
    val styling = this.restAttributes.remove("styling").toString()
    val additionalClasses = this.restAttributes.remove("additional-classes").toString()

    fun classNames() = "btn ${if (this.styling == "cta") "btn-primary" else "btn-secondary"} ${this.additionalClasses}".trim()

    fun otherAttributes() = if (restAttributes.size > 0) {
        restAttributes.map { (k, v) -> "${k}='${v}'"}.joinToString(",")
    }
    else {
        "," // "" leads to Thymeleaf errors in th:attr
    }

}
