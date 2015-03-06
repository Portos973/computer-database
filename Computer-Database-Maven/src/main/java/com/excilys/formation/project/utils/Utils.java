package com.excilys.formation.project.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.persistence.CompanyDAO;
import com.excilys.formation.project.persistence.ICompany;
import com.excilys.formation.project.service.IService;

public class Utils {

	public static String formatDate(Date date) {
		String str = date.toString();

		return str.substring(0, 11);
	}

	public static boolean checkDate(String d) {
		String regex = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(d);

		return matcher.matches();
	}

	public static boolean checkCompanyId(Long cid) {
		boolean bool = false;
		CompanyDAO comapny = null;
		IService service = null;
//		comapny = new CompanyDAO();

		List<Company> companies = new ArrayList<Company>();
		companies = service.companies();

		for (int i = 0; i < companies.size(); i++)
			if (companies.get(i).getId() == cid)
				bool = true;

		return bool;
	}

}
