package br.com.jpa.test;

import javax.persistence.EntityManager;

import br.com.jpa.model.Conta;
import br.com.jpa.util.JpaUtil;

public class TestMovRelacionamento {

	public static void main(String[] args) {
		
		EntityManager em = new JpaUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = em.find(Conta.class, 2);
		System.out.println(conta.getMovimentacoes());
	}
}
