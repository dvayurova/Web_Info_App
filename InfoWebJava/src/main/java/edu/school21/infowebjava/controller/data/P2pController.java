package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.service.P2pService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class P2pController extends BaseTableController{
    @Autowired
    public P2pController(P2pService p2pService) {
        this.entityService = p2pService;
    }

    @GetMapping("/p2p-list")
    public String showP2p(Model model){
        return show(model);
    }
}
