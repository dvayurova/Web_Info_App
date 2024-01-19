package edu.school21.infowebjava.controller.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data")
public class DataController {

    @GetMapping
    public String data(){
        return "data";
    }

}