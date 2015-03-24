package com.excilys.formation.project.servlet;

import java.util.ArrayList;
import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.project.service.IServiceCompany;
import com.excilys.formation.project.service.IServiceComputer;
import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.models.Company;

/**
 * Servlet implementation class EditComputer
 */
@Controller
@RequestMapping("/editComputer")
public class EditComputer{
	
	private static final String PARAM_COMPUTER_NAME = "computerName";
	private static final String PARAM_INTRODUCED = "limit";
	private static final String PARAM_DISCONTINUED= "index";
	private static final String PARAM_COMPANY_ID = "companyId";
	private static final String PARAM_ID = "id";
	
	@Autowired	
	private IServiceComputer serviceComputer;
	
	@Autowired	
	private IServiceCompany serviceCompany;
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputer() {   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap model, @RequestParam(value=PARAM_ID, required=false) String id,
			@RequestParam(value=PARAM_COMPUTER_NAME, required=false) String computerName,
			@RequestParam(value=PARAM_INTRODUCED, required=false) String introduced,
			@RequestParam(value=PARAM_DISCONTINUED, required=false) String discontinued,
			@RequestParam(value=PARAM_COMPANY_ID, required=false) String companyId){
		
		List<Company> companies = serviceCompany.companies();
		
		model.addAttribute(PARAM_ID, id);
		model.addAttribute("companies", companies);
		model.addAttribute(PARAM_COMPUTER_NAME, computerName);
		model.addAttribute(PARAM_INTRODUCED, introduced);
		model.addAttribute(PARAM_DISCONTINUED, discontinued);
		model.addAttribute(PARAM_COMPANY_ID, companyId);
		
		return "editComputer";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
    @RequestMapping(method = RequestMethod.POST)
	protected String doPost(ModelMap model, @RequestParam(value=PARAM_ID, required=false) String id,
			@RequestParam(value=PARAM_COMPUTER_NAME, required=false) String computerName,
			@RequestParam(value=PARAM_INTRODUCED, required=false) String introduced,
			@RequestParam(value=PARAM_DISCONTINUED, required=false) String discontinued,
			@RequestParam(value=PARAM_COMPANY_ID, required=false) String companyId){

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
		
		model.addAttribute("Computers", computersDTO);
		model.addAttribute(PARAM_COMPUTER_NAME, computerName);
		model.addAttribute(PARAM_INTRODUCED, introduced);
		model.addAttribute(PARAM_DISCONTINUED, discontinued);
		model.addAttribute(PARAM_COMPANY_ID, companyId);
		
		return "editComputer";
	}

}
