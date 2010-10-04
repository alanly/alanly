/**
 * Contains the back-end logic required to connect to and perform operations on a database for the Java Appointment Manager.
 */
package org.alanly.jam.da;

import java.sql.Connection;

/**
 * The <code>DbConnector</code> is responsible for handling database connections and operations.
 * 
 * @author Alan Ly
 * @version 1.0
 */
public abstract class DbConnector {
	
	/**
	 * Database connector details
	 */
	private final String DBMS_CONNECTOR = "jdbc";
	private final String DBMS_TYPE = "mysql";
	
	/**
	 * Database connection details
	 */
	private final String DB_HOST = "waldo.dawsoncollege.qc.ca";
	private final String DB_PORT = "3306";
	private final String DB_NAME = "";
	private final String DB_USER = "A830207";
	private final String DB_PASS = "";
	
	private Connection dbConnection;
	
	public DbConnector() {
		super();
	}
	
}
