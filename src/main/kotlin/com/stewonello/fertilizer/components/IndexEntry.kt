package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class IndexEntry(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {

    var text: String

    init {
        text = componentContext.attributes["text"] as String

        var index = componentContext.context.getVariable("index") as Index
        index.headings.add(text)
    }
}