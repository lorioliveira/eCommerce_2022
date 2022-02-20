package com.les.roupa.util.teste;

import java.sql.Connection;
import java.sql.SQLException;

import com.les.roupa.util.Conexao;

/**
 * Classe para testar a conex�o com o banco de dados
 */
public class TestaConexao {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection connection = new Conexao().getConnection();
		System.out.println("Conex�o aberta!");
		connection.close();
	}
	
}
