package com.excilys.formation.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.persistence.CompanyDAO;
import com.excilys.formation.project.persistence.ConnectionDAO;
import com.excilys.formation.project.service.IServiceCompany;
import com.excilys.formation.project.service.ServiceComputer;
import com.excilys.formation.project.service.IServiceComputer;

/**
 * Servlet implementation class AddComputer
 */


@Controller
@RequestMapping("/addComputer")
public class AddComputer {
	private static final String PARAM_COMPUTER_NAME = "computerName";
	private static final long serialVersionUID = 1L;
	
	@Autowired	
	private IServiceComputer serviceComputer;
	
	@Autowired	
	private IServiceCompany serviceCompany;
	
//	 public void init(ServletConfig config) throws ServletException  {
//		    super.init(config);
//		    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//		    }

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
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Company> companies = new ArrayList<Company>();
		companies = serviceCompany.companies();

		request.setAttribute("companies", companies);
		
		return "addComputer";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("...");
		String computerName = request.getParameter(PARAM_COMPUTER_NAME);
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		String companyName = null;

		List<ComputerDTO> list = null;
		ComputerDTO cpt = null;

		try {
			list = serviceComputer.computers();

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCompanyId() == new Long(companyId)) {
					companyName = list.get(i).getCompanyName();
				}
			}

			cpt = new ComputerDTO(0, computerName, introduced, discontinued,
					new Long(companyId), companyName);
			serviceComputer.create(serviceComputer.fromDTOToComputer(cpt));
			list = serviceComputer.computers();
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

		return "dashboard";
	}
}
