package com.lucassena.jobapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucassena.jobapi.entities.Company;
import com.lucassena.jobapi.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Override
    public Company createCompany(Company company) {
        return repository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> companyOptional = repository.findById(id);
        if (companyOptional.isEmpty()) {
            return null;
        }
        return companyOptional.get();
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> companyOptional = repository.findById(id);
        if (companyOptional.isEmpty()) {
            return false;
        }
        updateCompanyAttributes(companyOptional.get(), company);
        repository.save(companyOptional.get());
        return true;
    }

    private void updateCompanyAttributes(Company existingCompany, Company newCompany) {
        existingCompany.setName(newCompany.getName());
        existingCompany.setDescription(newCompany.getDescription());

        existingCompany.getJobs().clear();
        if (newCompany.getJobs() != null) {
            existingCompany.getJobs().addAll(newCompany.getJobs());
        }
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

}
