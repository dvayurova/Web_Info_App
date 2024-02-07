package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.models.TimeTracker;
import edu.school21.infowebjava.service.TimeTrackerService;
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
public class TimeTrackerController extends BaseTableController{

    private static final Logger logger = LoggerFactory.getLogger(TimeTrackerController.class);
    @Autowired
    public TimeTrackerController(TimeTrackerService timeTrackerService) {
        this.entityService = timeTrackerService;
    }
    @GetMapping("TimeTracker")
    public String showtimeTrackers(Model model){
        return show(model);
    }

    @GetMapping("/TimeTracker/create")
    public String createForm(TimeTracker timeTracker) {
        return "TimeTracker/create";
    }

    @PostMapping("TimeTracker/create")
    public String create(TimeTracker timeTracker, Model model) {
        try {
            entityService.add(timeTracker);
            return "redirect:/TimeTracker";
        } catch(Exception e){
            logger.error("TimeTracker creating error", e);
            model.addAttribute("error", "TimeTracker creating error");
            return "TimeTracker/create";
        }
    }

    @GetMapping("/TimeTracker/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        entityService.delete(id);
        return "redirect:/TimeTracker";
    }

    @GetMapping("/TimeTracker/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("timeTracker", entityService.findById(id));
        return "TimeTracker/update";
    }
    @PostMapping("/TimeTracker/update/{id}")
    public String update(@ModelAttribute("timeTracker") TimeTracker timeTracker){
        entityService.update(timeTracker);
        return "redirect:/TimeTracker";
    }
}
