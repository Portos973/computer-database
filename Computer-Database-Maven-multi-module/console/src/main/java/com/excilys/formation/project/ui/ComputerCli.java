/**
 * @author Anderson F.
 * Description: This class content methods which communicate with web service
 */

package com.excilys.formation.project.ui;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.stereotype.Component;

import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.models.Computer;

@Component
public class ComputerCli {
	Client client;
	WebTarget computerTarget;

	/**
	 * Constructor which initialize the instance variable:
	 * one client and webTarget
	 */
	private ComputerCli() {
		client = ClientBuilder.newBuilder().register(JacksonFeature.class)
				.build();
		computerTarget = client
				.target("http://localhost:8080/webservice/rest/computers/");

	}

	/**
	 * Take a list of computers
	 */
	public void computers() {
		List<ComputerDTO> computersDTO = computerTarget.path("/all")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<ComputerDTO>>() {
				});
		System.out.println("/** List of computers **/");
		for (int i = 0; i < computersDTO.size(); i++)
			System.out.println(computersDTO.get(i).getName());
	}

	/**
	 * Recover the detail of one computer 
	 * @param id
	 */
	public void details(Long id) {
		ComputerDTO computer = computerTarget.path("/" + id)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<ComputerDTO>() {
				});
		System.out.println(computer.toString());
	}

	/**
	 * Create one computer 
	 * @param Computer
	 */
	public void create(Computer computer) {

		Response response = computerTarget.path("/create")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(computer, MediaType.APPLICATION_JSON));
		if (response.getStatus() != 200 && response.getStatus() != 204) {
			System.out.println("Error http " + response.getStatus());
		}
	}

	/**
	 * Update one computer 
	 * @param Computer
	 */
	public void update(Computer computer) {
		Response response = computerTarget.path("/update")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(computer, MediaType.APPLICATION_JSON));
		if (response.getStatus() != 200 && response.getStatus() != 204) {
			System.out.println("Error http " + response.getStatus());
		}
	}

	/**
	 * Delete one computer
	 * @param id
	 */
	public void delete(Long id){
		Response response = computerTarget.path("/"+id)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(id, MediaType.APPLICATION_JSON));
		if (response.getStatus() != 200 && response.getStatus() != 204) {
			System.out.println("Error http " + response.getStatus());
		}
    }
}
