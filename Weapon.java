//package org.leadedpencilinc.TheBestDnDGameYouWillEverPlay.Items;

import java.util.ArrayList;

public class Weapon extends Item {
	private ArrayList<String> properties;
	private String dmgType;
	private boolean ranged;
	private int normalRange;
	private int maxRange;
	private int numD4;
	private int numD6;
	private int numD8;
	private int numD10;
	private int numD12;
	private boolean oneDmg;
	
	public Weapon(String n) {
		super(n);
	}

	public Weapon(String itemName, String itemDescription, double itemValue, double itemWeight, int itemQuantity,
			boolean isRanged, int normalRangeInt, int maxRangeInt, int d4Int, int d6Int, int d8Int, int d10Int,
			int d12Int, ArrayList<String> itemProperties) {
		super(itemName, itemDescription, itemValue, itemWeight, itemQuantity);
		
		ranged = isRanged;
		normalRange = normalRangeInt;
		maxRange = maxRangeInt;
		numD4 = d4Int;
		numD6 = d6Int;
		numD8 = d8Int;
		numD10 = d10Int;
		numD12 = d12Int;
		properties = itemProperties;
		type = "Weapon";
	}

	public ArrayList<String> getProperties() {
		return properties;
	}

	public void setProperties(ArrayList<String> properties) {
		this.properties = properties;
	}

	public String getDmgType() {
		return dmgType;
	}

	public void setDmgType(String dmgType) {
		this.dmgType = dmgType;
	}

	public boolean isRanged() {
		return ranged;
	}

	public void setRanged(boolean ranged) {
		this.ranged = ranged;
	}

	public int getNormalRange() {
		return normalRange;
	}

	public void setNormalRange(int normalRange) {
		this.normalRange = normalRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}

	public int getNumD4() {
		return numD4;
	}

	public void setNumD4(int numD4) {
		this.numD4 = numD4;
	}

	public int getNumD6() {
		return numD6;
	}

	public void setNumD6(int numD6) {
		this.numD6 = numD6;
	}

	public int getNumD8() {
		return numD8;
	}

	public void setNumD8(int numD8) {
		this.numD8 = numD8;
	}

	public int getNumD10() {
		return numD10;
	}

	public void setNumD10(int numD10) {
		this.numD10 = numD10;
	}

	public int getNumD12() {
		return numD12;
	}

	public void setNumD12(int numD12) {
		this.numD12 = numD12;
	}

	public boolean isOneDmg() {
		return oneDmg;
	}

	public void setOneDmg(boolean oneDmg) {
		this.oneDmg = oneDmg;
	}
	
	public String toString() {
		
		
		return "";
	}
	
}
