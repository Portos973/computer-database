/**
 * @author Anderson F.
 * Description: Classe Computer correspondant à la table Computer 
 * */

package com.excilys.formation.project.beans;

import java.sql.Timestamp;

public class Computer {
	
	private String name;
	private Long company_id;
	private Long id;
	private Timestamp discontinued;
	private Timestamp introduced;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Timestamp discontined) {
		this.discontinued = discontined;
	}
	public Timestamp getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}
	
	public String toString(){
		return ( "\n Détails de l'ordinateur: \n company_id: "+this.company_id+"\n id: "+this.id +"\n nom: "+ this.name +"\n discontinued: "+this.discontinued+"\n introduced: "+this.introduced);
	}
	

}
