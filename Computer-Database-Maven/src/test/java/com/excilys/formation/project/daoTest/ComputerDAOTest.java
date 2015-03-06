package com.excilys.formation.project.daoTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.persistence.ComputerDAO;
import com.excilys.formation.project.persistence.IComputer;
import com.excilys.formation.project.persistence.ConnectionDAO;

public class ComputerDAOTest {

	private static IComputer mockedCC;
	private static Computer cc = null;
	private static Computer cc1 = null;

	@BeforeClass
	public static void setUp() {
		mockedCC = mock(ComputerDAO.class);
		cc = new Computer();
		cc.setId(new Long(2));
		cc.setName("Apple Inc.");

		cc1 = new Computer();
		cc1.setId(new Long(3));
		cc1.setName("NEC");

		try {
			when(mockedCC.computers(ConnectionDAO.INSTANCE.connectionPool.getConnection())).thenReturn(Arrays.asList(cc, cc1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void computers() {
		List<Computer> liste=null;
		try {
			liste = mockedCC.computers(ConnectionDAO.INSTANCE.connectionPool.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(2, liste.size());
		Computer myComp = liste.get(0);
		assertEquals(new Long(2), myComp.getId());
		assertEquals("Apple Inc.", myComp.getName());
	}

	@Test
	public void details() {
		
		List<Computer> liste=null;
		try {
			mockedCC.details(new Long(2),ConnectionDAO.INSTANCE.connectionPool.getConnection());
			liste = mockedCC.computers(ConnectionDAO.INSTANCE.connectionPool.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if (liste.contains(cc)==false)
			fail(" Elément n'existe pas");
	}

	@Test
	public void create() {
	}

	@Test
	public void update() {
	}

//	@Test
//	public void delete() {
//		mockedCC.delete(new Long(2));
//		List<Computer> liste = mockedCC.computers();
//
//		
//		if (liste.contains(cc)==false)
//			fail(" Elément non supprimé");
//			
//
//	}
}
