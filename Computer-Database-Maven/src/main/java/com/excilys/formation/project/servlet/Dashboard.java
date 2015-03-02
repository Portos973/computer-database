package com.excilys.formation.project.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.controller.Controller;
import com.excilys.formation.project.service.Pages;

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

		List<Computer> list = null;
		List<Computer> listP = null;
		Controller c;
		int size = 0;
		try {
			if (search != null) {

				System.out.println(search);
				c = new Controller();
				size = c.count();
				list = c.search(search);
			} else {
				System.out.println(search);
				c = new Controller();
				size = c.count();
				// list = c.computers();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int nbPages = (size / 100) +1;
		
		String index = request.getParameter("index");
		String limit = request.getParameter("limit");

		// value of limit and offset calcul
		int i = 1;
		int l = 100;
		if (index != null && limit != null) {
			i = Integer.parseInt(index);
			l = Integer.parseInt(limit);
			nbPages = (size / l) +1;
		}else if(limit != null){
			l = Integer.parseInt(limit);
		}

		System.out.println("index= " + i + " limit= " + l);

		Pages pg = new Pages();
		if (index == null && limit == null){
			list = pg.next(100, 100 * 1 - 100);
				
		}else if( index == null &&limit != null)list = pg.next(l, l * i - l);
		else list = pg.next(l, l * i - l);
		
		request.setAttribute("Computers", list);
		request.setAttribute("size", size);
		request.setAttribute("index", i);
		request.setAttribute("limit", l);
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

	}

}
