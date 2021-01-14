package com.mmit.shop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.mmit.online.shop.Category;
import com.mmit.online.shop.service.CategoryService;

@TestMethodOrder (OrderAnnotation.class)
class CategoryService_test {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	private CategoryService service;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("jpa-relationship");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		service = new CategoryService(em);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_createQuery() {
		service.createQueryInterface();
	}
	
	//@Test // given ID
	void retrieveCategoryId () {
		List<Category> categoryList = service.findByIds();
		
		for (Category category : categoryList) {
			System.out.println(category.getName());
		}
	}
	
	//@Test //DESC Name
	void test_orderby_category () {
		List<Category> categoryList = service.getCategoryNames();
		
		for (Category category : categoryList) {
			System.out.println(category.getName());
		}
	}
	
	//@Test //find Max ID
	void retrieveMaxId () {
		List<Category> categoryList = service.findByMaxID();
		
		for (Category category : categoryList) {
			System.out.println(category.getId());
		}
	}
	
	//@Test // End With S
	void retrieveCategoryName () {
		List<String> list = service.findByNameEndWith("%s");
		list.forEach(cat -> {
			System.out.println(cat);
		});
	}
	
	// @Test // Update Name
	void updateCategoryName () {
		service.updateName ("Bag" , 5);
		Category cat = service.findByIds(5);
	}

}
