package com.example.demo.dto;

import java.util.Date;

public class JobApplicationDTO {

    private Long id;

    private String jobTitle;

    private String companyName;

    private String status;

    private Date dateApplied;

    private Long userId;

    public JobApplicationDTO(){

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(){
        this.userId = userId;
    }
}
