package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent

class Button(val attributes: MutableMap<String, String>, slotNames: MutableSet<String>) : FertilizerComponent(attributes, slotNames) {

    var styling: String
    var additionalClasses: String

    init {
        this.styling = this.attributes.remove("styling") ?: ""
        this.additionalClasses = this.attributes.remove("additional-classes") ?: ""
    }

    public fun classNames(): String {
        return "btn " + (if (this.styling == "cta") "btn-primary" else "btn-secondary") + " " + this.additionalClasses
    }

    public fun otherAttributes(): String {
        if (attributes.size > 0) {
            return attributes.map { (k, v) -> "${k}=${v}"}.joinToString(",")
        }
        else {
            return "," // "" leads to Thymeleaf errors in th:attr
        }
    }

}
