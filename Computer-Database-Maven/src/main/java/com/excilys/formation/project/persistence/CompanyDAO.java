/**
 * @author Anderson F.
 * Description: Class CompanyDAO allows to handle company table  
 * */

package com.excilys.formation.project.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.beans.Computer;

@Repository
public class CompanyDAO implements ICompanyDAO {

	@Autowired
	ConnectionDAO connectionDAO;

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

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
	public List<Company> companies() {

		String query = "SELECT id, name FROM company ORDER BY name;";

		ArrayList<Company> companies = new ArrayList<Company>();
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

		for (Map row : rows) {
			Company c = new Company((String) row.get("name"),
					(Long) row.get("id"));

			companies.add(c);
		}

		System.out.println("\n\n/** List of companies **/");
		for (int i = 0; i < companies.size(); i++)
			System.out.println(companies.get(i).getName());

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
	public String findById(Long id) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;
		String query = "SELECT id, name FROM company where id=?;";
		String name = null;

		jdbcTemplate = new JdbcTemplate(dataSource);
		Company company = null;
		company = jdbcTemplate.queryForObject(query, new Object[] { id },
				new CompanyMapper());

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
	public void delete(Long id, Connection connection) {
		int rs = 0;
		Connection cn = null;
		String query = "DELETE FROM company where id= ?";

		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(query, new Object[] { id });
		// try {
		// cn = connection;
		// PreparedStatement ps = cn.prepareStatement(query);
		// ps.setLong(1, id);
		// rs = ps.executeUpdate();
		// System.out.println(" Company deleted ! ");
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		//
		// }
	}

}
