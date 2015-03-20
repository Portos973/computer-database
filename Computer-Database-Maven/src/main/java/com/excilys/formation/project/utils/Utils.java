package com.excilys.formation.project.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.excilys.formation.project.controller.ServiceComputer;


public class Utils {
	
	static ServiceComputer service=new ServiceComputer() ;

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



}
