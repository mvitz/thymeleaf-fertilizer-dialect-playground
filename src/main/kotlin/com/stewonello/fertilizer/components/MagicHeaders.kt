package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class MagicHeaders(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {
  
    var test: MagicHeaders?
    var level: Int

    init {
        test = componentContext.context.getVariable("parentMagicHeaders") as MagicHeaders?
        level = if (test != null) test!!.level +1 else 1
        componentContext.structureHandler.setLocalVariable("parentMagicHeaders", this)
    }
}
