package com.les.roupa.core.domain;

/**
 * Classe para representar a Forma de Pagamento
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public class FormaDePagamento extends Carrinho{
	private String tp_pagamento;
	

	public String getTp_pagamento() {
		return tp_pagamento;
	}

	public void setTp_pagamento(String tp_pagamento) {
		this.tp_pagamento = tp_pagamento;
	}
}
