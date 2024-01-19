package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FriendController extends BaseTableController{
    @Autowired
    public FriendController(FriendService friendService) {
        this.entityService = friendService;
    }

    @GetMapping("/friend-list")
    public String showFriends(Model model){
        return show(model);
    }
}
