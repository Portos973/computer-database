/**
 * @author Anderson F.
 * Description: ComputerDAO Interface
 */


package com.excilys.formation.project.persistence;

import java.util.Date;
import java.util.List;

import com.excilys.formation.project.models.Computer;

public interface IComputerDAO {

	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#computers()
	 */
	/**
	 * Recover list of computers
	 * @return List<Computer>
	 */
	public abstract List<Computer> computers();

	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#details(java.lang.Long)
	 */
	/**
	 * Detail of a computer
	 * @param id
	 * @return Computer
	 */
	public abstract Computer details(Long id);

	// Creation and insertion of computer in database
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#create(java.lang.Long, java.lang.String, java.util.Date, java.util.Date)
	 */
	/**
	 * Create a computer
	 * @param cid
	 * @param name
	 * @param date
	 * @param date2
	 */
	public abstract void create(Computer computer);

	// mettre à jour les champs d'un ordinateur de la table computer
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#update(java.lang.Long, java.lang.Long, java.lang.String, java.util.Date, java.util.Date)
	 */
	/**
	 * Update a computer
	 * @param id
	 * @param cid
	 * @param name
	 * @param date
	 * @param date2
	 */
	public abstract void update(Long id, Long cid, String name, Date date,
			Date date2);

	// delete line of computer table
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#delete(java.lang.Long, java.sql.Connection)
	 */
	/**
	 * Delete a computer
	 * @param id
	 */
	public abstract void delete(Long id);

	// Select with limit and offset
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#pages(int, int, java.lang.String)
	 */
	/**
	 * Pagination with search
	 * @param limit
	 * @param offset
	 * @param search
	 * @param string2 
	 * @param string 
	 * @return List<Computer>
	 */
	public abstract List<Computer> pages(int limit, int offset, String search, String string, String string2);

	// number of elements
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#count(java.lang.String)
	 */
	/**
	 * Count the number of computer
	 * @param search
	 * @return Long
	 */
	public abstract Long count(String search);

	// find computer's id by company's id
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#findByCompanyId(java.lang.Long, java.sql.Connection)
	 */
	/**
	 * Return list of CompanyID
	 * @param id
	 * @return List<Long> 
	 */
	public abstract List<Long> findByCompanyId(Long id);


}