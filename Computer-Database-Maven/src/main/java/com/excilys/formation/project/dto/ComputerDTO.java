package com.excilys.formation.project.dto;

public class ComputerDTO {
	
	public String formatDate(String ss){
		String str=null;
		
		if (ss != null) {
			str = ss.substring(0, 11);
		} else {
			str = ss;
		}
		
		return str;
	}

}
