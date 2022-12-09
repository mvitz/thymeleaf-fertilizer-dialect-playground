package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class MagicHeaders(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {
  
    var parentMagicHeaders = componentContext.context.getVariable("parentMagicHeaders") as MagicHeaders?
    var level: Int = parentMagicHeaders?.level?.let { it + 1 } ?: 1

    init { 
        componentContext.structureHandler.setLocalVariable("parentMagicHeaders", this)
    }
}
