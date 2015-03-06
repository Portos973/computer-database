package com.excilys.formation.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.service.Service;
import com.excilys.formation.project.service.IService;

/**
 * Servlet implementation class EditComputer
 */
@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		
		
		
		List<Company> companies= new ArrayList<Company>();
		IService c=null;
		try {
			c = new Service();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		companies=c.companies();
		
		request.setAttribute("id", id);
		request.setAttribute("companies", companies);
		request.setAttribute("computerName", computerName);
		request.setAttribute("introduced", introduced);
		request.setAttribute("discontinued", discontinued);
		request.setAttribute("companyId", companyId);
		
		getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("...");
		String id=request.getParameter("id");;
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		String companyName=null;
		
		IService service =null;
		List<ComputerDTO> computersDTO= new ArrayList<ComputerDTO>();

		ComputerDTO dto= null;
		try {
			service = new Service();
			
			
			service = new Service();
			computersDTO = service.computers();

			for (int i = 0; i < computersDTO.size(); i++) {
				if (computersDTO.get(i).getCompanyId() == new Long(companyId)) {
					companyName = computersDTO.get(i).getCompanyName();
				}
			}
			
			dto= new ComputerDTO(new Long(id), computerName, introduced, discontinued, new Long(companyId), companyName);
			service.update(service.fromDTOToComputer(dto));
			
			System.out.println("Succes of update!!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Computer didn't update");
		}
		
		request.setAttribute("Computers", computersDTO);
		request.setAttribute("computerName", computerName);
		request.setAttribute("introduced", introduced);
		request.setAttribute("discontinued", discontinued);
		request.setAttribute("companyId", companyId);
		
		getServletContext().getRequestDispatcher("/Dashboard").forward(
				request, response);
	}

}
