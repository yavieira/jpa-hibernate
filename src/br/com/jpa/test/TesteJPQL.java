package br.com.jpa.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jpa.enums.TipoMovimentacaoEnum;
import br.com.jpa.model.Conta;
import br.com.jpa.model.Movimentacao;
import br.com.jpa.util.JpaUtil;

public class TesteJPQL {

	public static void main(String[] args) {

		EntityManager em = new JpaUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);

		String jpql = "select m from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacaoEnum.SAIDA);

		@SuppressWarnings("unchecked")
		List<Movimentacao> results = query.getResultList();

		if (results != null) {
			for (Movimentacao movimentacao : results) {
				System.out.println("Tipo: " + movimentacao.getTipo());
				System.out.println("Conta id: " + movimentacao.getConta().getId());
			}
		} else {
			System.out.println("Lista vazia");
		}
		em.getTransaction().commit();
		em.close();
	}
}
