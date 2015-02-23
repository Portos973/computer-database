/**
 * @author Anderson F.
 * Description: Classe CompanyDAO permet de manipuler la table computer 
 * */

package com.excilys.formation.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.excilys.formation.project.beans.Computer;

public class ComputerDAO {

	private ConnectionDAO connectionDAO;

	public ComputerDAO(ConnectionDAO connectionDAO) {
		this.connectionDAO = connectionDAO;
	}

	// méthode computers: liste les ordinateurs
	public void computers() {
		// PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = null;

		ArrayList<Computer> liste = new ArrayList<Computer>();

		try {

			cn = connectionDAO.getConnection();
			stmt = cn.createStatement();
			rs = stmt
					.executeQuery("SELECT company_id, discontinued, introduced, name, id FROM computer;");
			while (rs.next()) {
				Computer c = new Computer();
				c.setCompany_id(new Long(rs.getLong(1)));
				c.setDiscontinued(rs.getTimestamp(2));
				c.setIntroduced(rs.getTimestamp(3));
				c.setName(rs.getString(4));
				c.setId(rs.getLong(5));

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

		System.out.println("/** Liste des ordinateurs **/");
		for (int i = 0; i < liste.size(); i++)
			System.out.println(liste.get(i).getName());

	}

	// Détails d'un ordinateur à partir de son ID
	public void details(Long id) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = null;

		ArrayList<Computer> liste = new ArrayList<Computer>();
		Computer c = new Computer();

		try {

			cn = connectionDAO.getConnection();
			stmt = cn.createStatement();
			rs = stmt
					.executeQuery("SELECT company_id, discontinued, introduced, name, id FROM computer where id="
							+ id + ";");
			while (rs.next()) {

				c.setCompany_id(new Long(rs.getLong(1)));
				c.setDiscontinued(rs.getTimestamp(2));
				c.setIntroduced(rs.getTimestamp(3));
				c.setName(rs.getString(4));
				c.setId(rs.getLong(5));

				// liste.add(c);
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

		System.out.println(c.toString());

	}

	// Création et insertion d'un ordinateur dans la base de données
	public void create(Long cid, String name, String date) {
		int rs = 0;
		Connection cn = null;
		String query = "INSERT INTO computer set company_id= ?, name=?, introduced=?";

		try {
			cn = connectionDAO.getConnection();
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setLong(1, cid);
			ps.setString(2, name);
			ps.setTimestamp(3, Timestamp.valueOf(date));
			rs = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}
		}

	}

	
	// mettre à jour les champs company_id, name et date d'un ordinateur de la table computer
	public void update(Long id,Long cid, String name, String date) {
		int rs = 0;
		Connection cn = null;
		String query = "UPDATE computer set company_id= ?, name=?, introduced=? where id=?";

		try {
			cn = connectionDAO.getConnection();
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setLong(1, cid);
			ps.setString(2, name);
			ps.setTimestamp(3, Timestamp.valueOf(date));
			ps.setLong(4,id );
			rs = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}
		}


	}
	
	// mettre à jour les champs company_id et name d'un ordinateur de la table computer
	public void update(Long id, Long cid, String name) {
		int rs = 0;
		Connection cn = null;
		String query = "UPDATE computer set company_id= ?, name=? where id=?";

		try {
			cn = connectionDAO.getConnection();
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setLong(1, cid);
			ps.setString(2, name);
			rs = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}
		}


	}
	
	// mettre à jour les champs company_id et date d'un ordinateur de la table computer
	public void updated(Long id, Long cid, String date) {
		int rs = 0;
		Connection cn = null;
		String query = "UPDATE computer set company_id= ?, date=? where id=?";

		try {
			cn = connectionDAO.getConnection();
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setLong(1, cid);
			ps.setTimestamp(2, Timestamp.valueOf(date));
			rs = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}
		}


	}

	
	// supprimer un champs de la table computer
	public void delete(Long id) {
		int rs = 0;
		Connection cn = null;
		String query = "DELETE FROM computer where id= ?";

		try {
			cn = connectionDAO.getConnection();
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}
		}
	}
	

}
