package com.excilys.formation.project.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.excilys.formation.project.beans.Computer;

public interface IComputer {

	// method computers: list of computer
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#computers()
	 */
	public abstract List<Computer> computers();

	// Details of computer with his ID
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#details(java.lang.Long)
	 */
	public abstract void details(Long id);

	// Creation and insertion of computer in database
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#create(java.lang.Long, java.lang.String, java.util.Date, java.util.Date)
	 */
	public abstract void create(Long cid, String name, Date date, Date date2);

	// mettre à jour les champs d'un ordinateur de la table computer
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#update(java.lang.Long, java.lang.Long, java.lang.String, java.util.Date, java.util.Date)
	 */
	public abstract void update(Long id, Long cid, String name, Date date,
			Date date2);

	// delete line of computer table
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#delete(java.lang.Long, java.sql.Connection)
	 */
	public abstract void delete(Long id, Connection connection);

	// Method of search
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#search(java.lang.String)
	 */
	//public abstract List<Computer> search(String name, Connection connection);

	// Select with limit and offset
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#pages(int, int, java.lang.String)
	 */
	public abstract List<Computer> pages(int limit, int offset, String search);

	// number of elements
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#count(java.lang.String)
	 */
	public abstract int count(String search);

	// find computer's id by company's id
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#findByCompanyId(java.lang.Long, java.sql.Connection)
	 */
	public abstract ArrayList<Long> findByCompanyId(Long id,Connection connection);


}