package com.sidie88.IndocyberTest.services.impl;

import com.sidie88.IndocyberTest.entity.InvoiceDetails;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InvoiceDetailsDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<InvoiceDetails> queryAll() {
		Query query = em.createQuery("SELECT i FROM InvoiceDetails i");
		List<InvoiceDetails> result = query.getResultList();
		return result;
	}

	@Transactional(readOnly = true)
	public InvoiceDetails get(Long id) {
		return em.find(InvoiceDetails.class, id);
	}

	@Transactional
	public InvoiceDetails save(InvoiceDetails p) {
		em.persist(p);
		return p;
	}

	@Transactional
	public void delete(InvoiceDetails p) {
		InvoiceDetails r = get(p.getIdDetail());
		if(r != null) {
			em.remove(r);
		}
	}
	

}
