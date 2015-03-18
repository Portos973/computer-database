package com.excilys.formation.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import com.excilys.formation.project.service.IServiceCompany;
import com.excilys.formation.project.service.ServiceComputer;
import com.excilys.formation.project.service.IServiceComputer;

/**
 * Servlet implementation class EditComputer
 */
@Controller
@RequestMapping("/editComputer")
public class EditComputer{
	@Autowired	
	private IServiceComputer serviceComputer;
	
	@Autowired	
	private IServiceCompany serviceCompany;
	       
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
    @RequestMapping(method = RequestMethod.GET)
	protected String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		
		
		
		List<Company> companies= new ArrayList<Company>();

		companies=serviceCompany.companies();
		
		request.setAttribute("id", id);
		request.setAttribute("companies", companies);
		request.setAttribute("computerName", computerName);
		request.setAttribute("introduced", introduced);
		request.setAttribute("discontinued", discontinued);
		request.setAttribute("companyId", companyId);
		
		return "editComputer";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
    @RequestMapping(method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("...");
		String id=request.getParameter("id");;
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		String companyName=null;
		
		
		List<ComputerDTO> computersDTO= new ArrayList<ComputerDTO>();

		ComputerDTO dto= null;
		try {

			computersDTO = serviceComputer.computers();

			for (int i = 0; i < computersDTO.size(); i++) {
				if (computersDTO.get(i).getCompanyId() == new Long(companyId)) {
					companyName = computersDTO.get(i).getCompanyName();
				}
			}
			
			dto= new ComputerDTO(new Long(id), computerName, introduced, discontinued, new Long(companyId), companyName);
			serviceComputer.update(serviceComputer.fromDTOToComputer(dto));
			
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
		
		return "editComputer";
	}

}
