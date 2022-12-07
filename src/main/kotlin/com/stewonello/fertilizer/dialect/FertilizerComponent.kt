package com.stewonello.fertilizer.dialect

import org.thymeleaf.context.ITemplateContext

data class FertilizerComponentContext(
    val attributes: Map<String, Any?>, 
    val slotNames: MutableSet<String?>, 
    val context: ITemplateContext
)

open class FertilizerComponent(val componentContext: FertilizerComponentContext) {

    public fun hasSlot(slotName: String): Boolean {
        return this.componentContext.slotNames.contains(slotName)
    }

}