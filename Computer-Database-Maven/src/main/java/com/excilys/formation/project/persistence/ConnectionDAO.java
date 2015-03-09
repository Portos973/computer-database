/**
 * @author Anderson F.
 * Description: Classe ConnectionDAO permet de gérer la connection à la base
 * */

package com.excilys.formation.project.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ConnectionDAO {
	INSTANCE;

	private static final String FICHIER_PROPERTIES = "com/excilys/formation/project/persistence/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";
	private static final String PROPERTY_MIN_CONNECTION = "min";
	private static final String PROPERTY_MAX_CONNECTION = "max";

	private static ThreadLocal<Connection> threadCnx = new ThreadLocal<Connection>();

	public BoneCP connectionPool = null;

	private String url = null;
	private String username = null;
	private String password = null;

	/*
	 * 
	 * Méthode chargée de récupérer les informations de connexion à la base de
	 * 
	 * données, charger le driver JDBC et retourner une instance de la Factory
	 */

	private ConnectionDAO() {

		Properties properties = new Properties();
		String url = null;
		String driver = null;
		String nomUtilisateur = null;
		String motDePasse = null;
		String min = null;
		String max = null;
		BoneCP connectionPool = null;

		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream fichierProperties = classLoader
				.getResourceAsStream(FICHIER_PROPERTIES);

		if (fichierProperties == null) {
			try {
				throw new Exception("Le fichier properties "
						+ FICHIER_PROPERTIES + " est introuvable.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
			motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
			min = properties.getProperty(PROPERTY_MIN_CONNECTION);
			max = properties.getProperty(PROPERTY_MAX_CONNECTION);
		} catch (IOException e) {
			try {
				throw new Exception(
						"Impossible de charger le fichier properties "
								+ FICHIER_PROPERTIES, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			try {
				throw new Exception(
						"Le driver est introuvable dans le classpath.", e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			/*
			 * Création d'une configuration de pool de connexions via l'objet
			 * BoneCPConfig et les différents setters associés.
			 */
			BoneCPConfig config = new BoneCPConfig();
			/* Mise en place de l'URL, du nom et du mot de passe */
			config.setJdbcUrl(url);
			config.setUsername(nomUtilisateur);
			config.setPassword(motDePasse);
			/* Paramétrage de la taille du pool */
			config.setMinConnectionsPerPartition(Integer.parseInt(min));
			config.setMaxConnectionsPerPartition(Integer.parseInt(max));
			config.setPartitionCount(2);
			/* Création du pool à partir de la configuration, via l'objet BoneCP */
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erreur de configuration du pool de connexions."
					+ e);
		}

		this.url = url;
		this.username = nomUtilisateur;
		this.password = motDePasse;
		this.connectionPool = connectionPool;

	}

	/* Méthode chargée de fournir une connexion à la base de données */

	public Connection getConnection() throws SQLException {
		if (threadCnx.get() != null)
			return threadCnx.get();
		else {
			threadCnx.set(ConnectionDAO.INSTANCE.connectionPool.getConnection());
			return threadCnx.get();
		}
	}

	public void closeConnection() throws SQLException {

		Connection connection = threadCnx.get();

		connection.commit();
		connection.close();

		threadCnx.remove();
	}

}
