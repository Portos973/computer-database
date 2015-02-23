/**
 * @author Anderson F.
 * Description: Classe Console permet de lancer la connection et d'effectuer divers transactions 
 * */

package com.excilys.formation.project.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.excilys.formation.project.beans.Company;
import com.excilys.formation.project.dao.CompanyDAO;
import com.excilys.formation.project.dao.ComputerDAO;
import com.excilys.formation.project.dao.ConnectionDAO;

public class Console {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		ComputerDAO c = new ComputerDAO(ConnectionDAO.getInstance());
		CompanyDAO cc = new CompanyDAO(ConnectionDAO.getInstance());
		ArrayList<Company> list = new ArrayList<Company>();

		while (true) {
			System.out.println("Bonjour, veuillez faire un choix: ");
			System.out.println(" 1: Liste des ordinateurs");
			System.out.println(" 2: Liste des entreprises");
			System.out.println(" 3: Détails d'un ordinateur");
			System.out.println(" 4: Créer un ordinateur");
			System.out.println(" 5: Mettre à jour les données ordinateur");
			System.out.println(" 6: Supprimer un ordinateur");

			switch (sc.nextInt()) {
			case 1:
				c.computers();
				System.out.println("\n");
				break;
			case 2:
				list = cc.companies();
				System.out.println("\n");
				break;
			case 3:
				System.out.println(" Entrez l'id de cette ordinateur:");
				c.details(sc.nextLong());
				System.out.println("\n");
				break;
			case 4:
				System.out.println(" Entrez un nom *: ");
				String n = sc.next();

				System.out.println(" Entrez une date: (format: AAAA-MM-JJ) ");
				String d = sc.next();

				String regex = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(d);

				while (!matcher.matches()) {
					System.out
							.println(" Veuillez entre la date au bon format svp: (format: AAAA-MM-JJ)");
					String d1 = sc.next();
					d = d1;
					matcher = pattern.matcher(d);

				}
				System.out.println(" Entrez l'id de la compagnie: ");
				Long cid = sc.nextLong();
				boolean bool = false;

				for (int i = 0; i < list.size(); i++)
					if (list.get(i).getId() == cid)
						bool = true;
				if (!bool) {
					
					c.create(null, n, d);
					System.out.println("\n");
				}else
					c.create(cid, n, d);
					System.out.println("\n");


				break;

			case 5:
				System.out.println(" 1: Mettre à jour company_id, nom et date ");
				System.out.println(" 2: Mettre à jour company_id et nom ");
				System.out.println(" 3: Mettre à jour company_id et date");
				
				break;

			case 6:
				System.out
						.println(" Entrez l'id de l'ordinateur que vous voulez supprimer:");
				c.delete(sc.nextLong());
				System.out.println("\n");
				break;
			}

		}
	}

}
