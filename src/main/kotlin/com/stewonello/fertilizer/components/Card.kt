package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent
import com.stewonello.fertilizer.dialect.FertilizerComponentContext

class Card(componentContext: FertilizerComponentContext) : FertilizerComponent(componentContext) {

    val href = componentContext.attributes["href"]?.toString()
    val headingLevel = componentContext.attributes["heading-level"]?.toString()?.toInt()

}
