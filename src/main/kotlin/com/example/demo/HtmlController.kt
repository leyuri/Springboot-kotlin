package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.security.MessageDigest

@Controller
class HtmlController {

    @Autowired
    lateinit var repository:UserRepository // 나중에 초기화를 해준다?!

    @RequestMapping("/")
    fun index(model:Model) : String {
        model.addAttribute("title","home")
        return "index"
    }

    fun crypto(ss:String):String {
        val sha=MessageDigest.getInstance("SHA-256")
        val hexa = sha.digest(ss.toByteArray())
        val crypto_str=hexa.fold("",{str, it -> str + "%02x".format(it)})
        return crypto_str
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

    @PostMapping("/sign")
    fun postSign(model: Model,
                 @RequestParam("id") userId:String,
                 @RequestParam("password") password:String) : String {

        try {
            val cryptoPass=crypto(password)

            repository.save(User(userId, cryptoPass))
        } catch (e:Exception) {
            e.printStackTrace()
        }
        model.addAttribute("title", "sign sucess")
        return "login"
    }
}