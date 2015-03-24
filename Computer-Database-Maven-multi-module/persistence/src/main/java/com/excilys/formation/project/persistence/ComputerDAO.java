/**
 * @author Anderson F.
 * Description: Class CompanyDAO allows to handle computer table  
 * */

package com.excilys.formation.project.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.project.models.Company;
import com.excilys.formation.project.models.Computer;

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

	private ComputerDAO() {
	}

	@Override
	public List<Computer> computers() {
	
		Session session = factory.openSession();
		Query query = session.createQuery("from Computer order by name ");
		List<Computer> computers = query.list();
		
		System.out.println("/** List of computer **/");
		for (int i = 0; i < computers.size(); i++)
			System.out.println(computers.get(i).getName());

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
	public void details(Long id) {
		String query = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id,  cc.name FROM computer As c, company  As cc where c.id=? and  c.company_id=cc.id";

		jdbcTemplate = new JdbcTemplate(dataSource);
		Computer computer = jdbcTemplate.queryForObject(query,
				new Object[] { id }, new ComputerMapper());

		System.out.println(computer.toString());

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
	public void create(Long cid, String name, Date date, Date date2) {

		String query = "INSERT INTO computer set company_id= ?, name=?, introduced=?, discontinued=?";
		

		jdbcTemplate = new JdbcTemplate(dataSource);
		if (cid != null)

			jdbcTemplate.update(query, new Object[] { cid, name, date, date2 });
		else
			jdbcTemplate.update(query, new Object[] { null, name, date, date2 });

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
	public void delete(Long id) {

		String query = "DELETE FROM computer where id= ?";

		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(query, new Object[] { id });

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
	public List<Computer> pages(int limit, int offset, String search) {
		String query = null;

		ArrayList<Computer> computers = new ArrayList<Computer>();

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = null;
		if (search == null) {
			query = "SELECT c.company_id, c.discontinued, c.introduced, c.name, c.id FROM computer As c ORDER BY c.name LIMIT ? OFFSET ?;";
			rows = jdbcTemplate.queryForList(query, new Object[] { limit,
					offset });
		} else {
			query = "SELECT c.company_id, c.discontinued, c.introduced, c.name, c.id FROM computer As c WHERE c.name LIKE ? ORDER BY c.name LIMIT ? OFFSET ?";
			rows = jdbcTemplate.queryForList(query, new Object[] {
					search + "%", limit, offset });
		}

		for (Map row : rows) {
			Computer c = new Computer();

			if (row.get("company_id") != null)
				c.setCompany(new Company(companyDAO.findById((long) row
						.get("company_id")), (long) row.get("company_id")));
			else
				c.setCompany(new Company(null, null));
			c.setDiscontinued((Date) row.get("discontinued"));
			c.setIntroduced((Date) row.get("introduced"));
			c.setName((String) row.get("name"));
			c.setId((Long) row.get("id"));

			computers.add(c);
		}

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
	public int count(String search) {

		String query = null;

		int count;

		if (search != null) {
			query = "SELECT count(name) FROM computer As c WHERE name LIKE ? ";
			count = jdbcTemplate.queryForObject(query, new Object[] { search
					+ "%" }, Integer.class);
		} else {
			query = "SELECT count(*) FROM computer As c";
			count = jdbcTemplate.queryForObject(query, Integer.class);
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
	public ArrayList<Long> findByCompanyId(Long id) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = "SELECT id FROM computer where company_id=?;";
		ArrayList<Long> liste = new ArrayList<Long>();

		try {

			ps = dataSource.getConnection().prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				liste.add(rs.getLong(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Request error");
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				System.err.println("Sockets don't close");
			}
		}
		return liste;

	}

}
