/**
 * @author Anderson F.
 * Computer Service Interface content CRUD methods for Computer Table
 */

package com.excilys.formation.project.service;


import java.util.List;

import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.models.Computer;
import com.excilys.formation.project.service.Pages;

public interface IServiceComputer {

	/**
	 * @return List of computers
	 */
	public abstract List<ComputerDTO> computers();

	/**
	 * @param Computer ID
	 */
	public abstract void details(Long id);

	/**
	 * Create a computer
	 * @param computer
	 */
	public abstract void create(Computer computer);

	/**
	 * Update a computer
	 * @param computer
	 */
	public abstract void update(Computer comp);

	/**
	 * Delete a computer
	 * @param computer ID
	 */
	public abstract void delete(Long id);

	/**
	 * Pagination 
	 * @param Pages
	 * @return list of computer DTO
	 */
	public abstract List<ComputerDTO> pages(Pages page);

	/**
	 * Return the number of computer
	 * @param String
	 * @return Return the number of computer
	 */
	public abstract Long count(String search);

	/**
	 * Convert Computer object to DTO
	 * @param Computer
	 * @return ComputerDTO
	 */
	public abstract ComputerDTO fromComputerToDTO(Computer computer);

	/**
	 * Convert DTO to Computer object
	 * @param ComputerDTO
	 * @return ComputerDTO
	 */
	public abstract Computer fromDTOToComputer(ComputerDTO dto);

	/**
	 * Delete company use transaction
	 * @param Long
	 */
	public abstract void deleteCompany(Long id);

}