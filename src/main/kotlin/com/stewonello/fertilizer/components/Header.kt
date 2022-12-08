package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class Header(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {

    var level: Int

    init {
        if (componentContext.attributes["level"] is String) {
            this.level = (componentContext.attributes["level"] as String).toInt()
        }
        else if (componentContext.attributes["level"] is Int) {
            this.level = componentContext.attributes["level"] as Int
        }
        else if (componentContext.context.getVariable("parentMagicHeaders") is MagicHeaders) {
            this.level = (componentContext.context.getVariable("parentMagicHeaders") as MagicHeaders).level
        } else {
            this.level = 1   
        }

    }

}
