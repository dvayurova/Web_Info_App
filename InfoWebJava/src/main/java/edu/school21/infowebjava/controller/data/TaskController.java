package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController extends BaseTableController{
    @Autowired
    public TaskController(TaskService taskService) {
        this.entityService = taskService;
    }

    @GetMapping("/task-list")
    public String showTasks(Model model){
        return show(model);
    }
}
