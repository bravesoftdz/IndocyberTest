package com.sidie88.IndocyberTest.services.impl;

import com.sidie88.IndocyberTest.entity.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Product> queryAll() {
		Query query = em.createQuery("SELECT p FROM Product p");
		List<Product> result = query.getResultList();
		return result;
	}

	@Transactional(readOnly = true)
	public Product get(Long id) {
		return em.find(Product.class, id);
	}

	@Transactional
	public Product save(Product p) {
		em.persist(p);
		return p;
	}

	@Transactional
	public void delete(Product p) {
		Product r = get(p.getProductId());
		if(r != null) {
			em.remove(r);
		}
	}
	
	@Transactional(readOnly = true)
	public List<Product> queryByName(String param) {
		Query query = em.createQuery("SELECT p FROM Product p where p.productName LIKE ?1")
				.setParameter(1, param);
		List<Product> result = query.getResultList();
		return result;
	}

}
