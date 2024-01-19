package edu.school21.infowebjava.controller.data;


import edu.school21.infowebjava.service.PeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PeerController extends BaseTableController {

    @Autowired
    public PeerController(PeerService peerService) {
        this.entityService = peerService;
    }

    @GetMapping("/peer-list")
    public String showPeers(Model model){
        return show(model);
    }

}
