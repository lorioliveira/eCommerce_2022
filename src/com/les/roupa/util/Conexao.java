package com.les.roupa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe para fazer a conexão com o banco de dados
 *  @author Lorena Oliveira
 *  18/02/2022
 *  
 */
public class Conexao {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/ecommerce?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String password = "12345";
		
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, user, password);
		
		return conn;
	}
	
}
