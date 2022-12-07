package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent

class Button(attributes: Map<String, String>, slotNames: MutableSet<String>) : FertilizerComponent(attributes, slotNames) {

    var styling: String
    var additionalClasses: String
    val restAttributes: MutableMap<String, String>

    init {
        this.restAttributes = attributes.toMutableMap()
        this.styling = this.restAttributes.remove("styling") ?: ""
        this.additionalClasses = this.restAttributes.remove("additional-classes") ?: ""
    }

    public fun classNames(): String {
        return "btn " + (if (this.styling == "cta") "btn-primary" else "btn-secondary") + " " + this.additionalClasses
    }

    public fun otherAttributes(): String {
        if (restAttributes.size > 0) {
            return restAttributes.map { (k, v) -> "${k}=${v}"}.joinToString(",")
        }
        else {
            return "," // "" leads to Thymeleaf errors in th:attr
        }
    }

}
