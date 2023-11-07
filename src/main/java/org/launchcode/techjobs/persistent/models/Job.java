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
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private Employer employer;
   @ManyToMany
   @NotNull
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

//    @Override
//    public String toString() {
//        return "Job{" +
//                "employer=" + employer +
//                ", skills=" + skills +
//                '}';
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Job job)) return false;
//        if (!super.equals(o)) return false;
//        return Objects.equals(employer, job.employer) && Objects.equals(skills, job.skills);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), employer, skills);
//    }



}
