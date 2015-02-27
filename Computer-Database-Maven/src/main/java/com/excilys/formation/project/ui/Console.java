/**
 * @author Anderson F.
 * Description: Classe Console permet de lancer la connection et d'effectuer divers transactions 
 * */

package com.excilys.formation.project.ui;

import java.util.Scanner;

import com.excilys.formation.project.controller.Controller;


public class Console {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		Controller c=new Controller();

		while (true) {
			System.out.println("Hello, make a choice: ");
			System.out.println(" 1: List of computers");
			System.out.println(" 2: List of companies");
			System.out.println(" 3: Details of one computer");
			System.out.println(" 4: Insert one computer");
			System.out.println(" 5: Updated the data of one computer");
			System.out.println(" 6: Delete one computer");

			switch (sc.nextLine()) {
			case "1":
				c.computers();
				System.out.println("\n");
				break;
				
			case "2":
				c.companies();
				System.out.println("\n");
				break;
				
			case "3":
				System.out.println(" Enter the cumputer's id :");
				c.details(sc.nextLong());
				System.out.println("\n");
				break;
				
			case "4":
				System.out.println(" Enter one name *: ");
				String n = sc.nextLine();

				System.out.println(" Enter one introduced  date: (format: AAAA-MM-JJ) ");
				String d = sc.nextLine();


				while (!c.checkDate(d) && d.length() != 0) {
					System.out.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String d1 = sc.nextLine();
					d = d1;

				}
								
				System.out.println(" Enter one discontinued  date: (format: AAAA-MM-JJ) ");
				String f = sc.nextLine();


				while (!c.checkDate(f) && f.length() != 0) {
					System.out.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String f1 = sc.nextLine();
					f = f1;

				}
				
				System.out.println(" Enter the company's id: ");
				Long cid = sc.nextLong();

				c.create(cid, n, d, f);


				break;

			case "5":
				System.out.println(" Enter the cumputer's id that you want to update:");
				Long id=new Long(sc.nextLine());
				
				System.out.println(" Enter one name *:");
				String name = sc.nextLine();

				System.out.println(" Enter one date: (format: AAAA-MM-JJ) ");
				String date = sc.nextLine();

				while (!c.checkDate(date) && date.length() != 0) {
					System.out.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String d1 = sc.nextLine();
					date = d1;

				}
				
				System.out.println(" Enter one discontinued  date: (format: AAAA-MM-JJ) ");
				String fin = sc.nextLine();


				while (!c.checkDate(fin) && fin.length() != 0) {
					System.out.println(" Enter the date with a good format, please: (format: AAAA-MM-JJ)");
					String f1 = sc.nextLine();
					fin = f1;

				}
				
				System.out.println(" Enter the company's id: ");
				Long compId = new Long(sc.nextLong());
				
				c.update(id,compId, name, date, fin);
				
				break;

			case "6":
				System.out.println(" Enter the cumputer's id that you want to delete:");
				c.delete(sc.nextLong());
				System.out.println("\n");
				break;
			}

		}
	}

}
