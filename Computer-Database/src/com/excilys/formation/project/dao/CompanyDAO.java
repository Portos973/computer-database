/**
 * @author Anderson F.
 * Description: Classe CompanyDAO permet de manipuler la table company 
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

	// méthode companies: liste les entreprises
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
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (stmt != null)
					stmt.close();

				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}
		}

		System.out.println("\n\n/** Liste des Entreprises **/");
		for (int i = 0; i < liste.size(); i++)
			System.out.println(liste.get(i).getName());
		
		return liste;

	}

}
