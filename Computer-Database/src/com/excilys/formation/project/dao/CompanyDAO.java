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

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.beans.Computer;

public class CompanyDAO {
	private ConnectionDAO connectionDAO;

	public CompanyDAO(ConnectionDAO connectionDAO) {
		this.connectionDAO = connectionDAO;
	}

	// Method companies: list of companies
	public ArrayList<Company> companies() {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = null;

		ArrayList<Company> liste = new ArrayList<Company>();

		try {

			cn = connectionDAO.getConnection();
			stmt = cn.createStatement();
			rs = stmt.executeQuery("SELECT id, name FROM company;");
			while (rs.next()) {
				Company c = new Company();
				c.setName(rs.getString(2));
				c.setId(rs.getLong(1));

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

}
