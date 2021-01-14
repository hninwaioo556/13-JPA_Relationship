package com.mmit.online.shop;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity

public class Orders implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate orderdate;
	private int totalBalance;
	private int totalQty;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "order") 
	private List<OrderDetail> orderdetailList;
	
	public Orders() {
		super();
	}
   
}
