package com.excilys.formation.project.servlet;


import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.project.service.*;
import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.models.Company;

/**
 * Servlet implementation class AddComputer
 */


@Controller
@RequestMapping("/addComputer")
public class AddComputer {
	
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
	protected String doPost(ModelMap model, @Valid @ModelAttribute ComputerDTO computerDTO, BindingResult result ){
		List<Company> companies = serviceCompany.companies();

		model.addAttribute("companies", companies);
		
		
		if(result.hasErrors()) {
			model.addAttribute("computerDTO", computerDTO);
			return "addComputer";
		}

		
		serviceComputer.create(serviceComputer.fromDTOToComputer(computerDTO));
		System.out.println("ADD COMPUTER !!!!");

		return "redirect:dashboard";
	}
}
