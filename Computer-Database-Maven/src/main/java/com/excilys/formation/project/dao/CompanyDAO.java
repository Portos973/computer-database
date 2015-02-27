/**
 * @author Anderson F.
 * Description: Class CompanyDAO allows to handle company table  
 * */

package com.excilys.formation.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.beans.Computer;

public class CompanyDAO {
	private ConnectionDAO connectionDAO;

	public CompanyDAO(ConnectionDAO connectionDAO) {
		this.connectionDAO = connectionDAO;
	}

	// Method companies: list of companies
	public List<Company> companies() {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = null;

		ArrayList<Company> liste = new ArrayList<Company>();

		try {

			cn = connectionDAO.getConnection();
			stmt = cn.createStatement();
			rs = stmt.executeQuery("SELECT id, name FROM company;");
			while (rs.next()) {
				Company c = new Company(rs.getString(2),rs.getLong(1));

				liste.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Request error");
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (stmt != null)
					stmt.close();

				if (cn != null)
					cn.close();

			} catch (SQLException e) {
				System.err.println("Sockets don't close");
			}
		}

		System.out.println("\n\n/** List of companies **/");
		for (int i = 0; i < liste.size(); i++)
			System.out.println(liste.get(i).getName());
		
		return liste;

	}
	
	public String findById(Long id){
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;
		String query="SELECT id, name FROM company where id=?;";
		String name=null;
		
		try {

			cn = connectionDAO.getConnection();
			ps = cn.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString(2);

				//liste.add(c);
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

				if (cn != null)
					cn.close();

			} catch (SQLException e) {
				System.err.println("Sockets don't close");
			}
		}
		return name;
	}

}
