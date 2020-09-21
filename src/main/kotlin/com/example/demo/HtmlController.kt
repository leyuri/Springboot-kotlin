package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.security.MessageDigest
import javax.servlet.http.HttpSession

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

    @PostMapping("/login")
    fun postlogin(model: Model,
                  session: HttpSession,
                  @RequestParam("id") userId:String,
                  @RequestParam("password") password: String):String{

        var pageName = ""

        try {
            val cryptoPass=crypto(password)
            val db_user = repository.findByUserId(userId)

            if (db_user != null) {
                val db_pass = db_user.password

                if(cryptoPass.equals(db_pass)) {
                    session.setAttribute("userId", db_user.userId)
                    model.addAttribute("title", "welcome")
                    model.addAttribute("userId", userId)
                    pageName = "welcome"
                }
                else {
                    model.addAttribute("title","login")
                    pageName = "login"
                }
            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
        return "welcome"
    }
}