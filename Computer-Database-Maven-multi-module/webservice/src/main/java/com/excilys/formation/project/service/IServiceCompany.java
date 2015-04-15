/**
 * @author Anderson F.
 * Company Service Interface content CRUD methods for Company Table
 */

package com.excilys.formation.project.service;

import java.util.List;

import com.excilys.formation.project.models.Company;

public interface IServiceCompany {
	/**
	 * Recover list of companies
	 * @return List<Company>
	 */
	public abstract List<Company> companies();

}
