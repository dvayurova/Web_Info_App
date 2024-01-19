package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CheckController extends BaseTableController {
    @Autowired
    public CheckController(CheckService checkService) {
        this.entityService = checkService;
    }

    @GetMapping("/check-list")
    public String showChecks(Model model){
        return show(model);
    }
}
