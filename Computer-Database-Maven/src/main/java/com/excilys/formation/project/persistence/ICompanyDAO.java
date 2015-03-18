package com.excilys.formation.project.persistence;

import java.sql.Connection;
import java.util.List;

import com.excilys.formation.project.beans.Company;

public interface ICompanyDAO {

	// Method companies: list of companies
	public abstract List<Company> companies();

	public abstract String findById(Long id);

	// delete line of company table
	public abstract void delete(Long id, Connection connection);




	

}