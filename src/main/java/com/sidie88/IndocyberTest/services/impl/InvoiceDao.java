package com.sidie88.IndocyberTest.services.impl;

import com.sidie88.IndocyberTest.entity.Invoice;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InvoiceDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Invoice> queryAll() {
		Query query = em.createQuery("SELECT i FROM Invoice i");
		List<Invoice> result = query.getResultList();
		return result;
	}

	@Transactional(readOnly = true)
	public Invoice get(Long id) {
		return em.find(Invoice.class, id);
	}

	@Transactional
	public Invoice save(Invoice p) {
		em.persist(p);
		return p;
	}

	@Transactional
	public void delete(Invoice p) {
		Invoice r = get(p.getInvoiceId());
		if(r != null) {
			em.remove(r);
		}
	}
	

}
