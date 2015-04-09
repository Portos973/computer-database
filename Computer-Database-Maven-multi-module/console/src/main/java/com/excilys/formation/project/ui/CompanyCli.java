/**
 * @author Anderson F.
 * Description: This class content methods which communicate with web service
 */

package com.excilys.formation.project.ui;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.stereotype.Component;

import com.excilys.formation.project.models.Company;

@Component
public class CompanyCli {
	
	Client client;
	WebTarget computerTarget;
	
	private CompanyCli(){
		client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
		computerTarget = client.target("http://localhost:8080/webservice/rest/companies/");
				
	}
	
	public void companies(){
		List<Company> companies = computerTarget.path("/all").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Company>>() {});
		System.out.println("\n /** List of companies **/");
		for (int i = 0; i < companies.size(); i++)
			System.out.println(companies.get(i).getName());
	}
	
	

}
