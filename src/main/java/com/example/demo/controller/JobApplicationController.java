package com.example.demo.controller;

import com.example.demo.dto.JobApplicationDTO;
import com.example.demo.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("applications")
    public ResponseEntity<List<JobApplicationDTO>> getJobApplications(Authentication authentication) {
        return ResponseEntity.ok(this.jobApplicationService.getJobApplications(authentication));
    }

    @PostMapping("createJobApplication")
    public ResponseEntity<Void> createJobApplication(@RequestBody JobApplicationDTO jobApplicationDTO) {
        this.jobApplicationService.createJobApplication(jobApplicationDTO);
        return ResponseEntity.ok().build();
    }
}
