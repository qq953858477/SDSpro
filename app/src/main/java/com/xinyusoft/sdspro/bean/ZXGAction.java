package com.xinyusoft.sdspro.bean;

public class ZXGAction {
	private float price;
	private String sz;
	private String symbol;
	private String name;

	public String getSz() {
		return sz;
	}

	public void setSz(String sz) {
		this.sz = sz;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ZXGAction(float price, String sz, String symbol, String name) {
		super();
		this.price = price;
		this.sz = sz;
		this.symbol = symbol;
		this.name = name;
	}

}
