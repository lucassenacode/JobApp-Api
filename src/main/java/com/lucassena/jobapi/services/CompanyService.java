package com.lucassena.jobapi.services;

import java.util.List;

import com.lucassena.jobapi.entities.Company;

public interface CompanyService {

  Company createCompany(Company company);

  List<Company> getAllCompanies();

  Company getCompanyById(Long id);

  boolean updateCompany(Long id, Company company);

  boolean deleteCompany(Long id);
}
