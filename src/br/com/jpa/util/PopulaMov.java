package br.com.jpa.util;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.jpa.enums.TipoMovimentacaoEnum;
import br.com.jpa.model.Conta;
import br.com.jpa.model.Movimentacao;

public class PopulaMov {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setId(2);
		
		Movimentacao mov = new Movimentacao();
		mov.setTipo(TipoMovimentacaoEnum.ENTRADA);
		mov.setDescricao("Salário");
		mov.setValor(new BigDecimal("6700.0"));
		mov.setConta(conta);

		Movimentacao mov2 = new Movimentacao();
		mov2.setTipo(TipoMovimentacaoEnum.SAIDA);
		mov2.setDescricao("Contas Fixas");
		mov2.setValor(new BigDecimal("2000.0"));
		mov2.setConta(conta);

		Movimentacao mov3 = new Movimentacao();
		mov3.setTipo(TipoMovimentacaoEnum.SAIDA);
		mov3.setDescricao("Presentes");
		mov3.setValor(new BigDecimal("1000.0"));
		mov3.setConta(conta);

		EntityManager em = new JpaUtil().getEntityManager();
		em.getTransaction().begin();
		
		em.persist(mov);
		em.persist(mov2);
		em.persist(mov3);
		
		em.getTransaction().commit();
		em.close();
	}
}
