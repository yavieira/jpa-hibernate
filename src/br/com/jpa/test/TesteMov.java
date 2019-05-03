package br.com.jpa.test;

import java.math.BigDecimal;

import br.com.jpa.dao.MovimentacaoDAO;
import br.com.jpa.enums.TipoMovimentacaoEnum;
import br.com.jpa.model.Conta;
import br.com.jpa.model.Movimentacao;

public class TesteMov {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setId(2);
		
		Movimentacao mov = new Movimentacao();
		mov.setTipo(TipoMovimentacaoEnum.SAIDA);
		mov.setValor(new BigDecimal("200.0"));
		mov.setDescricao("Viagem");
		mov.setConta(conta);
		
		MovimentacaoDAO dao = new MovimentacaoDAO();
		dao.addMovimentacao(mov, conta);
	}
}
