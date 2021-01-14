package com.mmit.shop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.mmit.online.shop.Customer;
import com.mmit.online.shop.Item;
import com.mmit.online.shop.service.CustomerService;

@TestMethodOrder (OrderAnnotation.class)
class CustomerService_test {	

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	private CustomerService service;
	
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
		service = new CustomerService(em);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}
	
	@Test
	void test() {
		
	}
	
	//@Test
	void updateCustomerEmail() {
		service.updateEmail("yarzarmon123@gmail.com", 1);
		Customer cust=service.findById(1);
//		assertEquals("yzm@gmail.com", cust.getEmail());
	}
	
	//@Test
	void retrieveCustomerName () {
		List<String> list = service.searchwithLikeOperator("w");
		list.forEach(name -> System.out.println(name));
	}
	
	//@Test
	void retrieveCustomerEmail () {
		List<String> list = service.findByEmailStartWith("n");
		list.forEach(c -> {
			System.out.println(c);
		});
	}
	
	//@Test
	void retrieveCustomerCount () {
		long total = service.getNoOfCustomers();
		System.out.println("Total: "+total);
	}
	
	//@Test
	void retrieveCustomerByIds () {
		List<Customer> list = service.findByRange(4,8);
		list.forEach(c-> {
			System.out.println(c.getName()+ "\t" +c.getId());
			
		});
	}
	
	//@Test
	void retrieveCustomers() {
		List<Customer> list =service.findByName("dan shaung");
		if(list!=null)
			System.out.println(list.get(0).getEmail());
	}
	
	//@Test // create = dynamic DESC
	void test_orderby_customer () {
		List<Customer> customerList = service.getCustomerNames();
		
		for (Customer customer : customerList) {
			System.out.println(customer.getName());
		}
	}

	//@Test
	//@Order(1)
	void test_createQuery() {
		service.createQueryInterface();
	}
	
	//@Test
	//@Order(2)
	void test_retrieve_data() {
		// Query interface ( dynamic qry)
		Query query1 = em.createQuery("SELECT c FROM Customer c"); // not recommended
		// Customer c = (Customer) query1.getSingleResult();
		//List<Item> list = query1.getResultList();
		
		TypedQuery<Customer> query2 = em.createQuery("SELECT c FROM Customer c", Customer.class);
		//List<Customer> list = query2.getResultList();
		
		TypedQuery<Customer> query3 = em.createQuery("SELECT c FROM Customer c WHERE c.id=2", Customer.class);
		
		Customer c = query3.getSingleResult(); //1
		
		List<Customer> list = query3.getResultList();
		System.out.println("list" + list.size());
		Customer c1=list.get(0);
		
		assertEquals("12345678", c.getPassword());
				
	}
	

}
