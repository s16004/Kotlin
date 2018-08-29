package com.example.demo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView


@Controller
class HelloController {


    @RequestMapping("/test")
    fun test(model: Model):String {
        val id = 10
        model.addAttribute("check", id % 2 == 0)
        model.addAttribute("trueVal","Even")
        model.addAttribute("falseVal", "Odd")
        //val str = "message1 <hr />message 2<br/>message3"
        //model.addAttribute("str",str)
        //var stdList = mutableListOf<>()
        //stdList.add(StdData(100,"nohara","male"))
        //stdList.add(StdData(200,"shiroma","male"))
        //stdList.add(StdData(300,"aaa","male"))
        //stdList.addAttribute("stdList",stdList)

        val std = StdData(300, "higuchi", "male")
        model.addAttribute("std", std)
        return "test"
    }

    @RequestMapping(value="/")
    fun  first(model: Model):String{
        return "input"
    }


    @RequestMapping("/hello")
    fun index(
              @RequestParam("text", required=false) text: String?,
              @RequestParam("name",required =false) name: String?,
              @RequestParam("age", required =false) age: String?,
              @RequestParam("check1", required =false) check1: String?,
              @RequestParam("radio1", required =false) radio1: String?,
              @RequestParam("select1", required =false) select1: String?,
              @RequestParam("select2", required =false) select2: Array<String?>?,

              model: ModelAndView):ModelAndView {
        //val str = "Shiroma Asatarou"
        model.addObject("name",name!!)
        model.addObject("age",age!!)
        model.addObject("check1", check1!!)
        model.addObject("radio1", radio1!!)
        model.addObject("select1", select1!!)
        //model.addAttribute("select2",select2)
        var select2Str=""
        if(select2 != null)
            for(str in select2) select2Str += str
        model.addObject("select2", select2Str)
        model.viewName = "index"
        return model
    }
}