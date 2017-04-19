package com.highwire.interview.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for rendering a simple welcome page.
 */
@Controller
public class HomeController {

    // Get the name from the application's properties.
    @Value("${home.name}")
    private String name;
    
    /**
     * Present the welcome page using the configured name.
     * @param model the object that will contain variables to pass back to the view
     * @return the view name
     */
    @RequestMapping("/")
    public String getHome(Model model) {
        model.addAttribute("name", this.name);
        return "home";
    }
}
