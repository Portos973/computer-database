//package com.excilys.formation.project.daoTest;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.excilys.formation.project.models.Company;
//import com.excilys.formation.project.persistence.CompanyDAO;
//import com.excilys.formation.project.persistence.ICompanyDAO;
//import com.excilys.formation.project.persistence.ConnectionDAO;
//
//public class CompanyDAOTest {
//
//	private static ICompanyDAO mockedCC;
//	private static Company cc = null;
//	private static Company cc1 = null;
//
//	@BeforeClass
//	public static void setUp() {
//		mockedCC = mock(CompanyDAO.class);
//		cc = new Company("Apple Inc.",new Long(2) );
//		cc1 = new Company("NEC",new Long(3));
//		
////		try {
////			when(mockedCC.companies(ConnectionDAO.INSTANCE.connectionPool.getConnection())).thenReturn(Arrays.asList(cc, cc1));
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}
//
//	@Test
//	public void testCompanies() {
//
//		List<Company> liste=null;
////		try {
////			liste = mockedCC.companies(ConnectionDAO.INSTANCE.connectionPool.getConnection());
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		assertEquals(2, liste.size());
//		Company myComp = liste.get(0);
//		assertEquals(new Long(2), myComp.getId());
//		assertEquals("Apple Inc.", myComp.getName());
//	}
//
//}
