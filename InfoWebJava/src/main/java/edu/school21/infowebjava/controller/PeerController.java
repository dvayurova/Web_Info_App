package edu.school21.infowebjava.controller;

import edu.school21.infowebjava.models.EntityInterface;

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
    public String show(Model model){
        model.addAttribute("entities", (List<EntityInterface>) peerService.getAll());
        model.addAttribute("columnNames", peerService.columnNames());
        model.addAttribute("tableName", peerService.tableName());
        return "entity-list";
    }

}
