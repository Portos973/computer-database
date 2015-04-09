/**
 * @author Anderson F.
 * Description: Class ComputerDTO corresponding to Computer table, the instance variable are primitive type
 * */

package com.excilys.formation.project.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.excilys.formation.project.utils.Date;

public class ComputerDTO {

	private long id;

	@NotEmpty
	@Size(min = 2, max = 30)
	private String name;

	@Date
	private String discontinued;

	@Date
	private String introduced;

	private long companyId;
	private String companyName;

	public ComputerDTO() {
		// TODO Auto-generated constructor stub
	}

	public ComputerDTO(long id, String name, String introduced,
			String discontinued, long companyId, String companyName) {
		this.id = id;
		this.name = name;
		this.companyId = companyId;
		this.companyName = companyName;
		this.discontinued = discontinued;
		this.introduced = introduced;
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
		str.append("\n Details of computer: \n Company ID: ");
		str.append(this.companyId);
		str.append("\n Company Name: ");
		str.append(this.companyName);
		str.append("\n ID: ");
		str.append(this.id);
		str.append("\n Name: ");
		str.append(this.name);

		str.append("\n Discontinued: ");
		if (this.discontinued != null) {
			str.append(this.discontinued);
		} else {
			str.append(" ");
		}
		str.append("\n Introduced: ");
		if (this.introduced != null) {
			str.append(this.introduced);
		} else {
			str.append(" ");
		}

		return str.toString();
	}

}
