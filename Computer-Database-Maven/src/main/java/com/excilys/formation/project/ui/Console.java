/**
 * @author Anderson F.
 * Description: Classe Console permet de lancer la connection et d'effectuer divers transactions 
 * */

package com.excilys.formation.project.ui;

import java.util.Scanner;

import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.persistence.ConnectionDAO;
import com.excilys.formation.project.service.Service;
import com.excilys.formation.project.service.IService;
import com.excilys.formation.project.utils.Utils;


public class Console {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		IService service=new Service();

		while (true) {
			System.out.println("Hello, make a choice: ");
			System.out.println(" 1: List of computers");
			System.out.println(" 2: List of companies");
			System.out.println(" 3: Details of one computer");
			System.out.println(" 4: Insert one computer");
			System.out.println(" 5: Updated the data of one computer");
			System.out.println(" 6: Delete one computer");
			System.out.println(" 7: Delete one company");

			switch (sc.nextLine()) {
			case "1":
				service.computers();
				System.out.println("\n");
				break;
				
			case "2":
				service.companies();
				System.out.println("\n");
				break;
				
			case "3":
				System.out.println(" Enter the cumputer's id :");
				service.details(sc.nextLong());
				System.out.println("\n");
				break;
				
			case "4":
				System.out.println(" Enter one name *: ");
				String n = sc.nextLine();

				System.out.println(" Enter one introduced  date: (format: AAAA-MM-JJ) ");
				String d = sc.nextLine();


				while (!Utils.checkDate(d) && d.length() != 0) {
					System.out.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String d1 = sc.nextLine();
					d = d1;

				}
								
				System.out.println(" Enter one discontinued  date: (format: AAAA-MM-JJ) ");
				String f = sc.nextLine();


				while (!Utils.checkDate(f) && f.length() != 0) {
					System.out.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String f1 = sc.nextLine();
					f = f1;

				}
				
				System.out.println(" Enter the company's id: ");
				Long cid = sc.nextLong();

				ComputerDTO dto = new ComputerDTO(0, n,d,f, cid, null);
				service.create(service.fromDTOToComputer(dto));


				break;

			case "5":
				System.out.println(" Enter the cumputer's id that you want to update:");
				Long id=new Long(sc.nextLine());
				
				System.out.println(" Enter one name *:");
				String name = sc.nextLine();

				System.out.println(" Enter one date: (format: AAAA-MM-JJ) ");
				String date = sc.nextLine();

				while (!Utils.checkDate(date) && date.length() != 0) {
					System.out.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String d1 = sc.nextLine();
					date = d1;

				}
				
				System.out.println(" Enter one discontinued  date: (format: AAAA-MM-JJ) ");
				String fin = sc.nextLine();


				while (!Utils.checkDate(fin) && fin.length() != 0) {
					System.out.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String f1 = sc.nextLine();
					fin = f1;

				}
				
				System.out.println(" Enter the company's id: ");
				Long compId = new Long(sc.nextLong());
				
				ComputerDTO dto1 = new ComputerDTO(id, name, date, fin, compId, null);
				service.update(service.fromDTOToComputer(dto1));
				
				break;

			case "6":
				System.out.println(" Enter the cumputer's id that you want to delete:");
				//service.delete(sc.nextLong());
				System.out.println("\n");
				break;
				
			case "7":
				System.out.println(" Enter the company's id that you want to delete:");
				service.deleteCompany(sc.nextLong());
				System.out.println("\n");
				break;
			}

		}
	}

}
