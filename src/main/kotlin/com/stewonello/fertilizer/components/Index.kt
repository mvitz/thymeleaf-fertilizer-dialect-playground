package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class Index(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {

    var headings: MutableList<String> = mutableListOf<String>();

    init {
        componentContext.structureHandler.setLocalVariable("index", this);
    }
}