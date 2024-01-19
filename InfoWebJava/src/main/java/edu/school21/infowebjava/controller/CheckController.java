package edu.school21.infowebjava.controller;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CheckController {
    private final CheckService checkService;

    @Autowired
    public CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping("/check-list")
    public String show(Model model){
        model.addAttribute("entities", (List<EntityInterface>) checkService.getAll());
        model.addAttribute("columnNames", checkService.columnNames());
        model.addAttribute("tableName", checkService.tableName());
        return "entity-list";
    }
}
