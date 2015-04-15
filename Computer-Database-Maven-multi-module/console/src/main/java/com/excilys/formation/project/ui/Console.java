/**
 * @author Anderson F.
 * Description: Classe Console allow to launch the menu 
 * */

package com.excilys.formation.project.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.excilys.formation.project.service.*;
import com.excilys.formation.project.dto.ComputerDTO;
import com.excilys.formation.project.utils.Utils;
import com.excilys.formation.project.validation.Validate;

@Component
public class Console {

	@Autowired
	private IServiceComputer serviceComputer;

	@Autowired
	private IServiceCompany serviceCompany;

	@Autowired
	private Validate validate;

	@Autowired
	ComputerCli computerView;

	@Autowired
	CompanyCli companyView;

	public void launch() {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("Hello, make a choice: ");
			System.out.println(" 1: List of computers");
			System.out.println(" 2: List of companies");
			System.out.println(" 3: Details of one computer");
			System.out.println(" 4: Insert a computer");
			System.out.println(" 5: Updated the data of one computer");
			System.out.println(" 6: Delete a computer");
			System.out.println(" 7: Delete a company");

			switch (sc.nextLine()) {
			case "1":
				computerView.computers();
				System.out.println("\n");
				break;

			case "2":
				companyView.companies();
				System.out.println("\n");
				break;

			case "3":
				System.out.println(" Enter the cumputer's id :");
				Long id1 = sc.nextLong();
				while (!validate.checkId(id1)) {
					System.out.println(" Enter a id which exist, please");
					id1 = sc.nextLong();
				}
				computerView.details(id1);
				System.out.println("\n");
				break;

			case "4":
				System.out.println(" Enter a name *: ");
				String n = sc.nextLine();

				while (n.length() == 0) {
					System.out.println(" Please, enter a name *: ");
					n = sc.nextLine();
				}
				System.out
						.println(" Enter a introduced  date: (format: AAAA-MM-JJ) ");
				String d = sc.nextLine();

				while (!Utils.checkDate(d) && d.length() != 0) {
					System.out
							.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String d1 = sc.nextLine();
					d = d1;

				}

				System.out
						.println(" Enter a discontinued  date: (format: AAAA-MM-JJ) ");
				String f = sc.nextLine();

				while (!Utils.checkDate(f) && f.length() != 0) {
					System.out
							.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String f1 = sc.nextLine();
					f = f1;

				}

				System.out.println(" Enter the company's id: ");
				Long cid = sc.nextLong();

				while (!validate.checkCompanyId(cid) || cid != 0) {
					System.out
							.println(" Enter the company's id valid, please ");
					cid = sc.nextLong();
				}
				ComputerDTO dto = new ComputerDTO(0, n, d, f, cid, null);
				computerView.create(serviceComputer.fromDTOToComputer(dto));
				System.out
						.println("===========> Computer created !!! <=========");

				break;

			case "5":
				System.out
						.println(" Enter the cumputer's id that you want to update:");
				Long id = new Long(sc.nextLine());

				System.out.println(" Enter a name *:");
				String name = sc.nextLine();
				
				while (name.length() == 0) {
					System.out.println(" Please, enter a name *: ");
					name = sc.nextLine();
				}

				System.out.println(" Enter a date: (format: AAAA-MM-JJ) ");
				String date = sc.nextLine();

				while (!Utils.checkDate(date) && date.length() != 0) {
					System.out
							.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String d1 = sc.nextLine();
					date = d1;

				}

				System.out
						.println(" Enter one discontinued  date: (format: AAAA-MM-JJ) ");
				String fin = sc.nextLine();

				while (!Utils.checkDate(fin) && fin.length() != 0) {
					System.out
							.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String f1 = sc.nextLine();
					fin = f1;

				}

				System.out.println(" Enter the company's id: ");
				Long compId = new Long(sc.nextLong());

				ComputerDTO dto1 = new ComputerDTO(id, name, date, fin, compId,
						null);
				computerView.update(serviceComputer.fromDTOToComputer(dto1));

				break;

			case "6":
				System.out
						.println(" Enter the cumputer's id that you want to delete:");
				computerView.delete(sc.nextLong());
				System.out.println("\n");
				break;

			case "7":
				System.out
						.println(" Enter the company's id that you want to delete:");
				serviceComputer.deleteCompany(sc.nextLong());
				System.out.println("\n");
				break;
			}

		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/SpringContext-console.xml");
		Console console = context.getBean(Console.class);

		console.launch();

	}

}
