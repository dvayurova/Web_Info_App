package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.models.Task;
import edu.school21.infowebjava.service.TaskService;
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
public class TaskController extends BaseTableController{

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    public TaskController(TaskService taskService) {
        this.entityService = taskService;
    }

    @GetMapping("/Task")
    public String showTasks(Model model){
        return show(model);
    }

    @GetMapping("/Task/create")
    public String createForm(Task task) {
        return "Task/create";
    }

    @PostMapping("Task/create")
    public String create(Task task, Model model) {
        try {
            entityService.add(task);
            return "redirect:/Task";
        } catch(Exception e) {
            logger.error("Task creating error", e);
            model.addAttribute("error", "Task creating error");
            return "Task/create";
        }
    }

    @GetMapping("/Task/delete/{id}")
    public String delete(@PathVariable("id") String task) {
        entityService.delete(task);
        return "redirect:/Task";
    }

    @GetMapping("/Task/update/{id}")
    public String updateForm(@PathVariable("id") String task, Model model) {
        model.addAttribute("task", entityService.findById(task));
        return "Task/update";
    }
    @PostMapping("/Task/update/{id}")
    public String update(@ModelAttribute("task") Task task){
        entityService.update(task);
        return "redirect:/Task";
    }
}
