package com.mmit.online.shop;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity

public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int price;
	private LocalDate expiredate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderdetailList;
	
	public Item() {
		super();
	}
   
}
