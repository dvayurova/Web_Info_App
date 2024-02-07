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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class XpController extends BaseTableController{

    private static final Logger logger = LoggerFactory.getLogger(XpController.class);
    @Autowired
    public XpController(XpService xpService) {
        this.entityService = xpService;
    }

    @GetMapping("/XP")
    public String showXp(Model model){
        return show(model);
    }

    @GetMapping("/XP/create")
    public String createForm(Model model) {
        model.addAttribute("xp", new XP());
        return "XP/create";
    }

    @PostMapping("XP/create")
    public String create(XP xp, Model model) {
        try {
            entityService.add(xp);
            return "redirect:/XP";
        } catch (Exception e){
            logger.error("XP creating error", e);
            model.addAttribute("error", "XP creating error");
            return "XP/create";
        }
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
