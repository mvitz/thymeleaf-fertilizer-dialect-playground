package com.stewonello.fertilizer.dialect

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.processor.element.IElementModelStructureHandler

data class FertilizerComponentContext(
    val attributes: Map<String, Any?>, 
    val slotNames: MutableSet<String?>, 
    val context: ITemplateContext,
    val structureHandler: IElementModelStructureHandler
)

open class FertilizerComponent(val componentContext: FertilizerComponentContext) {

    fun hasSlot(slotName: String): Boolean {
        return this.componentContext.slotNames.contains(slotName)
    }

}