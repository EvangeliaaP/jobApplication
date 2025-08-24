package com.example.demo.service;

import com.example.demo.dto.JobApplicationDTO;
import com.example.demo.dto.UserPrincipal;
import com.example.demo.entity.JobApplication;
import com.example.demo.repository.JobApplicationRepository;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public JobApplicationService(JobApplicationRepository repository, UserRepository userRepository, ModelMapper mapper) {
        this.jobApplicationRepository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<JobApplicationDTO> getJobApplications(Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        Long userId = principal.user.getId();
        List<JobApplication> jobApplications = this.jobApplicationRepository.findByUserId(userId);
        List<JobApplicationDTO> applications = null;

        if (!jobApplications.isEmpty()) {
            applications = jobApplications.stream().map(this::convertToDTO).toList();
        }
        return applications;
    }

    public void createJobApplication(JobApplicationDTO jobApplicationDTO) {
        this.jobApplicationRepository.save(this.convertToEntity(jobApplicationDTO));
    }

    private JobApplicationDTO convertToDTO(JobApplication jobApplication) {
        return this.mapper.map(jobApplication, JobApplicationDTO.class);
    }

    private JobApplication convertToEntity(JobApplicationDTO jobApplicationDTO) {
        return this.mapper.map(jobApplicationDTO, JobApplication.class);
    }

    public JobApplicationRepository getJobApplicationRepository() {
        return jobApplicationRepository;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }
}
