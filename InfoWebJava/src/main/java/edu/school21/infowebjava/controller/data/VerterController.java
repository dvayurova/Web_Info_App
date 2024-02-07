package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.models.Verter;
import edu.school21.infowebjava.service.VerterService;
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
public class VerterController extends BaseTableController {

    private static final Logger logger = LoggerFactory.getLogger(VerterController.class);

    @Autowired
    public VerterController(VerterService verterService) {
        this.entityService = verterService;
    }

    @GetMapping("/Verter")
    public String showVerter(Model model) {
        return show(model);
    }

    @GetMapping("/Verter/create")
    public String createForm(Verter verter) {
        return "Verter/create";
    }

    @PostMapping("Verter/create")
    public String create(Verter verter, Model model) {
        try {
            entityService.add(verter);
            return "redirect:/Verter";
        } catch (Exception e) {
            logger.error("Verter creating error", e);
            model.addAttribute("error", "Verter creating error");
            return "Verter/create";
        }
    }

    @GetMapping("/Verter/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        entityService.delete(id);
        return "redirect:/Verter";
    }

    @GetMapping("/Verter/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("verter", entityService.findById(id));
        return "Verter/update";
    }

    @PostMapping("/Verter/update/{id}")
    public String update(@ModelAttribute("verter") Verter verter) {
        entityService.update(verter);
        return "redirect:/Verter";
    }
}
