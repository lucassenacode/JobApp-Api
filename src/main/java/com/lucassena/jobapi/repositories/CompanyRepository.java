package com.lucassena.jobapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucassena.jobapi.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
