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



@Controller
public class PeerController extends BaseTableController {

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
        entityService.add(peer);
        return "redirect:/Peer";
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
