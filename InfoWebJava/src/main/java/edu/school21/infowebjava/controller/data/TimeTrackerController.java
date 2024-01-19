package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.service.TimeTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TimeTrackerController extends BaseTableController{
    @Autowired
    public TimeTrackerController(TimeTrackerService timeTrackerService) {
        this.entityService = timeTrackerService;
    }
    @GetMapping("timeTracker-list")
    public String showtimeTrackers(Model model){
        return show(model);
    }
}
