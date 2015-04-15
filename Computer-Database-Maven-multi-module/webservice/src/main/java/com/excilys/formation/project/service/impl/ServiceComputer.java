package com.excilys.formation.project.service.impl;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.models.Company;
import com.excilys.formation.project.models.Computer;
import com.excilys.formation.project.persistence.ICompanyDAO;
import com.excilys.formation.project.persistence.IComputerDAO;
import com.excilys.formation.project.utils.Utils;
import com.excilys.formation.project.validation.Validate;
import com.excilys.formation.project.service.*;

@Path("/computers")
public class ServiceComputer implements IServiceComputer {
	private static final Logger logger = LoggerFactory
			.getLogger(ServiceComputer.class);

	List<Company> companies = null;
	List<Computer> computers = null;
	Connection cnx = null;

	@Autowired
	Validate validate;

	@Autowired
	private IComputerDAO computerDAO;

	@Autowired
	private ICompanyDAO companyDAO;

	public ServiceComputer() {
		companies = new ArrayList<Company>();
		computers = new ArrayList<Computer>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.formation.project.service.ServiceInterface#computers()
	 */
	@Override
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComputerDTO> computers() {
		computers = computerDAO.computers();
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		for (int i = 0; i < computers.size(); i++)
			computersDTO.add(this.fromComputerToDTO(computers.get(i)));

		return computersDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#details(java.lang
	 * .Long)
	 */
	@Override
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO details(@PathParam("id") Long id) {

		ComputerDTO computerDTO = this.fromComputerToDTO(computerDAO
				.details(id));

		return computerDTO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#create(com.excilys
	 * .formation.project.beans.Computer)
	 */
	@Override
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(Computer computer) {


			computerDAO.create(computer);
			System.out.println("\n");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#update(com.excilys
	 * .formation.project.beans.Computer)
	 */
	@Override
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Computer comp) {
		System.out.println(comp.toString());
		if (comp.getId() > 0) {
			computerDAO.update(comp.getId(), comp.getCompany().getId(),
					comp.getName(), comp.getIntroduced(),
					comp.getDiscontinued());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#delete(java.lang
	 * .Long)
	 */
	@Override
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		computerDAO.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#pages(com.excilys
	 * .formation.project.service.Pages)
	 */
	@Override
	public List<ComputerDTO> pages(Pages page) {

		computers = computerDAO.pages(page.getLimit(), page.getOffset(),
				page.getSearch(), page.getOrder(), page.getSort());

		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		for (int i = 0; i < computers.size(); i++)
			computersDTO.add(this.fromComputerToDTO(computers.get(i)));

		return computersDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#count(java.lang
	 * .String)
	 */
	@Override
	public Long count(String search) {
		Long count;

		count = computerDAO.count(search);

		return count;
	}

	// Method for convert Computer object to DTO
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#fromComputerToDTO
	 * (com.excilys.formation.project.beans.Computer)
	 */
	@Override
	public ComputerDTO fromComputerToDTO(Computer computer) {
		String introduced = null;
		String discontinued = null;

		if (computer.getIntroduced() != null) {
			introduced = Utils.formatDate(computer.getIntroduced());
		}

		if (computer.getDiscontinued() != null) {
			discontinued = Utils.formatDate(computer.getDiscontinued());
		}

		if (computer.getCompany() != null) {
			if (computer.getCompany().getId() != null) {
				return new ComputerDTO(computer.getId(), computer.getName(),
						introduced, discontinued,
						computer.getCompany().getId(), computer.getCompany()
								.getName());

			} else {
				return new ComputerDTO(computer.getId(), computer.getName(),
						introduced, discontinued, 0, null);
			}
		} else {
			return new ComputerDTO(computer.getId(), computer.getName(),
					introduced, discontinued, 0, null);
		}
	}

	// Method for convert DTO to Computer object
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#fromDTOToComputer
	 * (com.excilys.formation.project.dto.ComputerDTO)
	 */
	@Override
	public Computer fromDTOToComputer(ComputerDTO dto) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date introduced = null;
		Date discontinued = null;

		try {
			if (dto.getIntroduced() != null)
				introduced = formatter.parse(dto.getIntroduced());
			if (dto.getDiscontinued() != null)
				discontinued = formatter.parse(dto.getDiscontinued());
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("Conversion didn't do ");
		}

		Computer computer = new Computer();
		computer.setCompany(new Company(dto.getCompanyName(), dto
				.getCompanyId()));
		computer.setId(dto.getId());
		computer.setName(dto.getName());
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);

		return computer;
	}

	// Deleted company use transaction
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#deleteCompany(
	 * java.lang.Long)
	 */
	@Override
	@Transactional
	public void deleteCompany(Long id) {

		List<Long> list = computerDAO.findByCompanyId(id);

		for (int i = 0; i < list.size(); i++) {
			computerDAO.delete(list.get(i));
		}

		companyDAO.delete(id);

		System.out.println("Done!");

	}
}
