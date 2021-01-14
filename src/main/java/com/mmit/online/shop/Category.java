package com.mmit.online.shop;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.REMOVE;

@Entity

@Table (name = "categories")
@NamedQuery (name = "Category.getAll", query = "SELECT cat FROM Category cat")
@NamedQuery (name = "Category.findByIds",query = "SELECT cat FROM Category cat WHERE cat.id= :catId")
public class Category implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "category_name")
	private String name;
	
	@OneToMany(mappedBy = "category", 
			cascade = REMOVE, orphanRemoval = true)
	private List<Item> itemList;
	
	public Category() {
		super();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	
   
}
