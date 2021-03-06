/**
 * @author Anderson F.
 * Description: Class CompanyDAO allows to handle computer table  
 * */

package com.excilys.formation.project.persistence.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.project.models.Computer;
import com.excilys.formation.project.persistence.*;

@Repository
public class ComputerDAO implements IComputerDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ICompanyDAO companyDAO;
	
	@Autowired
	private  SessionFactory factory;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// method computers: list of computer
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.persistence.ComputerInterface#computers()
	 */

	public ComputerDAO() {
	}

	@Override
	@Transactional
	public List<Computer> computers() {
	
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Computer order by name ");
		List<Computer> computers = query.list();
		return computers;

	}

	// Details of computer with his ID
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.persistence.ComputerInterface#details(java
	 * .lang.Long)
	 */
	@Override
	@Transactional
	public Computer details(Long id) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("SELECT cmp FROM Computer cmp left outer join cmp.company as company where cmp.id= :id");
		
		query.setLong("id", id);
		
		Computer computer = (Computer) query.uniqueResult();

		return computer;

	}

	// Creation and insertion of computer in database
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.persistence.ComputerInterface#create(java
	 * .lang.Long, java.lang.String, java.util.Date, java.util.Date)
	 */

	@Override
	@Transactional
	public void create(Computer computer) {

		Session session = factory.getCurrentSession();
		session.save(computer);
	}

	// mettre à jour les champs d'un ordinateur de la table computer
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.persistence.ComputerInterface#update(java
	 * .lang.Long, java.lang.Long, java.lang.String, java.util.Date,
	 * java.util.Date)
	 */

	@Override
	//@Transactional
	public void update(Long id, Long cid, String name, Date date, Date date2) {
		String query = null;

		if (id != null && cid != null && name != null && date != null
				&& date2 != null) {
			query = "UPDATE computer set company_id= ?, name=?, introduced=?, discontinued=? where id=?";
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(query, new Object[] { id, name, date, date2,
					cid });
		}

	}

	// delete line of computer table
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.persistence.ComputerInterface#delete(java
	 * .lang.Long, java.sql.Connection)
	 */
	@Override
	@Transactional
	public void delete(Long id) {

		Session session = factory.getCurrentSession();
		Query query = session
				.createQuery("delete Computer where id = :id");
		query.setParameter("id", id);

		query.executeUpdate();

	}

	// Select with limit and offset
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.persistence.ComputerInterface#pages(int,
	 * int, java.lang.String)
	 */

	@Override
	@Transactional
	public List<Computer> pages(int limit, int offset, String search, String sort, String order) {
		Session session = factory.getCurrentSession();
		Query query = null;

		if (search == null) {
			query = session.createQuery("FROM Computer ORDER BY "+order+" "+sort);
			query.setFirstResult(offset);
			query.setMaxResults(limit);

		} else {
			query = session.createQuery("FROM Computer WHERE name LIKE :search ORDER BY "+order+" "+sort);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			query.setParameter("search", search+"%");

		}

		List<Computer> computers = query.list();


		return computers;
	}

	// number of elements
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.persistence.ComputerInterface#count(java
	 * .lang.String)
	 */

	@Override
	@Transactional
	public Long count(String search) {
		Session session = factory.getCurrentSession();
		Query query = null;

		Long count;

		if (search != null) {
			query = session.createQuery("SELECT count(C.name) FROM Computer C WHERE name LIKE :search ");
			query.setParameter("search", search+"%");
			count = (Long) query.uniqueResult();
		} else {
			query = session.createQuery("SELECT count(C.name) FROM Computer C");
			count = (Long) query.uniqueResult();
		}

		return count;
	}

	// find computer's id by company's id
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.persistence.ComputerInterface#findByCompanyId
	 * (java.lang.Long, java.sql.Connection)
	 */

	@Override
	@Transactional
	public List<Long> findByCompanyId(Long id) {

		
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("Select C.id from Computer C where company_id= :id ");
		query.setParameter("id", id);
		
		List<Long> liste = query.list();
		
		return liste;
	}

}
