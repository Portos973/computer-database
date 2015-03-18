/**
 * @author Anderson F.
 * Description: Class ComputerDTO corresponding to Computer table  
 * */

package com.excilys.formation.project.dto;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


public class ComputerDTO {
	
	private long id;
	
	 
	@Size(min=2, max=30) 
	private String name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String discontinued;
		
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String introduced;

	private long companyId;
	private String companyName;
	
	public ComputerDTO(long id,String name,String introduced,String discontinued, long companyId,String companyName){
		this.id=id;
		this.name=name;
		this.companyId=companyId;
		this.companyName= companyName;
		this.discontinued=discontinued;
		this.introduced=introduced;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n Détails de l'ordinateur: \n company_id: ");
		str.append(this.companyId);
		str.append(this.companyName);
		str.append("\n ID: ");
		str.append(this.id);
		str.append("\n nom: ");
		str.append(this.name);
		str.append("\n discontinued: ");
		str.append(this.discontinued);
		str.append("\n introduced: ");
		str.append(this.introduced);
		return str.toString();
	}

	

	
	

}
