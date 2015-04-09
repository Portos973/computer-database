package com.excilys.formation.project.service;


import java.util.List;

import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.models.Computer;
import com.excilys.formation.project.service.Pages;

public interface IServiceComputer {

	public abstract List<ComputerDTO> computers();

	public abstract ComputerDTO details(Long id);

	public abstract void create(Computer computer);

	public abstract void update(Computer comp);

	public abstract void delete(Long id);

	public abstract List<ComputerDTO> pages(Pages page);

	public abstract Long count(String search);

	// Method for convert Computer object to DTO
	public abstract ComputerDTO fromComputerToDTO(Computer computer);

	// Method for convert DTO to Computer object
	public abstract Computer fromDTOToComputer(ComputerDTO dto);

	// Deleted company use transaction
	public abstract void deleteCompany(Long id);

}