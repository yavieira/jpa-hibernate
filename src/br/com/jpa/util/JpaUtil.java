package br.com.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("banco");
	
	public EntityManager getEntityManager() {
		
		return emf.createEntityManager();
	}
}
