package com.excilys.formation.project.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.project.beans.Company;

public class CompanyMapper implements RowMapper<Company>{
	
	@Override
	public Company mapRow(ResultSet rs, int line) throws SQLException {
		Company company = new Company(rs.getString(2),rs.getLong(1));
	    
	    return company;
	  }

}
