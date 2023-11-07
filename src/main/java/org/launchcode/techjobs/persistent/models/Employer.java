package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {


    @NotNull
    @NotBlank(message = "Location is required")
    @Size(min=2, max=250, message = "Location must be longer than 3 characters")
    private  String location;

    @OneToMany
    //@Valid
  //  @NotNull
    @JoinColumn(name = "employer_id")
    private List<Job> jobs = new ArrayList<>();


    public Employer() {}
//an employer can only have one location.
//    public Employer(String location) {
//
//        this.location = location;
//    }
        public Employer(String location, List<Job> jobs) {
        super();
        this.location = location;
        this.jobs = jobs;
    }




    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
