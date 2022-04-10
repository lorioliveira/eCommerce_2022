package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.VerificaCupom;

public class VerificaCupomDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Verifica Cupom
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Salvar
	
	
	/**
	 * Metodo para alterar o Verifica Cupom
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir o Verifica Cupom
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para Consultar o Verifica Cupom
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		VerificaCupom verificaCupomEntidade = (VerificaCupom) entidade;
		List<EntidadeDominio> listVerificaCupom = new ArrayList<>();
		VerificaCupom novoVerificaCupom = new VerificaCupom();
		
		CupomDAO cupomDAO = new CupomDAO();
		
		// busca todos os Cupons no sistema com o mesmo nome que foi digitado na tela
		List<Cupom> cupons = cupomDAO.consultarCupomByNome(verificaCupomEntidade.getCupom().getNome());
		
		novoVerificaCupom.setNomeCupons(cupons);
		
		listVerificaCupom.add(novoVerificaCupom);
		
		return listVerificaCupom;
	} // Consultar
	
}