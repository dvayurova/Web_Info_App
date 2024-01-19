package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.service.EntityService;
import org.springframework.ui.Model;

import java.util.List;

public class BaseTableController {

    protected EntityService entityService;

    public String show(Model model) {
        model.addAttribute("entities", (List<EntityInterface>) entityService.getAll());
        model.addAttribute("columnNames", entityService.columnNames());
        model.addAttribute("tableName", entityService.tableName());
        return "entity-list";
    }
}
