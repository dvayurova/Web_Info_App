package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.models.P2P;
import edu.school21.infowebjava.service.P2pService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class P2pController extends BaseTableController{
    @Autowired
    public P2pController(P2pService p2pService) {
        this.entityService = p2pService;
    }

    @GetMapping("/P2P")
    public String showP2p(Model model){
        return show(model);
    }

    @GetMapping("/P2P/create")
    public String createForm(Model model) {

        model.addAttribute("p2p", new P2P());
        return "P2P/create";
    }

    @PostMapping("P2P/create")
    public String create(P2P p2p, Model model) {
        System.out.println("===== P2P ======:" + p2p);
        System.out.println("===== P2P state class ======:"+ p2p.getState().getClass());
        p2p.setState(p2p.getState().toString());
        entityService.add(p2p);
        return "redirect:/P2P";
    }

    @GetMapping("/P2P/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        entityService.delete(id);
        return "redirect:/P2P";
    }

    @GetMapping("/P2P/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("p2p", entityService.findById(id));
        return "P2P/update";
    }
    @PostMapping("/P2P/update/{id}")
    public String update(@ModelAttribute("p2p") P2P p2p){
        entityService.update(p2p);
        return "redirect:/P2P";
    }
}
