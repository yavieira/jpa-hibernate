package br.com.jpa.test;

import br.com.jpa.model.Conta;
import br.com.jpa.model.Titular;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteConta {

    public static void main(String[] args) {

        Conta conta = new Conta();
        Titular titular = new Titular();
        titular.setNome("Yuri");
        conta.setTitular(titular);
        conta.setBanco("Caixa Economica");
        conta.setAgencia("123");
        conta.setNumero("456");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banco");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(titular);
        em.persist(conta);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
