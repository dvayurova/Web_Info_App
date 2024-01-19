package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.service.TransferredPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransferredPointController extends BaseTableController {
    @Autowired
    public TransferredPointController(TransferredPointService transferredPointService) {
        this.entityService = transferredPointService;
    }

    @GetMapping("/transferredPoint-list")
    public String showTransferredPoints(Model model) {
        return show(model);
    }
}
