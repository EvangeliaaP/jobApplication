package com.example.demo.controller;

import com.example.demo.dto.JobApplicationDTO;
import com.example.demo.dto.UserPrincipal;
import com.example.demo.service.JobApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public ResponseEntity<Void> createJobApplication(@RequestBody @Valid JobApplicationDTO jobApplicationDTO,
                                                     @AuthenticationPrincipal UserPrincipal userPrincipal, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        this.jobApplicationService.createJobApplication(jobApplicationDTO, userPrincipal);
        return ResponseEntity.ok().build();
    }

    @PostMapping("updateApplicationStatus")
    public ResponseEntity<Void> updateJobApplicationStatus(@RequestBody JobApplicationDTO jobApplicationDTO,
                                                           @AuthenticationPrincipal UserPrincipal principal, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        this.jobApplicationService.updateJobApplicationStatus(jobApplicationDTO, principal);
        return ResponseEntity.ok().build();
    }

}
