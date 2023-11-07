package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/")
    public  String index(Model model){

        model.addAttribute("skills", skillRepository.findAll()); // Make visible all Skills
        return "skills/index";
    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {

        model.addAttribute(new Skill());
        return "skills/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "skills/add";
        } else {
            skillRepository.save(newSkill);
            return "redirect:";
        }
    }


    @GetMapping("view/{skillId}")// Individual skill display
    public String displayViewSkill(Model model, @PathVariable Integer skillId) {
      // @RequestParam(required = false)
        if (skillId !=null) {

            Optional<Skill> optSkill = skillRepository.findById(skillId);
            if (optSkill.isPresent()) {
                Skill skill = (Skill) optSkill.get();
              //  model.addAttribute("skill", skill);// OLD CODE
                model.addAttribute("skill", skill.getId());// pass Skill Object as a value

            }

            return "skills/view";

        } else {

            model.addAttribute("skills", skillRepository.findAll());

            return "redirect:../";

        }
    }


}
