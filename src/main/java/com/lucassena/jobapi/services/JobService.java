package com.lucassena.jobapi.services;

import java.util.List;

import com.lucassena.jobapi.entities.Job;

public interface JobService {

    boolean createJob(Job job);

    List<Job> getAllJobs();

    Job getJobById(Long id);

    boolean updateJob(Long id, Job job);

    boolean deleteJob(Long id);
}