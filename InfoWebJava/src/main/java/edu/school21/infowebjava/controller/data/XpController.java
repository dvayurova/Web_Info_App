package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.service.XpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class XpController extends BaseTableController{
    @Autowired
    public XpController(XpService xpService) {
        this.entityService = xpService;
    }

    @GetMapping("/xp-list")
    public String showXp(Model model){
        return show(model);
    }
}
