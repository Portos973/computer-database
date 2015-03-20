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
import javax.validation.Valid;
import javax.xml.ws.BindingType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.models.Company;
import com.excilys.formation.project.models.Computer;
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
	private static final String PARAM_INTRODUCED = "limit";
	private static final String PARAM_DISCONTINUED= "index";
	private static final String PARAM_COMPANY_ID = "companyId";
	
	
	@Autowired	
	private IServiceComputer serviceComputer;
	
	@Autowired	
	private IServiceCompany serviceCompany;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap model){
		List<Company> companies = serviceCompany.companies();

		model.addAttribute("companies", companies);
		
		return "addComputer";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@Valid ModelMap model, @RequestParam(value=PARAM_COMPUTER_NAME, required=false) String computerName,
			@RequestParam(value=PARAM_INTRODUCED, required=false) String introduced,
			@RequestParam(value=PARAM_DISCONTINUED, required=false) String discontinued,
			@RequestParam(value=PARAM_COMPANY_ID, required=false) String companyId ){
		String companyName = null;

		List<ComputerDTO> computers = null;
		ComputerDTO cpt = null;

		try {
//			computers = serviceComputer.computers();
//
//			for (int i = 0; i < computers.size(); i++) {
//				if (computers.get(i).getCompanyId() == new Long(companyId)) {
//					companyName = computers.get(i).getCompanyName();
//				}
//			}

			cpt = new ComputerDTO(0, computerName, introduced, discontinued,
					new Long(companyId), null);
			serviceComputer.create(serviceComputer.fromDTOToComputer(cpt));
			computers = serviceComputer.computers();
			System.out.println("ADD COMPUTER !!!!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Computer didn't created");
		}

//		model.addAttribute(PARAM_COMPUTER_NAME, computerName);
//		model.addAttribute(PARAM_INTRODUCED, introduced);
//		model.addAttribute(PARAM_DISCONTINUED, discontinued);
//		model.addAttribute(PARAM_COMPANY_ID, companyId);
//		model.addAttribute("companyName", companyName);
//		model.addAttribute("Computers", computers);
//		model.addAttribute("size", computers.size());

		return "redirect:dashboard";
	}
}
