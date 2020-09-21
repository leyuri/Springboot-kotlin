package com.example.demo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HtmlController {

    @RequestMapping("/")
    fun index(model:Model) : String {
        return "index"
    }
}