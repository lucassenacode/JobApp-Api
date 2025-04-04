package com.lucassena.jobapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucassena.jobapi.entities.Company;
import com.lucassena.jobapi.services.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @PostMapping
  public ResponseEntity<String> createCompany(@RequestBody Company company) {
    companyService.createCompany(company);
    return new ResponseEntity<>("Empresa adicionada com sucesso", HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Company>> getAllCompanies() {
    List<Company> companies = companyService.getAllCompanies();
    if (companies.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return ResponseEntity.ok(companies);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
    Company company = companyService.getCompanyById(id);
    if (company != null) {
      return ResponseEntity.ok(company);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
    boolean updated = companyService.updateCompany(id, company);
    if (updated) {
      return ResponseEntity.ok("Empresa Atualizada com sucesso");
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
    boolean deleted = companyService.deleteCompany(id);
    if (deleted) {
      return ResponseEntity.ok("Empresa deletada com sucesso");
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
