package com.excilys.formation.project.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.persistence.CompanyDAO;
import com.excilys.formation.project.persistence.ComputerDAO;
import com.excilys.formation.project.persistence.ConnectionDAO;
import com.excilys.formation.project.utils.Utils;

public class Service implements IService {
	private static final Logger logger = LoggerFactory.getLogger(Service.class);

	List<Company> companies = null;
	List<Computer> computers = null;
	Connection cnx = null;

	public Service() throws Exception {
		companies = new ArrayList<Company>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.formation.project.service.ServiceInterface#computers()
	 */
	@Override
	public List<ComputerDTO> computers() {
		List<Computer> computers = null;
		computers = ComputerDAO.INSTANCE.computers();
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		for (int i = 0; i < computers.size(); i++)
			computersDTO.add(this.fromComputerToDTO(computers.get(i)));

		return computersDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.formation.project.service.ServiceInterface#companies()
	 */
	@Override
	public List<Company> companies() {
		List<Company> companies = null;

		companies = CompanyDAO.INSTANCE.companies();
		return companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#details(java.lang
	 * .Long)
	 */
	@Override
	public void details(Long id) {

		ComputerDAO.INSTANCE.details(id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#create(com.excilys
	 * .formation.project.beans.Computer)
	 */
	@Override
	public void create(Computer computer) {

		if (!Utils.checkCompanyId(computer.getCompany().getId())) {

			ComputerDAO.INSTANCE.create(null, computer.getName(),
					computer.getIntroduced(), computer.getDiscontinued());
			System.out.println("\n");

		} else {
			ComputerDAO.INSTANCE.create(computer.getCompany().getId(),
					computer.getName(), computer.getIntroduced(),
					computer.getDiscontinued());
			System.out.println("===========> création faite !!!");

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.service.ServiceInterface#update(com.excilys
	 * .formation.project.beans.Computer)
	 */
	@Override
	public void update(Computer comp) {
		System.out.println(comp.toString());
		if (comp.getId() > 0) {
			ComputerDAO.INSTANCE.update(comp.getId(),
					comp.getCompany().getId(), comp.getName(),
					comp.getIntroduced(), comp.getDiscontinued());
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
	public void delete(Long id) {
		try {
			ComputerDAO.INSTANCE.delete(id, ConnectionDAO.INSTANCE.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnectionDAO.INSTANCE.closeConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

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

		computers = ComputerDAO.INSTANCE.pages(page.getLimit(),
				page.getOffset(), page.getSearch());

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
	public int count(String search) {
		int count = 0;

		count = ComputerDAO.INSTANCE.count(search);

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

		if (computer.getIntroduced() != null)
			introduced = computer.getIntroduced().toString();

		if (computer.getDiscontinued() != null)
			discontinued = computer.getDiscontinued().toString();

		return new ComputerDTO(computer.getId(), computer.getName(),
				introduced, discontinued, computer.getCompany().getId(),
				computer.getCompany().getName());
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
			introduced = formatter.parse(dto.getIntroduced());
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
	public void deleteCompany(Long id) {
		Connection connection = null;

		try {
			;

			ConnectionDAO.INSTANCE.getConnection().setAutoCommit(false);

			List<Long> list = ComputerDAO.INSTANCE.findByCompanyId(id,
					ConnectionDAO.INSTANCE.getConnection());

			for (int i = 0; i < list.size(); i++) {
				ComputerDAO.INSTANCE.delete(list.get(i),
						ConnectionDAO.INSTANCE.getConnection());
			}

			CompanyDAO.INSTANCE.delete(id,
					ConnectionDAO.INSTANCE.getConnection());

			ConnectionDAO.INSTANCE.getConnection().commit();

			System.out.println("Done!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			try {
				ConnectionDAO.INSTANCE.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				logger.error("SQL Error: Rollback didn't do ");
			}

		} finally {

			try {
				if (ConnectionDAO.INSTANCE.getConnection() != null) {
					ConnectionDAO.INSTANCE.getConnection().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("SQL Error: Connection closure didn't do ");
			}

		}

	}
}
