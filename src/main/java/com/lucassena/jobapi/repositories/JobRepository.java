package com.lucassena.jobapi.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.lucassena.jobapi.entities.Job;


public interface JobRepository extends JpaRepository <Job, Long>{
  
}
