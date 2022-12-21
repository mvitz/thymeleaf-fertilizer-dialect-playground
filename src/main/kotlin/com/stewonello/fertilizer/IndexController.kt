package com.stewonello.fertilizer

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView

@Controller
class IndexController {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("list", mapOf(Pair("foo", "bar"), Pair("fnd", "saarland")))
        return "pages/index"
    }

    @GetMapping("/foo")
    fun foo(): ModelAndView {
        return ModelAndView("pages/foo");
    }
}
