package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "JobApplications")
public class JobApplication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;

    private String companyName;

    private String status;

    private Date dateApplied;

    private Long userId;

    private String notes;

    public JobApplication() {

    }

    public JobApplication(Long id, String jobTitle, String companyName, String status, Date dateApplied, Long userId, String notes) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.status = status;
        this.dateApplied = dateApplied;
        this.userId = userId;
        this.notes = notes;
    }
}
