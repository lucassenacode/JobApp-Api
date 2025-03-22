package com.lucassena.jobapi.services;

import java.util.List;

import com.lucassena.jobapi.entities.Job;

public interface JobService {

    Job create(Job job);

    List<Job> getAll();

    Job getById(Long id);

    boolean update(Long id, Job job);

    boolean delete(Long id);
}