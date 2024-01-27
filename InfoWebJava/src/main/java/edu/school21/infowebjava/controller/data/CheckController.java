package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.models.Check;
import edu.school21.infowebjava.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CheckController extends BaseTableController {
    @Autowired
    public CheckController(CheckService checkService) {
        this.entityService = checkService;
    }

    @GetMapping("/Check")
    public String showChecks(Model model){
        return show(model);
    }

    @GetMapping("/Check/create")
    public String createForm(Check check) {
        return "Check/create";
    }

    @PostMapping("Check/create")
    public String create(Check check, Model model) {
        entityService.add(check);
        return "redirect:/Check";
    }

    @GetMapping("/Check/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        entityService.delete(id);
        return "redirect:/Check";
    }

    @GetMapping("/Check/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("check", entityService.findById(id));
        return "Check/update";
    }
    @PostMapping("/Check/update/{id}")
    public String update(@ModelAttribute("check") Check check){
        entityService.update(check);
        return "redirect:/Check";
    }
}
