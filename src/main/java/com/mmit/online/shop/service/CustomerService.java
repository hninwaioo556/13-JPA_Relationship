package com.mmit.online.shop.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.mmit.online.shop.Customer;

public class CustomerService {
	
	private EntityManager em;
	
	public CustomerService (EntityManager em) {
		this.em=em;
	}

	public void createQueryInterface () {
		// Query interface (dynamic query)
		Query query1 = em.createQuery("SELECT c FROM Customer c "); // not recommended
				
		// TypedQuery interface (dynamic query) (recommended)
		TypedQuery<Customer> query2 = em.createQuery("SELECT c FROM Customer c", Customer.class);
				
		// Query interface (static query)
		Query query3 = em.createNamedQuery("Customer.getAll"); // not recommended
				
		// TypedQuery interface (static query) (recommended)
		TypedQuery<Customer> query4 = em.createNamedQuery("Customer.getAll", Customer.class);
			
	}
	
	public List<Customer> getCustomerNames() {
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c ORDER BY c.name DESC", Customer.class);
		
		List<Customer> list = query.getResultList();
		
		return list;
	}

	public List<Customer> findByName(String na) {
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findByName", Customer.class);
		
		query.setParameter("custName", na);
		return query.getResultList();
	}

	public long getNoOfCustomers() {
		TypedQuery<Long> query=em.createQuery("SELECT COUNT (c) FROM Customer c", Long.class); 
		return query.getSingleResult();
		
	}
	
	public List<Customer> findByRange(int i, int j) {
		
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.id >= :from AND c.id <= :to", Customer.class);
		query.setParameter("from", i);
		query.setParameter("to", j);
		return query.getResultList();
	}
	
	public List<String> findByEmailStartWith(String start) {
		TypedQuery<String> query = em.createQuery("SELECT c.email FROM Customer c WHERE c.email LIKE :email" ,String.class);
		//System.out.println("start: "+start.concat("%"));
		query.setParameter("email", start.concat("%"));
		
		return query.getResultList();
	}
	
	public List<String> searchwithLikeOperator (String keyword) {
		return em.createQuery("SELECT c.name FROM Customer c WHERE c.name LIKE :na",String.class)
				.setParameter("nam", "%".concat(keyword).concat("%"))
				.getResultList();
		
	}
	
	public void updateEmail (String newEmail, int id) {
		em.getTransaction().begin();
		
//		em.createQuery("UPDATE Customer c SET c.email = :email WHERE c.id = :id")
//		.setParameter("email", newEmail)
//		.setParameter("id", id)
//		.executeUpdate();
		
		Customer c = em.find(Customer.class, id); //manage state
		
		//c.setEmail(newEmail);
		em.remove(c);
		
		em.getTransaction().commit();
	}

	public Customer findById(int i) {
//		TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer c WHERE c.id=:id",Customer.class);
//		q.setParameter("id", i);//		
//		return q.getSingleResult();
		
		//Customer c = em.find(Customer.class, i);
		
		return em.find(Customer.class, i);
	}
	
}
