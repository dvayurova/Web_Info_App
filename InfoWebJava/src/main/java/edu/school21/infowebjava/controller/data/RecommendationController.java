package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.models.Recommendation;
import edu.school21.infowebjava.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecommendationController extends BaseTableController{

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.entityService = recommendationService;
    }

    @GetMapping("/Recommendation")
    public String showRecommendations(Model model){
        return show(model);
    }

    @GetMapping("/Recommendation/create")
    public String createForm(Recommendation recommendation) {
        return "Recommendation/create";
    }

    @PostMapping("Recommendation/create")
    public String create(Recommendation recommendation, Model model) {
        entityService.add(recommendation);
        return "redirect:/Recommendation";
    }

    @GetMapping("/Recommendation/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        entityService.delete(id);
        return "redirect:/Recommendation";
    }

    @GetMapping("/Recommendation/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("recommendation", entityService.findById(id));
        return "Recommendation/update";
    }
    @PostMapping("/Recommendation/update/{id}")
    public String update(@ModelAttribute("recommendation") Recommendation recommendation){
        entityService.update(recommendation);
        return "redirect:/Recommendation";
    }
}
