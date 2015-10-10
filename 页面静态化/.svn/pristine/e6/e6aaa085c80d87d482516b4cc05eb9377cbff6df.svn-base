package com.cplatform.mall.back.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping()
    public String home(Model model) {
    	// Page<DeveloperMessage> messagelist = developerMessageService.list(0,7);
    	// model.addAttribute("messagelist", messagelist);
        return "home";
    }

    @RequestMapping(value="info/{status}")
    public String info(@PathVariable String status, Model model) {
        model.addAttribute("status", status);
        return "info";
    }

}
