package main.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringController {

    @RequestMapping("/*")
    @ResponseBody
    public String hello() {
        return "Hello";
    }
}
