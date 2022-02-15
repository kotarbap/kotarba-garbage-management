package pl.edu.wszib.kotarba.garbage.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.kotarba.garbage.management.service.IBagService;
import pl.edu.wszib.kotarba.garbage.management.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

    @Autowired
    IBagService bagService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("bags", this.bagService.getAllBags());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "main";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "contact";
    }

    @RequestMapping(value = "/newbags", method = RequestMethod.GET)
    public String newBags(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "newbags";
    }

    @RequestMapping(value = "/newbags", method = RequestMethod.POST)
    public String addBags(@RequestParam String color, @RequestParam int quantity) {
        this.bagService.addBagQuantity(color, quantity);

        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        } else {
            return "redirect:/newbags";
        }

    }

}
