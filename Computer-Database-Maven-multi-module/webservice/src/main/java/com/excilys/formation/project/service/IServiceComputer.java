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
	 * List of computers
	 * @return List<ComputerDTO>
	 */
	public abstract List<ComputerDTO> computers();

	/**
	 * Detail of a computer
	 * @param Long
	 */
	public abstract ComputerDTO details(Long id);

	/**
	 * Create a computer
	 * @param Computer
	 */
	public abstract void create(Computer computer);

	/**
	 * Update a computer
	 * @param Computer
	 */
	public abstract void update(Computer comp);

	/**
	 * Delete a computer
	 * @param Long
	 */
	public abstract void delete(Long id);

	/**
	 * Pagination 
	 * @param Pages
	 * @return List<ComputerDTO>
	 */
	public abstract List<ComputerDTO> pages(Pages page);

	/**
	 * Return the number of computer
	 * @param String
	 * @return Long
	 */
	public abstract Long count(String search);

	/**
	 * Convert Computer object to DTO
	 * @param Computer
	 * @returnComputerDTO
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