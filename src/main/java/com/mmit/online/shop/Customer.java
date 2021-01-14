package com.mmit.online.shop;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity

@Table (name = "customers")
@NamedQuery (name = "Customer.getAll", query = "SELECT c FROM Customer c")
@NamedQuery (name = "Customer.findByName",query = "SELECT c FROM Customer c WHERE c.name= :custName ")
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	@Column(name = "customer_name")
	private String name;
	private String password;
	private String phone;

	public Customer() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	   
}
