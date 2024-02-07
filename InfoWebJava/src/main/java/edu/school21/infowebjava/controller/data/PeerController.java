package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.models.Peer;
import edu.school21.infowebjava.service.PeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class PeerController extends BaseTableController {

    private static final Logger logger = LoggerFactory.getLogger(PeerController.class);

    @Autowired
    public PeerController(PeerService peerService) {
        this.entityService = peerService;
    }

    @GetMapping("/Peer")
    public String showPeers(Model model) {
        return show(model);
    }

    @GetMapping("/Peer/create")
    public String createForm(Peer peer) {
        return "Peer/create";
    }

    @PostMapping("Peer/create")
    public String create(Peer peer, Model model) {
        try {
            entityService.add(peer);
            return "redirect:/Peer";
        } catch(Exception e) {
            logger.error("Peer creating error", e);
            model.addAttribute("error", "Peer creating error");
            return "Peer/create";
        }
    }

    @GetMapping("/Peer/delete/{id}")
    public String delete(@PathVariable("id") String nickname) {
        entityService.delete(nickname);
        return "redirect:/Peer";
    }

    @GetMapping("/Peer/update/{id}")
    public String updateForm(@PathVariable("id") String nickname, Model model) {
        model.addAttribute("peer", entityService.findById(nickname));
        return "Peer/update";
    }
    @PostMapping("/Peer/update/{id}")
    public String update(@ModelAttribute("peer") Peer peer){
        entityService.update(peer);
        return "redirect:/Peer";
    }

}
