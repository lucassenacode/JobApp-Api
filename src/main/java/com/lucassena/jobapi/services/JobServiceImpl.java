package com.lucassena.jobapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucassena.jobapi.entities.Job;
import com.lucassena.jobapi.repositories.JobRepository;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository repository;

    @Override
    public Job create(Job job) {
        return repository.save(job);
    }

    @Override
    public List<Job> getAll() {
        return repository.findAll();
    }

    @Override
    public Job getById(Long id) {
        Optional<Job> jobOptional = repository.findById(id);
        if (jobOptional.isEmpty()) {
            return null;
        }
        return jobOptional.get();
    }

    @Override
    public boolean update(Long id, Job job) {
        Optional<Job> jobOptional = repository.findById(id); 
        if (jobOptional.isEmpty()) {
            return false;
        }

        updateJob(jobOptional, job);
        repository.save(jobOptional.get());
        return true;
    }

    private void updateJob(Optional<Job> jobOptional, Job job) {
        jobOptional.get().setTitle(job.getTitle());
        jobOptional.get().setDescription(job.getDescription());
        jobOptional.get().setMinSalary(job.getMinSalary());
        jobOptional.get().setMaxSalary(job.getMaxSalary());
        jobOptional.get().setLoaction(job.getLoaction());
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}