package com.excilys.formation.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.dao.CompanyDAO;
import com.excilys.formation.project.dao.ComputerDAO;
import com.excilys.formation.project.dao.ConnectionDAO;


public class Controller {

	ComputerDAO c;
	CompanyDAO cc;
	List<Company> list;

	public Controller() throws Exception {

		c = new ComputerDAO(ConnectionDAO.getInstance());
		cc = new CompanyDAO(ConnectionDAO.getInstance());
		list = new ArrayList<Company>();
	}

	public List<Computer> computers() {
		//c.computers();
		return c.computers();
	}

	public List<Company> companies() {
		List<Company> companies = cc.companies();
		return companies; 
	}

	public void details(Long id) {
		c.details(id);
	}

	public boolean checkDate(String d) {
		String regex = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(d);

		return matcher.matches();
	}

	public boolean checkCompanyId(Long cid) {
		boolean bool = false;
		list = cc.companies();
		
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getId() == cid)
				bool = true;

		return bool;
	}

	public void create(Long cid, String name, String date, String finProd) {

		if (!checkCompanyId(cid)) {

			c.create(null, name, date,finProd);
			System.out.println("\n");
		} else
			c.create(cid, name, date,finProd);
	}

	public void update(Long id, Long cid, String name, String date, String finProd) {
		if (id != null)
		c.update(id,  cid,  name,  date,  finProd);
	}

	public void delete(Long id) {
		c.delete(id);
	}
	
	public List<Computer> search(String name){
		return c.search(name);
	}
	
	public List<Computer>  pages(int limit, int offset){
		return c.pages(limit, offset);
	}
	
	public int count(){
		return c.count();
	}
	

}
