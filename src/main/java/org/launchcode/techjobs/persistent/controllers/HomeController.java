package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");
       // model.addAttribute("jobs", new Job());//  pass the variable to views
        model.addAttribute("jobs", jobRepository.findAll());// Display all jobs
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {

        model.addAttribute("title", "Add Job");
        model.addAttribute( new Job());// Display the form
        model.addAttribute("employers", employerRepository.findAll());//  Must keep Visible to select the employer
        model.addAttribute("skills", skillRepository.findAll());// Make visible the skills in Job Form
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam(required = false) int employerId, @RequestParam(required=false)List<Integer> skills) {

//(required=false) must need for form submission
        if (errors.hasErrors()) {

            model.addAttribute("employers", employerRepository.findAll());//  Must keep Visible to select the employer
            model.addAttribute("skills", skillRepository.findAll());// Make visible the skills in Job Form

            return "add";

        }

     //else  if (employerId != null) {
                Optional<Employer> optEmployer = employerRepository.findById(employerId);
                if (optEmployer.isPresent()) {
                    Employer employer = (Employer) optEmployer.get();
                    newJob.setEmployer(employer);

                }

        if (skills != null) {

            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);

        }

        jobRepository.save(newJob);


        return "redirect:/";


            }




    @GetMapping("view/{jobId}")// View individual job
    public String displayViewJob(Model model, @PathVariable Integer jobId) {
        //model.addAttribute("jobs", jobId);
        if (jobId != null) {
            Optional<Job> optJob = jobRepository.findById(jobId);
            if (optJob.isPresent()) {
                Job job = (Job) optJob.get();
                // model.addAttribute("employer", new Employer());//working code
                model.addAttribute("job", job);// Passing values
            }
            return "view";
        }
        else {
            model.addAttribute("jobs", jobRepository.findAll());//Make visible the Jobs
            return "redirect:/";
        }
    }

    }

