package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "JobApplications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;

    private String companyName;

    private String status;

    private Date dateApplied;

    private Long userId;

    public JobApplication(){

    }

    public JobApplication(Long id, String jobTitle, String companyName, String status, Date dateApplied, Long userId){
        this.id = id;
        this.companyName = companyName;
        this.status = status;
        this.dateApplied = dateApplied;
        this.userId = userId;
    }

    public String getJobTitle(){
        return this.jobTitle;
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public String getStatus(){
        return this.status;
    }

    public Date getDateApplied(){
        return this.dateApplied;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
