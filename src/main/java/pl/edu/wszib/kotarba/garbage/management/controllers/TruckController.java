package pl.edu.wszib.kotarba.garbage.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.kotarba.garbage.management.service.ITruckService;
import pl.edu.wszib.kotarba.garbage.management.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class TruckController {

    @Autowired
    ITruckService truckService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/truck", method = RequestMethod.GET)
    public String truck() {
        this.truckService.confirmTruck();
        return "redirect:/main";
    }

    @RequestMapping(value = "/trucks", method = RequestMethod.GET)
    public String trucks(Model model) {
        model.addAttribute("trucks", this.truckService.getTrucksForCurrentUser());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "trucks";
    }
}
