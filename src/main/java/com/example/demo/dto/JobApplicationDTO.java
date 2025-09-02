package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class JobApplicationDTO {

    private Long id;

    private String jobTitle;

    private String companyName;

    private String status;

    private Date dateApplied;

    private Long userId;

    private String notes;
}
