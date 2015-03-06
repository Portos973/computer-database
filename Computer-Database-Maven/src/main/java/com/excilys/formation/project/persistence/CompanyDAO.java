/**
 * @author Anderson F.
 * Description: Class CompanyDAO allows to handle company table  
 * */

package com.excilys.formation.project.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.beans.Computer;

public enum CompanyDAO implements ICompany {
	INSTANCE;

	// Method companies: list of companies
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.CompanyInterface#companies()
	 */
	@Override
	public List<Company> companies() {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = null;

		ArrayList<Company> liste = new ArrayList<Company>();

		try {

			stmt = ConnectionDAO.INSTANCE.getConnection().createStatement();
			rs = stmt.executeQuery("SELECT id, name FROM company ORDER BY name;");
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

				ConnectionDAO.INSTANCE.closeConnection();

			} catch (SQLException e) {
				System.err.println("Sockets don't close");
			}
		}

		System.out.println("\n\n/** List of companies **/");
		for (int i = 0; i < liste.size(); i++)
			System.out.println(liste.get(i).getName());
		
		return liste;

	}
	
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.CompanyInterface#findById(java.lang.Long)
	 */
	@Override
	public String findById(Long id){
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;
		String query="SELECT id, name FROM company where id=?;";
		String name=null;
		
		try {

			cn = ConnectionDAO.INSTANCE.connectionPool.getConnection();
			ps = cn.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString(2);

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
	
	// delete line of company table
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.CompanyInterface#delete(java.lang.Long, java.sql.Connection)
	 */
	@Override
	public void delete(Long id,Connection connection) {
		int rs = 0;
		Connection cn = null;
		String query = "DELETE FROM company where id= ?";

		try {
			cn = connection;
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeUpdate();
			System.out.println(" Company deleted ! ");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
}
