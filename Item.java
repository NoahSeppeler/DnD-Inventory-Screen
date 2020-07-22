//package org.leadedpencilinc.TheBestDnDGameYouWillEverPlay.Items;

public class Item {
	private String name;
	private double value;
	private double weight;
	private String description;
	private int quantity;
	public String type;
	
	public Item(String n) {
		name = n;
	}
	
	public Item(String n, String desc) {
		name = n;
		description = desc;
	}
	
	public Item(String name,  String description, double value, double weight, int quantity) {
		super();
		this.name = name;
		this.value = value;
		this.weight = weight;
		this.description = description;
		this.quantity = quantity;
		type = "Item";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		if(this.quantity+quantity < 0) {
			quantity = 0;
		}else {
			this.quantity = quantity;
		}
		
	}
	
	public String toString() {
		return name;
	}
}
