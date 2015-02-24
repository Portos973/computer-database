/**
 * @author Anderson F.
 * Description: Classe ConnectionDAO permet de gérer la connection à la base
 * */

package com.excilys.formation.project.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public enum ConnectionDAO {
	INSTANCE;
	
	private static final String FICHIER_PROPERTIES = "com/excilys/formation/project/dao/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

	private String url;
	private String username;
	private String password;

	private ConnectionDAO() {

		this.url = null;
		this.username = null;
		this.password = null;

	}

	/*
	 * 
	 * Méthode chargée de récupérer les informations de connexion à la base de
	 * 
	 * données, charger le driver JDBC et retourner une instance de la Factory
	 */


	public static ConnectionDAO getInstance() throws Exception {
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new Exception( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
        } catch ( IOException e ) {
            throw new Exception( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }
        
        try {
            Class.forName( driver );
            
        } catch ( ClassNotFoundException e ) {
            throw new Exception( "Le driver est introuvable dans le classpath.", e );
        }

        INSTANCE.url=url;
        INSTANCE.username=nomUtilisateur;
		INSTANCE.password=motDePasse;
		

		return INSTANCE;

	}

	/* Méthode chargée de fournir une connexion à la base de données */

	Connection getConnection() throws SQLException {

		return DriverManager.getConnection(url, username, password);

	}

	/* Méthodes de récupération de l'implémentation des différents DAO */

	public CompanyDAO getCompanyDAO() {

		return new CompanyDAO(this);

	}

	public ComputerDAO getComputerDao() {

		return new ComputerDAO(this);

	}

}
