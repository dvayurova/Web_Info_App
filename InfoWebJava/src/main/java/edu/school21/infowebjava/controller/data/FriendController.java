package edu.school21.infowebjava.controller.data;

import edu.school21.infowebjava.models.Friend;
import edu.school21.infowebjava.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FriendController extends BaseTableController{
    @Autowired
    public FriendController(FriendService friendService) {
        this.entityService = friendService;
    }

    @GetMapping("/Friend")
    public String showFriends(Model model){
        return show(model);
    }
    @GetMapping("/Friend/create")
    public String createForm(Friend friend) {
        return "Friend/create";
    }

    @PostMapping("Friend/create")
    public String create(Friend friend, Model model) {
        entityService.add(friend);
        return "redirect:/Friend";
    }

    @GetMapping("/Friend/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        entityService.delete(id);
        return "redirect:/Friend";
    }

    @GetMapping("/Friend/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("friend", entityService.findById(id));
        return "Friend/update";
    }
    @PostMapping("/Friend/update/{id}")
    public String update(@ModelAttribute("friend") Friend friend){
        entityService.update(friend);
        return "redirect:/Friend";
    }
}
