package edu.school21.infowebjava.controller.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DataController {

    @GetMapping("/data")
    public String data(){
        return "data";
    }


}
