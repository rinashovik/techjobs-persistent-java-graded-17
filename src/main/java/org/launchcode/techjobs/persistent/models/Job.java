package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity{

    /*CascadeType. ALL is a cascading type in Hibernate that specifies that all state transitions
     for auto update CRUD should be cascaded from the parent entity to the child entities.
  */
   @ManyToOne(cascade = CascadeType.ALL)// f
    private Employer employer;
   @ManyToMany
   private List<Skill> skills = new ArrayList<>();

    public Job() {
    }

    public Job(Employer employer, List<Skill> skills) {
        this.employer = employer;
        this.skills = skills;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }


}
