package br.com.jpa.dao;

import javax.persistence.EntityManager;

import br.com.jpa.model.Conta;
import br.com.jpa.model.Titular;
import br.com.jpa.util.JpaUtil;

public class ContaDAO {

	public void addConta(Conta conta, Titular titular) {
		
		EntityManager em = new JpaUtil().getEntityManager();
		em.getTransaction().begin();
		
		em.persist(titular);
		em.persist(conta);
		
		em.getTransaction().commit();
		em.close();
	}
}
