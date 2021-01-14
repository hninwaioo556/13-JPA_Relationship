package com.mmit.online.shop;

import java.io.Serializable;
import javax.persistence.*;

@Entity

public class OrderDetail implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private int subQty;
	private int subPrice;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	public OrderDetail() {
		super();
	}
   
}
