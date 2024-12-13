package com.KLTN.Service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.KLTN.Entity.Product;
import com.KLTN.Service.CriteriaDemoService;

@Service
public class CriteriaDemoServiceImpl implements CriteriaDemoService{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Product> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> from = cq.from(Product.class);
		
		Predicate price2 = cb.between(from.get("price"), 3000000, 6490000);
		
		cq.where(price2);
		TypedQuery<Product> q = em.createQuery(cq);
		List<Product> allitems = q.getResultList();
		return allitems;
	}

}
