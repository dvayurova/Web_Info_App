package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecommendationController extends BaseTableController{

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.entityService = recommendationService;
    }

    @GetMapping("/recommendation-list")
    public String showRecommendations(Model model){
        return show(model);
    }
}
