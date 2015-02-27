/**
 * @author Anderson F.
 * Description: Class CompanyDAO allows to handle computer table  
 * */

package com.excilys.formation.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.dto.ComputerDTO;

public class ComputerDAO {

	private ConnectionDAO connectionDAO;

	public ComputerDAO(ConnectionDAO connectionDAO) {
		this.connectionDAO = connectionDAO;
	}

	// method computers: list of computer
	public List<Computer> computers() {
		// PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;

		ArrayList<Computer> liste = new ArrayList<Computer>();
		String query = "SELECT c.company_id, c.discontinued, c.introduced, c.name, c.id FROM computer As c ";

		try {

			cn = connectionDAO.getConnection();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Computer c = new Computer();
				CompanyDAO cc = new CompanyDAO(connectionDAO);
				ComputerDTO cdt = new ComputerDTO();

				c.setCompany(new Company( cc.findById(rs.getLong(1)), rs.getLong(1) ));
				c.setDiscontinued(cdt.formatDate(rs.getString(2)));
				c.setIntroduced(cdt.formatDate(rs.getString(3)));
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

				if (ps != null)
					ps.close();

				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}
		}

		System.out.println("/** List of computer **/");
		for (int i = 0; i < liste.size(); i++)
			System.out.println(liste.get(i).getName());

		return liste;

	}

	// Details of computer with his ID
	public void details(Long id) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;

		ArrayList<Computer> liste = new ArrayList<Computer>();
		Computer c = new Computer();
		String query = "SELECT c.company_id, c.discontinued, c.introduced, c.name, c.id, cc.name FROM computer As c, company As cc where id=?";

		try {

			cn = connectionDAO.getConnection();
			ps = cn.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				c.setCompany(new Company(rs.getString(6), rs.getLong(1)));
				c.setDiscontinued(rs.getString(2));
				c.setIntroduced(rs.getString(3));
				c.setName(rs.getString(4));
				c.setId(rs.getLong(5));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (ps != null)
					ps.close();

				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}
		}

		System.out.println(c.toString());

	}

	// Creation and insertion of computer in database
	public void create(Long cid, String name, String date, String finProd) {
		int rs = 0;
		Connection cn = null;
		String query = "INSERT INTO computer set company_id= ?, name=?, introduced=?, discontinued=?";
		PreparedStatement ps = null;

		try {
			cn = connectionDAO.getConnection();
			ps = cn.prepareStatement(query);
			if (cid != null)
				ps.setLong(1, cid);
			else
				ps.setNull(1, 0);
			ps.setString(2, name);
			ps.setTimestamp(3, Timestamp.valueOf(date + " 00:00:00"));
			ps.setTimestamp(4, Timestamp.valueOf(finProd + " 00:00:00"));
			rs = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}
		}

	}

	// mettre à jour les champs d'un ordinateur de la table computer
	public void update(Long id, Long cid, String name, String date,
			String finProd) {
		int rs = 0;
		Connection cn = null;

		String query = null;

		if (id != null && cid != null && name != null && date != null
				&& finProd != null)
			query = "UPDATE computer set company_id= ?, name=?, introduced=?, discontinued=? where id=?";

		try {
			cn = connectionDAO.getConnection();
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setLong(1, cid);
			ps.setString(2, name);
			ps.setTimestamp(3, Timestamp.valueOf(date));
			ps.setTimestamp(4, Timestamp.valueOf(finProd));
			ps.setLong(5, id);

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

	// delete line of computer table
	public void delete(Long id) {
		int rs = 0;
		Connection cn = null;
		String query = "DELETE FROM computer where id= ?";

		try {
			cn = connectionDAO.getConnection();
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeUpdate();
			System.out.println(" Ordinateur supprimé ! ");

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

	// Method of search
	public List<Computer> search(String name) {
		List<Computer> list = new ArrayList<Computer>();

		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;

		ArrayList<Computer> liste = new ArrayList<Computer>();
		String query = "SELECT c.company_id, c.discontinued, c.introduced, c.name, c.id FROM computer As c WHERE c.name = ? LIMIT 100 OFFSET 0";

		try {

			cn = connectionDAO.getConnection();
			ps = cn.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();

			while (rs.next()) {
				ComputerDTO cdt = new ComputerDTO();

				Computer c = new Computer();
				c.setDiscontinued(cdt.formatDate(rs.getString(3)));
				c.setIntroduced(cdt.formatDate(rs.getString(2)));
				c.setName(rs.getString(4));
				c.setId(rs.getLong(5));
				CompanyDAO cc = new CompanyDAO(connectionDAO);
				c.setCompany(new Company(cc.findById(rs.getLong(1)), rs.getLong(1)));

				liste.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (ps != null)
					ps.close();

				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}
		}

		return liste;
	}

	// Select with limit and offset
	public List<Computer> pages(int limit, int offset) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;

		ArrayList<Computer> liste = new ArrayList<Computer>();
		String query = "SELECT c.company_id, c.discontinued, c.introduced, c.name, c.id FROM computer As c LIMIT ? OFFSET ?;";

		try {

			cn = connectionDAO.getConnection();
			ps = cn.prepareStatement(query);
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			rs = ps.executeQuery();

			while (rs.next()) {
				Computer c = new Computer();
				CompanyDAO cc = new CompanyDAO(connectionDAO);
				c.setCompany(new Company(cc.findById(rs.getLong(1)), rs
						.getLong(1)));
				c.setDiscontinued(rs.getString(2));
				c.setIntroduced(rs.getString(3));
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

				if (ps != null)
					ps.close();

				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}
		}

		return liste;
	}

	public int count() {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;

		String query = "SELECT count(*) FROM computer As c";
		int count = 0;

		try {

			cn = connectionDAO.getConnection();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next())
			count = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (ps != null)
					ps.close();

				if (cn != null)
					cn.close();

			} catch (SQLException e) {
			}

		}
		return count;
	}

}
