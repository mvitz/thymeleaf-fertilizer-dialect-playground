package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class Button(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {

    var styling: String
    var additionalClasses: String
    val restAttributes: MutableMap<String, Any?>

    init {
        this.restAttributes = componentContext.attributes.toMutableMap()
        this.styling = this.restAttributes.remove("styling").toString()
        this.additionalClasses = this.restAttributes.remove("additional-classes").toString()
    }

    public fun classNames(): String {
        return "btn " + (if (this.styling == "cta") "btn-primary" else "btn-secondary") + " " + this.additionalClasses
    }

    public fun otherAttributes(): String {
        if (restAttributes.size > 0) {
            return restAttributes.map { (k, v) -> "${k}='${v}'"}.joinToString(",")
        }
        else {
            return "," // "" leads to Thymeleaf errors in th:attr
        }
    }

}
