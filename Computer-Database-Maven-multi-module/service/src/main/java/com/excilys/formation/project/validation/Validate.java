package com.excilys.formation.project.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.project.service.IServiceCompany;
import com.excilys.formation.project.service.IServiceComputer;
import com.excilys.formation.project.models.Company;
import com.excilys.formation.project.dto.ComputerDTO;


@Component
public class Validate {
	@Autowired
	IServiceCompany serviceCompany;
	
	@Autowired
	IServiceComputer serviceComputer;
	
	
	public  boolean checkCompanyId(Long cid) {
		boolean bool = false;
		List<Company> companies = new ArrayList<Company>();
		companies = serviceCompany.companies();

		for (int i = 0; i < companies.size(); i++)
			if (companies.get(i).getId() == cid)
				bool = true;

		return bool;
	}
	
	public boolean checkId(Long id) {
		boolean bool = false;
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		computers = serviceComputer.computers();

		for (int i = 0; i < computers.size(); i++)
			if (computers.get(i).getId() == id)
				bool = true;

		System.out.println("======>>> "+bool);
		return bool;
	}

}
