package br.com.jpa.test;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.jpa.enums.TipoMovimentacaoEnum;
import br.com.jpa.model.Conta;
import br.com.jpa.model.Movimentacao;
import br.com.jpa.model.Titular;
import br.com.jpa.util.JpaUtil;

public class TesteMov {

	public static void main(String[] args) {
		
		Titular titular = new Titular();
		titular.setNome("Yuri");

		Movimentacao mov = new Movimentacao();
		mov.setTipo(TipoMovimentacaoEnum.SAIDA);
		mov.setValor(new BigDecimal("200.0"));
		mov.setDescricao("Viagem");

		Conta conta = new Conta();
		conta.setBanco("Itaú");
		conta.setAgencia("1010");
		conta.setNumero("50509-9");
		conta.setTitular(titular);

		mov.setConta(conta);
		
		EntityManager em = (EntityManager) new JpaUtil().getEntityManager();
		em.getTransaction().begin();
		
		em.persist(titular);
		em.persist(mov);
		em.persist(conta);
		em.getTransaction().commit();
		em.close();
	}
}
