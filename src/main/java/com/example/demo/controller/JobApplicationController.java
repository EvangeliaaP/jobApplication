package com.example.demo.controller;

import com.example.demo.dto.JobApplicationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.JobApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("job-application")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService){
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("jobApplications")
    public ResponseEntity<JobApplicationDTO[]> getJobApplication(@RequestParam("user") UserDTO user){
        return ResponseEntity.ok(this.jobApplicationService.getJobApplications());
    }

    @PostMapping("createJobApplication")
    public ResponseEntity<Void> createJobApplication(@RequestBody JobApplicationDTO jobApplicationDTO){
        this.jobApplicationService.createJobApplication(jobApplicationDTO);
        return null;
    }
}
