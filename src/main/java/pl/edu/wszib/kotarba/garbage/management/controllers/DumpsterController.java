package pl.edu.wszib.kotarba.garbage.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.kotarba.garbage.management.service.IDumpsterService;
import pl.edu.wszib.kotarba.garbage.management.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/dumpster")
public class DumpsterController {
    @Autowired
    IDumpsterService dumpsterService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/add/{bagId}")
    public String addBagToDumpster(@PathVariable Integer bagId) {
        this.dumpsterService.addBagToDumpster(bagId);
        return "redirect:/main";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String dumpster(Model model) {
        model.addAttribute("dumpster",
                this.sessionObject.getDumpster());
        model.addAttribute("sum", this.sessionObject.getDumpster().getSum());
        model.addAttribute("logged", this.sessionObject.isLogged());

        return "dumpster";
    }
}
