package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.models.XP;
import edu.school21.infowebjava.service.XpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class XpController extends BaseTableController{
    @Autowired
    public XpController(XpService xpService) {
        this.entityService = xpService;
    }

    @GetMapping("/Xp")
    public String showXp(Model model){
        return show(model);
    }

    @GetMapping("/XP/create")
    public String createForm(XP xp) {
        return "XP/create";
    }

    @PostMapping("XP/create")
    public String create(XP xp, Model model) {
        entityService.add(xp);
        return "redirect:/XP";
    }

    @GetMapping("/XP/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        entityService.delete(id);
        return "redirect:/XP";
    }

    @GetMapping("/XP/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("xp", entityService.findById(id));
        return "XP/update";
    }
    @PostMapping("/XP/update/{id}")
    public String update(@ModelAttribute("xp") XP xp){
        entityService.update(xp);
        return "redirect:/XP";
    }
}
