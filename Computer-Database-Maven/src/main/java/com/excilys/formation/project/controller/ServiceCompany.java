package com.excilys.formation.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.project.models.Company;
import com.excilys.formation.project.persistence.ICompanyDAO;


@Service
public class ServiceCompany implements IServiceCompany{
	
	List<Company> companies = null;
	
	@Autowired
	private ICompanyDAO companyDAO;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.formation.project.service.ServiceInterface#companies()
	 */
	@Override
	public List<Company> companies() {
		//List<Company> companies = null;
		companies = companyDAO.companies();
		return companies;
	}

}
