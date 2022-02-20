package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;

import java.util.InputMismatchException;

import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo CPF do cliente
 * 
 */
public class ValidarCPF implements IStrategy {
	
	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		String CPF = cliente.getCpf();
				
					if(cliente.getCpf() == null || cliente.getCpf().equals("")) {
						return ("Insira um CPF.<br>");
					}
					else if (cliente.getCpf().length() < 11) {
						return ("Insira um CPF com no minimo 11 caracteres.<br>");
					}
					else {
						
						// considera-se erro CPF's formados por uma sequencia de numeros iguais
				        if (CPF.equals("00000000000") ||
				            CPF.equals("11111111111") ||
				            CPF.equals("22222222222") || CPF.equals("33333333333") ||
				            CPF.equals("44444444444") || CPF.equals("55555555555") ||
				            CPF.equals("66666666666") || CPF.equals("77777777777") ||
				            CPF.equals("88888888888") || CPF.equals("99999999999") ||
				            (CPF.length() != 11))
				            return("CPF invalido !"+ "<br>");
			
				        char dig10, dig11;
				        int sm, i, r, num, peso;
			
				        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
				        try {
				        // Calculo do 1o. Digito Verificador
				            sm = 0;
				            peso = 10;
				            for (i=0; i<9; i++) {
				        // converte o i-esimo caractere do CPF em um numero:
				        // por exemplo, transforma o caractere '0' no inteiro 0
				        // (48 eh a posicao de '0' na tabela ASCII)
				            num = (int)(CPF.charAt(i) - 48);
				            sm = sm + (num * peso);
				            peso = peso - 1;
				            }
			
				            r = 11 - (sm % 11);
				            if ((r == 10) || (r == 11))
				                dig10 = '0';
				            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
			
				        // Calculo do 2o. Digito Verificador
				            sm = 0;
				            peso = 11;
				            for(i=0; i<10; i++) {
				            num = (int)(CPF.charAt(i) - 48);
				            sm = sm + (num * peso);
				            peso = peso - 1;
				            }
			
				            r = 11 - (sm % 11);
				            if ((r == 10) || (r == 11))
				                 dig11 = '0';
				            else dig11 = (char)(r + 48);
			
				            // Verifica se os digitos calculados conferem com os digitos informados.
				            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				                 return(null);
				            else 
				            	return("CPF invalido! Insira um CPF válido <br>");
				        } catch (InputMismatchException erro) {
				        	return("CPF invalido! Insira um CPF válido<br>");
				        }
					}
			}

		}