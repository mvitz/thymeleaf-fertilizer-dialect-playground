package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent

// Must inherit FertilizerComponent() to be auto registered
data class VCard(var attributes: Map<String, String>) : FertilizerComponent () {

    var data: Map<String, String>? = null
    var updatedAt: java.time.LocalDate 
    var title: String?
    var content: String?
    
    init {
       this.title = this.attributes["title"]
       this.content = this.attributes["content"]
       this.updatedAt = java.time.LocalDate.now()
    }

    public fun humanUpdatedAt(): String {
        return "Nach Lucas ist es ${updatedAt} Uhr9"
    }
}
