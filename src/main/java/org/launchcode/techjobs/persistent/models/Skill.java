package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {


   @NotBlank(message = "Required field")
   @Size(min=3, message = "Must be Minimum 3 characters")
    private String description;

    @ManyToMany(mappedBy="skills")//Need to check foreign key
   // @NotNull
    //  @JoinColumn(name = "skill_id")
    private List<Job> jobs = new ArrayList<>();


    public Skill() {
    }

    public Skill(String description, List<Job> jobs) {
        super();
        this.description = description;
        this.jobs = jobs;
    }


    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
