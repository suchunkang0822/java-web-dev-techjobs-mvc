package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.launchcode.javawebdevtechjobsmvc.models.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
//        model.addAttribute("search", new Search());
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("/results")
    public String displaySearchResults(Model model,
                                       @RequestParam(required = false) String searchType,
                                       @RequestParam(required = false) String searchTerm
                                       ){
        ArrayList<Job> jobs;
        if(searchTerm == "all" || searchTerm.isEmpty()){
            jobs = JobData.findAll();
        }else{
            jobs = JobData.findByColumnAndValue(searchType,searchTerm);
        }
        model.addAttribute("jobs",jobs);
        model.addAttribute("columns", columnChoices);
//        model.addAttribute("search", search);
        return "search";
    }
}
