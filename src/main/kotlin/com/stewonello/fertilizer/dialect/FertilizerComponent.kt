package com.stewonello.fertilizer.dialect

open class FertilizerComponent(attributes: MutableMap<String, String>, var slotNames: MutableSet<String>) {
    public fun hasSlot(slotName: String): Boolean {
        return slotNames.contains(slotName)
    }
}