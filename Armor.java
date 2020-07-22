//package org.leadedpencilinc.TheBestDnDGameYouWillEverPlay.Items;

public class Armor extends Item {

	private int strengthRequirement = 0;
	private int aC;
	private boolean dexMod;
	private int dexMax = 999;
	
	public Armor(String n) {
		super(n);
	}
	
	public Armor(String name, String description, double value, double weight, int quantity, int strengthRq, int armorClass, boolean dexM, int dMax) {
		super(name, description, value, weight, quantity);
		strengthRequirement = strengthRq;
		aC = armorClass;
		dexMod = dexM;
		dexMax = dMax;
		type = "Armor";
	}

	public int getStrengthRequirement() {
		return strengthRequirement;
	}
	public void setStrengthRequirement(int strengthRequirement) {
		this.strengthRequirement = strengthRequirement;
	}
	public int getaC() {
		return aC;
	}
	public void setaC(int aC) {
		this.aC = aC;
	}
	public boolean isDexMod() {
		return dexMod;
	}
	public void setDexMod(boolean dexMod) {
		this.dexMod = dexMod;
	}
	public int getDexMax() {
		return dexMax;
	}
	public void setDexMax(int dexMax) {
		this.dexMax = dexMax;
	}
	
}
