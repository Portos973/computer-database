package com.excilys.formation.project.daoTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.dao.CompanyDAO;

public class CompanyDAOTest {

	private static CompanyDAO mockedCC;
	private static Company cc = null;
	private static Company cc1 = null;

	@BeforeClass
	public static void setUp() {
		mockedCC = mock(CompanyDAO.class);
		cc = new Company("Apple Inc.",new Long(2) );
		cc1 = new Company("NEC",new Long(3));
		
		when(mockedCC.companies()).thenReturn(Arrays.asList(cc, cc1));
	}

	@Test
	public void testCompanies() {

		List<Company> liste = mockedCC.companies();
		assertEquals(2, liste.size());
		Company myComp = liste.get(0);
		assertEquals(new Long(2), myComp.getId());
		assertEquals("Apple Inc.", myComp.getName());
	}

}
