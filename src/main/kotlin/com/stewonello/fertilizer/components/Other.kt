package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent

class Other : FertilizerComponent()

data class OtherProps(
    val title: String,
    val content: String
)