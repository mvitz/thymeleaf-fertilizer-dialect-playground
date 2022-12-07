package com.stewonello.fertilizer

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class IndexController {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("data", mapOf(Pair("value", "some map object value passed down")))
        return "pages/index"
    }
}
