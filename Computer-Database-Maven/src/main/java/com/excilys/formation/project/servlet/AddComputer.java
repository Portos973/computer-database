package com.excilys.formation.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.persistence.CompanyDAO;
import com.excilys.formation.project.persistence.ConnectionDAO;
import com.excilys.formation.project.service.Service;
import com.excilys.formation.project.service.IService;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final String PARAM_COMPUTER_NAME = "computerName";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Company> companies = new ArrayList<Company>();
		IService c = null;
		try {
			c = new Service();
		} catch (Exception e) {
			e.printStackTrace();
		}
		companies = c.companies();

		request.setAttribute("companies", companies);
		getServletContext().getRequestDispatcher("/views/addComputer.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("...");
		String computerName = request.getParameter(PARAM_COMPUTER_NAME);
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		String companyName = null;

		List<ComputerDTO> list = null;
		IService service = null;
		ComputerDTO cpt = null;

		try {
			service = new Service();
			list = service.computers();

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCompanyId() == new Long(companyId)) {
					companyName = list.get(i).getCompanyName();
				}
			}

			cpt = new ComputerDTO(0, computerName, introduced, discontinued,
					new Long(companyId), companyName);
			service.create(service.fromDTOToComputer(cpt));
			list = service.computers();
			System.out.println("ADD COMPUTER !!!!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Computer didn't created");
		}

		request.setAttribute(PARAM_COMPUTER_NAME, computerName);
		request.setAttribute("introduced", introduced);
		request.setAttribute("discontinued", discontinued);
		request.setAttribute("companyId", companyId);
		request.setAttribute("companyName", companyName);
		request.setAttribute("Computers", list);
		request.setAttribute("size", list.size());

		getServletContext().getRequestDispatcher("/views/dashboard.jsp")
				.forward(request, response);
	}
}
