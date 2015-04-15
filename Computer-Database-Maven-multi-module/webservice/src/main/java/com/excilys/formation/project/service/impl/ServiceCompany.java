package com.excilys.formation.project.service.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.project.models.Company;
import com.excilys.formation.project.persistence.ICompanyDAO;
import com.excilys.formation.project.service.*;


@Path("/companies")
//@RestController("/companies")
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
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
//	@RequestMapping(value="/all",produces = MediaType.APPLICATION_JSON,method=RequestMethod.GET)
	public List<Company> companies() {
		System.out.println("companies method");
		companies = companyDAO.companies();
		return companies;
	}

}
