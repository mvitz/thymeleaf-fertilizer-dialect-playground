package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent

class Test : FertilizerComponent()

data class TestProps(
    val title: String,
    val content: String
)