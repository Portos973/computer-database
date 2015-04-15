/**
 * @author Anderson F.
 * Description: Class CompanyDAO allows to handle company table  
 * */

package com.excilys.formation.project.persistence.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.project.models.Company;
import com.excilys.formation.project.persistence.*;

@Repository
public class CompanyDAO implements ICompanyDAO {

	@Autowired
	private SessionFactory factory;

	// Method companies: list of companies
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.persistence.CompanyInterface#companies()
	 */
	@Override
	@Transactional
	public List<Company> companies() {

		// factory= new Configuration().configure().buildSessionFactory(new
		// StandardServiceRegistryBuilder().build());

		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Company order by name ");
		List<Company> companies = query.list();

		return companies;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.persistence.CompanyInterface#findById(java
	 * .lang.Long)
	 */
	@Override
	@Transactional
	public String findById(Long id) {

		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Company where id = :id ");
		query.setParameter("id", id);
		Company company = (Company) query.uniqueResult();

		return company.getName();
	}

	// delete line of company table
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.project.persistence.CompanyInterface#delete(java
	 * .lang.Long, java.sql.Connection)
	 */
	@Override
	@Transactional
	public void delete(Long id) {

		Session session = factory.getCurrentSession();
		Query query = session
				.createQuery("delete Company where where id = :id");
		query.setParameter("id", id);

		query.executeUpdate();

	}

}
