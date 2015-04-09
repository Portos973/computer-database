package com.excilys.formation.project.servlet;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.project.service.*;
import com.excilys.formation.project.service.Pages;
import com.excilys.formation.project.dto.ComputerDTO;

/**
 * Servlet implementation class Dashboard
 */

@Controller
@RequestMapping("/dashboard")
public class Dashboard {	
	private static final String PARAM_SEARCH = "search";
	private static final String PARAM_LIMIT = "limit";
	private static final String PARAM_INDEX= "index";
	private static final String PARAM_SELECTION = "selection";
	private static final String PARAM_ORDER = "order";
	private static final String PARAM_SORT= "sort";
	
	@Autowired	
	private IServiceComputer service;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap model, @RequestParam(value=PARAM_SEARCH, required=false) String search,
			@RequestParam(value=PARAM_LIMIT, required=false) String limit,
			@RequestParam(value=PARAM_INDEX, required=false) String index,
			@RequestParam(value=PARAM_ORDER, required=false) String order,
			@RequestParam(value=PARAM_SORT, required=false) String sort){
		// TODO Auto-generated method stub

		System.out.println("doGet");
		
		//String search = request.getParameter("search");

		List<ComputerDTO> computersDTO = null;
		Pages page = null;
		long size = 0;

		long nbPages =0;

		//String index = request.getParameter("index");
		//String limit = request.getParameter("limit");

		// value of limit and offset calcul
		int i = 1;
		int l = 100;
		if (index != null && limit != null) {
			i = Integer.parseInt(index);
			l = Integer.parseInt(limit);
		} else if (limit != null) {
			l = Integer.parseInt(limit);
		}

		System.out.println("index= " + i + " limit= " + l);

		try {
			System.out.println(search);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Bad instanciation of service");
		}

		page = new Pages(l,i, l * i - l, search, sort, order);
		computersDTO = service.pages(page);
		
		size = service.count(search);
		nbPages = (size / l) + 1;

		model.addAttribute("Computers", computersDTO);
		model.addAttribute("size", size);
		model.addAttribute("index", page.getIndex());
		model.addAttribute("limit", page.getLimit());
		model.addAttribute("nbPages", nbPages);

		return "dashboard";
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	 @RequestMapping(method = RequestMethod.POST)
	protected String doPost(ModelMap model, @RequestParam(value=PARAM_SELECTION, required=false) String selection){
		// TODO Auto-generated method stub

		if (selection != null && selection.length() > 0) {
			System.out.println(selection);
			String[] str = selection.split(",");
			try {
				for (int i = 0; i < str.length; i++) {
					service.delete(Long.valueOf(str[i]));
				}
				System.out.println("Computers deleted");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "redirect:dashboard";
	}

}
