package com.xinyusoft.sdspro.bean;

public class Stock {

	private String stockName;
	private String stockCode;
	private float stockAllCount;
	private float stockShiZhi;
	private float stockJiaoYiLiang;
	private float stockYiKui;

	public Stock() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Stock(String stockName, String stockCode, float stockAllCount, float stockShiZhi, float stockJiaoYiLiang, float stockYiKui) {
		super();
		this.stockName = stockName;
		this.stockCode = stockCode;
		this.stockAllCount = stockAllCount;
		this.stockShiZhi = stockShiZhi;
		this.stockJiaoYiLiang = stockJiaoYiLiang;
		this.stockYiKui = stockYiKui;
	}



	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public float getStockAllCount() {
		return stockAllCount;
	}

	public void setStockAllCount(float stockAllCount) {
		this.stockAllCount = stockAllCount;
	}

	public float getStockShiZhi() {
		return stockShiZhi;
	}

	public void setStockShiZhi(float stockShiZhi) {
		this.stockShiZhi = stockShiZhi;
	}

	public float getStockJiaoYiLiang() {
		return stockJiaoYiLiang;
	}

	public void setStockJiaoYiLiang(float stockJiaoYiLiang) {
		this.stockJiaoYiLiang = stockJiaoYiLiang;
	}

	public float getStockYiKui() {
		return stockYiKui;
	}

	public void setStockYiKui(float stockYiKui) {
		this.stockYiKui = stockYiKui;
	}

}
