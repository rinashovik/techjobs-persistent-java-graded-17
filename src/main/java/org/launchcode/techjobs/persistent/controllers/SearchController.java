package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.JobData;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.launchcode.techjobs.persistent.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        //   model.addAttribute("searchTerm", searchTerm);

        return "search";
    }




    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam(required = false) String searchType, @RequestParam(required = false) String searchTerm) {
        Iterable<Job> jobs;
        if (searchType.equals("all") && searchTerm.equalsIgnoreCase("all") || searchTerm.isEmpty()) {
            jobs = jobRepository.findAll();
            model.addAttribute("title", "All Jobs");
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);


        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm, jobRepository.findAll());
            // jobs = jobRepository.findAll();
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);

        }
        //  model.addAttribute("searchTerm", searchTerm);

        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchTerm", searchTerm);
        //model.addAttribute("searchType", searchType);
        model.addAttribute("jobs", jobs);
        return "search";
    }
}









