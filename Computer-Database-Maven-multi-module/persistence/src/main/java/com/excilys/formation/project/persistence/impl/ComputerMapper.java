package com.excilys.formation.project.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.project.models.Company;
import com.excilys.formation.project.models.Computer;

public class ComputerMapper implements RowMapper<Computer>{
	
	@Override
	public Computer mapRow(ResultSet rs, int line) throws SQLException {
	    Computer computer = new Computer();
	    
	    computer.setId(rs.getLong(1));
	    computer.setName(rs.getString(2));
	    computer.setIntroduced(rs.getDate(3));
	    computer.setIntroduced(rs.getDate(4));
	    computer.setCompany(new Company(rs.getString(6),rs.getLong(5)));
	    return computer;
	  }

}
