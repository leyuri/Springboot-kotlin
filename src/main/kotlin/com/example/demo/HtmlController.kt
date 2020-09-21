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
        model.addAttribute("title","Home")
        return "index"
    }

    @GetMapping("/{formType}")
    fun htmlForm(model: Model, @PathVariable formType:String) : String {

        var response : String=""

        if (formType.equals("sign")) {
            response="sign"
        }
        else if (formType.equals("login")) {
            response="login"
        }

        model.addAttribute("title", response)

        return response
    }
}