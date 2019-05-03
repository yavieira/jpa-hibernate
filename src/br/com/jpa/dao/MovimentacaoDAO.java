package br.com.jpa.dao;

import javax.persistence.EntityManager;

import br.com.jpa.model.Conta;
import br.com.jpa.model.Movimentacao;
import br.com.jpa.util.JpaUtil;

public class MovimentacaoDAO {

	public void addMovimentacao(Movimentacao mov, Conta conta) {
		EntityManager em = new JpaUtil().getEntityManager();
		em.getTransaction().begin();
		conta = em.find(Conta.class, conta.getId());

		em.persist(conta);
		em.persist(mov);

		em.getTransaction().commit();
		em.close();
	}
}
