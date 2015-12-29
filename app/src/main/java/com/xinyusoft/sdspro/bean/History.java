package com.xinyusoft.sdspro.bean;

public class History {
	private String stockName;
	private String stockCode;
	private int AllCount;
	private int jiaoyiliang;
	private float yikui;
	
	public History() {
		// TODO Auto-generated constructor stub
	}

	public History(String stockName, String stockCode, int allCount, int jiaoyiliang, float yikui) {
		super();
		this.stockName = stockName;
		this.stockCode = stockCode;
		AllCount = allCount;
		this.jiaoyiliang = jiaoyiliang;
		this.yikui = yikui;
	}

	public float getYikui() {
		return yikui;
	}

	public void setYikui(float yikui) {
		this.yikui = yikui;
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

	public int getAllCount() {
		return AllCount;
	}

	public void setAllCount(int allCount) {
		AllCount = allCount;
	}

	public int getJiaoyiliang() {
		return jiaoyiliang;
	}

	public void setJiaoyiliang(int jiaoyiliang) {
		this.jiaoyiliang = jiaoyiliang;
	}

}
