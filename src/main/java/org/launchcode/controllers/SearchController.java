package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    
    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        if (searchTerm.equals("")) {
            model.addAttribute("jobs", JobData.findAll());
            model.addAttribute("total", JobData.findAll().size());
        } else if (searchType.equals("all")) {
            model.addAttribute("jobs", JobData.findByValue(searchTerm));
            model.addAttribute("total", JobData.findByValue(searchTerm).size());
        } else {
            model.addAttribute("jobs", JobData.findByColumnAndValue(searchType, searchTerm));
            model.addAttribute("total", JobData.findByColumnAndValue(searchType, searchTerm).size());
        }

        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

}
