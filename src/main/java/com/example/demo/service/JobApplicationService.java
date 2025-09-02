package com.example.demo.service;

import com.example.demo.dto.JobApplicationDTO;
import com.example.demo.dto.UserPrincipal;
import com.example.demo.entity.JobApplication;
import com.example.demo.repository.JobApplicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper mapper;

    public JobApplicationService(JobApplicationRepository repository, ModelMapper mapper) {
        this.jobApplicationRepository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public List<JobApplicationDTO> getJobApplications(Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        Long userId = principal.getUser().getId();
        List<JobApplication> jobApplications = this.jobApplicationRepository.findByUserId(userId);
        List<JobApplicationDTO> applications = null;

        if (!jobApplications.isEmpty()) {
            applications = jobApplications.stream().map(this::convertToDTO).toList();
        }
        return applications;
    }

    @Transactional
    public void createJobApplication(JobApplicationDTO jobApplicationDTO, UserPrincipal userPrincipal) {
        JobApplication application = this.convertToEntity(jobApplicationDTO);
        application.setUserId(userPrincipal.getUser().getId());
        this.jobApplicationRepository.save(application);
    }

    @Transactional
    public void updateJobApplicationStatus(JobApplicationDTO applicationDTO, UserPrincipal userPrincipal) {
        JobApplication jobApplication = this.jobApplicationRepository.findAllById(Collections.singleton(applicationDTO.getId())).getFirst();
        if (jobApplication != null) {
            jobApplication.setStatus(applicationDTO.getStatus());
        }
    }

    private JobApplicationDTO convertToDTO(JobApplication jobApplication) {
        return this.mapper.map(jobApplication, JobApplicationDTO.class);
    }

    private JobApplication convertToEntity(JobApplicationDTO jobApplicationDTO) {
        return this.mapper.map(jobApplicationDTO, JobApplication.class);
    }
}
