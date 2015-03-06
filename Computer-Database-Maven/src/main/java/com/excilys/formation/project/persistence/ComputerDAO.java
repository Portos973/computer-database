/**
 * @author Anderson F.
 * Description: Class CompanyDAO allows to handle computer table  
 * */

package com.excilys.formation.project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.dto.ComputerDTO;

public enum ComputerDAO implements IComputer {
	INSTANCE;

	// method computers: list of computer
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#computers()
	 */
	
	private ComputerDAO() {
	}

	@Override
	public List<Computer> computers() {
		// PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;

		ArrayList<Computer> liste = new ArrayList<Computer>();
		String query = "SELECT c.company_id, c.discontinued, c.introduced, c.name, c.id FROM computer As c ORDER BY c.name";

		try {
			ps = ConnectionDAO.INSTANCE.getConnection().prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Computer c = new Computer();

				c.setCompany(new Company(CompanyDAO.INSTANCE.findById(rs.getLong(1)), rs.getLong(1)));
				c.setDiscontinued(rs.getDate(2));
				c.setIntroduced(rs.getDate(3));
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
				
				ConnectionDAO.INSTANCE.closeConnection();


			} catch (SQLException e) {
			}
		}

		System.out.println("/** List of computer **/");
		for (int i = 0; i < liste.size(); i++)
			System.out.println(liste.get(i).getName());

		return liste;

	}

	// Details of computer with his ID
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#details(java.lang.Long)
	 */
	@Override
	public void details(Long id) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;

		ArrayList<Computer> liste = new ArrayList<Computer>();
		Computer c = new Computer();
		String query = "SELECT c.company_id, c.discontinued, c.introduced, c.name, c.id, cc.name FROM computer As c, company As cc where c.id=?";

		try {
			ps = ConnectionDAO.INSTANCE.getConnection().prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				c.setCompany(new Company(rs.getString(6), rs.getLong(1)));
				c.setDiscontinued(rs.getDate(2));
				c.setIntroduced(rs.getDate(3));
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
				
				ConnectionDAO.INSTANCE.closeConnection();

			} catch (SQLException e) {
			}
		}

		System.out.println(c.toString());

	}

	// Creation and insertion of computer in database
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#create(java.lang.Long, java.lang.String, java.util.Date, java.util.Date)
	 */

	@Override
	public void create(Long cid, String name, Date date, Date date2) {
		int rs = 0;
		Connection cn = null;
		String query = "INSERT INTO computer set company_id= ?, name=?, introduced=?, discontinued=?";
		PreparedStatement ps = null;

		try {
			ps = ConnectionDAO.INSTANCE.getConnection().prepareStatement(query);
			if (cid != null)
				ps.setLong(1, cid);
			else
				ps.setNull(1, 0);
			ps.setString(2, name);
			ps.setTimestamp(3, new Timestamp(date.getTime()));
			ps.setTimestamp(4, new Timestamp(date2.getTime()));
			rs = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				
				ConnectionDAO.INSTANCE.closeConnection();

			} catch (SQLException e) {
			}
		}

	}

	// mettre à jour les champs d'un ordinateur de la table computer
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#update(java.lang.Long, java.lang.Long, java.lang.String, java.util.Date, java.util.Date)
	 */

	@Override
	public void update(Long id, Long cid, String name, Date date, Date date2) {
		int rs = 0;
		Connection cn = null;

		String query = null;

		if (id != null && cid != null && name != null && date != null
				&& date2 != null) {
			query = "UPDATE computer set company_id= ?, name=?, introduced=?, discontinued=? where id=?";

			try {
				PreparedStatement ps = ConnectionDAO.INSTANCE.getConnection().prepareStatement(query);
				ps.setLong(1, cid);
				ps.setString(2, name);
				ps.setTimestamp(3, new Timestamp(date.getTime()));
				ps.setTimestamp(4, new Timestamp(date2.getTime()));
				ps.setLong(5, id);

				rs = ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				try {
					ConnectionDAO.INSTANCE.closeConnection();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	// delete line of computer table
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#delete(java.lang.Long, java.sql.Connection)
	 */
	@Override
	public void delete(Long id, Connection connection) {
		int rs = 0;
		Connection cn = null;
		String query = "DELETE FROM computer where id= ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeUpdate();
			System.out.println(" Computer deleted ! ");
			ConnectionDAO.INSTANCE.closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	// Select with limit and offset
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#pages(int, int, java.lang.String)
	 */

	@Override
	public List<Computer> pages(int limit, int offset, String search) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;
		String query = null;

		ArrayList<Computer> liste = new ArrayList<Computer>();
		if (search == null) {
			query = "SELECT c.company_id, c.discontinued, c.introduced, c.name, c.id FROM computer As c ORDER BY c.name LIMIT ? OFFSET ?;";
		} else {
			query = "SELECT c.company_id, c.discontinued, c.introduced, c.name, c.id FROM computer As c WHERE c.name LIKE ? ORDER BY c.name LIMIT ? OFFSET ?";
		}

		try {
			ps = ConnectionDAO.INSTANCE.getConnection().prepareStatement(query);
			if (search == null) {
				ps.setInt(1, limit);
				ps.setInt(2, offset);
			} else {
				ps.setString(1, search+"%");
				ps.setInt(2, limit);
				ps.setInt(3, offset);
			}
			rs = ps.executeQuery();

			while (rs.next()) {
				Computer c = new Computer();
				c.setCompany(new Company(CompanyDAO.INSTANCE.findById(rs.getLong(1)), rs
						.getLong(1)));
				c.setDiscontinued(rs.getDate(2));
				c.setIntroduced(rs.getDate(3));
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

				ConnectionDAO.INSTANCE.closeConnection();

			} catch (SQLException e) {
			}
		}

		return liste;
	}

	// number of elements
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#count(java.lang.String)
	 */

	@Override
	public int count(String search) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;

		String query = null;

		if (search == null) {
			query = "SELECT count(*) FROM computer As c";
		} else {
			query = "SELECT count(name) FROM computer As c WHERE name LIKE ? ";
		}

		int count = 0;
		try {
			ps = ConnectionDAO.INSTANCE.getConnection().prepareStatement(query);
			if (search != null) {
				ps.setString(1, search + "%");
			}
			rs = ps.executeQuery();
			while (rs.next())
				count = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (ps != null)
					ps.close();
			} catch (SQLException e) {
			}

		}
		return count;
	}

	// find computer's id by company's id
	/* (non-Javadoc)
	 * @see com.excilys.formation.project.persistence.ComputerInterface#findByCompanyId(java.lang.Long, java.sql.Connection)
	 */

	@Override
	public ArrayList<Long> findByCompanyId(Long id, Connection connection) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection cn = null;
		String query = "SELECT id FROM computer where company_id=?;";
		ArrayList<Long> liste = new ArrayList<Long>();

		try {

			cn = connection;
			ps = cn.prepareStatement(query);
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
