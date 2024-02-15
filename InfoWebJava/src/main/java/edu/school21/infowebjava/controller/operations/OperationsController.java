package edu.school21.infowebjava.controller.operations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class OperationsController {
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/operations")
    public String operationsForm() {
        return "operations";
    }

    @PostMapping("/execute-query")
    @Transactional
    public String executeQuery(String sqlQuery, Model model) {
        try {
            List<Object[]> result = entityManager.createNativeQuery(sqlQuery).getResultList();
            if (!result.isEmpty()) {
                Object[] firstRow = result.get(0);
                model.addAttribute("columnNames", getColumnNames(firstRow));
                model.addAttribute("queryResult", result);
            } else {
                model.addAttribute("message", "Запрос выполнен успешно, но не вернул результатов.");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Произошла ошибка при выполнении запроса: " + e.getMessage());
        }
        return "operations"; //
    }

    private String[] getColumnNames(Object[] row) {
        String[] columnNames = new String[row.length];
        for (int i = 0; i < row.length; i++) {
            columnNames[i] = "Column " + i;
        }
        return columnNames;
    }
}
