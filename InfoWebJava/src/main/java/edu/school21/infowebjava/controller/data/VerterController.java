package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.service.VerterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VerterController extends BaseTableController{
    @Autowired
    public VerterController(VerterService verterService) {
        this.entityService = verterService;
    }

    @GetMapping("/verter-list")
    public String showVerter(Model model){
        return  show(model);
    }
}
