package com.lucassena.jobapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucassena.jobapi.entities.Job;
import com.lucassena.jobapi.repositories.CompanyRepository;
import com.lucassena.jobapi.repositories.JobRepository;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public boolean createJob(Job job) {
        if (job.getCompany() == null || job.getCompany().getId() == null) {
            return false;
        }

        if (!companyRepository.existsById(job.getCompany().getId())) {
            return false;
        }

        jobRepository.save(job);
        return true;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateJob(Long id, Job job) {

        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isEmpty()) {
            return false;
        }

        if (job.getCompany() != null && !companyRepository.existsById(job.getCompany().getId())) {
            return false;
        }

        updateJobAttributes(jobOptional.get(), job);
        jobRepository.save(jobOptional.get());
        return true;
    }

    private void updateJobAttributes(Job existingJob, Job newJob) {

        existingJob.setTitle(newJob.getTitle());
        existingJob.setDescription(newJob.getDescription());
        existingJob.setMinSalary(newJob.getMinSalary());
        existingJob.setMaxSalary(newJob.getMaxSalary());
        existingJob.setLocation(newJob.getLocation());
        existingJob.setCompany(newJob.getCompany());
    }

    @Override
    public boolean deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            return false;
        }
        jobRepository.deleteById(id);
        return true;
    }
}