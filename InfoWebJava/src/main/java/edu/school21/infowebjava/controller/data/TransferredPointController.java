package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.models.TransferredPoint;
import edu.school21.infowebjava.service.TransferredPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransferredPointController extends BaseTableController {
    @Autowired
    public TransferredPointController(TransferredPointService transferredPointService) {
        this.entityService = transferredPointService;
    }

    @GetMapping("/TransferredPoint")
    public String showTransferredPoints(Model model) {
        return show(model);
    }

    @GetMapping("/TransferredPoint/create")
    public String createForm(TransferredPoint transferredPoint) {
        return "TransferredPoint/create";
    }

    @PostMapping("TransferredPoint/create")
    public String create(TransferredPoint transferredPoint, Model model) {
        entityService.add(transferredPoint);
        return "redirect:/TransferredPoint";
    }

    @GetMapping("/TransferredPoint/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        entityService.delete(id);
        return "redirect:/TransferredPoint";
    }

    @GetMapping("/TransferredPoint/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("transferredPoint", entityService.findById(id));
        return "TransferredPoint/update";
    }
    @PostMapping("/TransferredPoint/update/{id}")
    public String update(@ModelAttribute("transferredPoint") TransferredPoint transferredPoint){
        entityService.update(transferredPoint);
        return "redirect:/TransferredPoint";
    }
}
