package com.excilys.formation.project.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.service.Service;
import com.excilys.formation.project.service.Pages;
import com.excilys.formation.project.service.IService;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("doGet");
		String search = request.getParameter("search");

		List<ComputerDTO> computersDTO = null;
		IService service = null;
		Pages page = null;
		int size = 0;

		int nbPages = (size / 100) + 1;

		String index = request.getParameter("index");
		String limit = request.getParameter("limit");

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
			service = new Service();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Bad instanciation of service");
		}

		page = new Pages(l, l * i - l, search);
		computersDTO = service.pages(page);
		
		size = service.count(search);
		nbPages = (size / l) + 1;

		request.setAttribute("Computers", computersDTO);
		request.setAttribute("size", size);
		request.setAttribute("index", i);
		request.setAttribute("limit", page.getLimit());
		request.setAttribute("nbPages", nbPages);

		getServletContext().getRequestDispatcher("/views/dashboard.jsp")
				.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String selection = request.getParameter("selection");

		if (selection != null && selection.length() > 0) {
			System.out.println(selection);
			String[] str = selection.split(",");
			try {
				IService service = new Service();
				for (int i = 0; i < str.length; i++) {
					service.delete(Long.valueOf(str[i]));
				}
				System.out.println("Computers deleted");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		getServletContext().getRequestDispatcher("/views/dashboard.jsp")
				.forward(request, response);

	}

}
