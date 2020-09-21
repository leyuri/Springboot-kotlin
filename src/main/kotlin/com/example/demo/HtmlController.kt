package com.example.demo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HtmlController {

    @RequestMapping("/")
    fun index(model:Model) : String {
        return "index"
    }

    @GetMapping("/sign")
    fun htmlForm(model: Model) : String {
        return "sign"
    }
    @GetMapping("/login")
    fun htmlForm2(model: Model) : String {
        return "login"
    }
}