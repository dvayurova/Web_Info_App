package edu.school21.infowebjava.controller;

import edu.school21.infowebjava.models.Peer;
import edu.school21.infowebjava.service.PeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PeerController {

    private final PeerService peerService;

    @Autowired
    public PeerController(PeerService peerService) {
        this.peerService = peerService;
    }

    @GetMapping("/peer-list")
    public String showPeers(Model model){
        List<Peer> peers = peerService.getAllPeers();
        model.addAttribute("peers", peers);
        return "peer-list";
    }

}
