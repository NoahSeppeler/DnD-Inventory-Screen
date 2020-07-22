//package org.leadedpencilinc.TheBestDnDGameYouWillEverPlay.Items;


public class WeaponProperty {
	private String name;
	private String description;
	
	public WeaponProperty(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		String s = "";
		
		s+= name+": \n";
		s+= description;
		
		return s;
	}
	
}
