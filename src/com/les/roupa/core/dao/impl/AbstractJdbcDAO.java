package com.les.roupa.core.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.les.roupa.core.dao.IDAO;
import com.les.roupa.util.Conexao;

/**
 * Classe abstrata AbstractJdbcDAO, para abrir a conexão com o banco de dados.
 * @author Lorena Oliveira
 */

public abstract class AbstractJdbcDAO implements IDAO {
	
	protected Connection connection;
	
	protected void openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
            	connection = Conexao.getConnection();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
