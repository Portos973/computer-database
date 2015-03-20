/**
 * @author Anderson F.
 * Description: Class CompanyDAO allows to handle company table  
 * */

package com.excilys.formation.project.persistence;

import java.util.List;
import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.project.models.Company;

@Repository
public class CompanyDAO implements ICompanyDAO {
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private  SessionFactory factory;
	

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

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

		
//		factory= new Configuration().configure().buildSessionFactory(new StandardServiceRegistryBuilder().build());
		
		
		
		Session session = factory.openSession();
		Query query = session.createQuery("from Company order by name ");
		List<Company> companies = query.list();
		
//		String query = "SELECT id, name FROM company ORDER BY name;";
//		List<Company> companies = new ArrayList<Company>();
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
//
//		for (Map row : rows) {
//			Company c = new Company((String) row.get("name"),
//					(Long) row.get("id"));
//
//			companies.add(c);
//		}
//
//		System.out.println("\n\n/** List of companies **/");
//		for (int i = 0; i < companies.size(); i++)
//			System.out.println(companies.get(i).getName());

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
//		String query = "SELECT id, name FROM company where id=?;";
//
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		Company company = null;
//		company = jdbcTemplate.queryForObject(query, new Object[] { id },
//				new CompanyMapper());
		
		Session session = factory.openSession();
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
//		String query = "DELETE FROM company where id= ?";
//
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.update(query, new Object[] { id });
		
		Session session = factory.openSession();
		Query query = session.createQuery("delete Company where where id = :id");
		query.setParameter("id", id);
		
		query.executeUpdate();

	}

}
