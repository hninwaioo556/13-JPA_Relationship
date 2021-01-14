package com.mmit.online.shop.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.mmit.online.shop.Category;

public class CategoryService {
	
	private EntityManager em;
	
	public CategoryService (EntityManager em) {
		this.em=em;
	}
	
	public void createQueryInterface () {
		// Query interface (dynamic query)
		Query query1 = em.createQuery("SELECT cat FROM Category cat "); // not recommended
				
		// TypedQuery interface (dynamic query) (recommended)
		TypedQuery<Category> query2 = em.createQuery("SELECT cat FROM Category cat", Category.class);
				
		// Query interface (static query)
		Query query3 = em.createNamedQuery("Category.getAll"); // not recommended
				
		// TypedQuery interface (static query) (recommended)
		TypedQuery<Category> query4 = em.createNamedQuery("Category.getAll", Category.class);
			
	}
	
	public List<Category> findByIds() {
		TypedQuery<Category> query = em.createQuery("SELECT cat FROM Category cat WHERE id=1",Category.class);
		
		List<Category> list = query.getResultList();
		
		return list;
	}
	
	public List<Category> getCategoryNames() {
		TypedQuery<Category> query = em.createQuery("SELECT cat FROM Category cat ORDER BY cat.name DESC",Category.class);
		
		List<Category> list = query.getResultList();
		
		return list;
	}
	
	public List<Category> findByMaxID () {
		TypedQuery<Category> query = em.createQuery("SELECT cat FROM Category cat WHERE id = ( SELECT MAX( id ) FROM Category cat )",Category.class);
		
		List<Category> list = query.getResultList();
		
		return list;
	}

	public List<String> findByNameEndWith(String end) {
		TypedQuery<String> query = em.createQuery("SELECT cat.name FROM Category cat WHERE cat.name LIKE :name" , String.class);
		query.setParameter("name", end.concat("s"));
		
		return query.getResultList();
	}

	public void updateName(String newName, int id) {
		em.getTransaction().begin();
		
		Category cat = em.find(Category.class, id);
		//cat.setName(newName);//update
		em.remove(cat);//remove
		
		em.getTransaction().commit();
	}

	public Category findByIds(int i) {
		
		return em.find(Category.class, i);
	}


	

	
	
}
