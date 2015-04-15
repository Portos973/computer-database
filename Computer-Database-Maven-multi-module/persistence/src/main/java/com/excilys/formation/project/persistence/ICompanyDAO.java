/**
 * @author Anderson F.
 * Description: CompanyDAO Interface
 */

package com.excilys.formation.project.persistence;

import java.util.List;

import com.excilys.formation.project.models.Company;

public interface ICompanyDAO {

	
	
	/**
	 * Recover list of companies
	 * @return List<Company>
	 */
	public abstract List<Company> companies();

	public abstract String findById(Long id);

	/**
	 * Delete one company 
	 * @param id
	 */
	public abstract void delete(Long id);




	

}