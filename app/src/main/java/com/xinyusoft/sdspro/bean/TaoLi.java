package com.xinyusoft.sdspro.bean;

/**
 * Created by ZHOUCHAO on 2016/1/5.
 */
public class TaoLi {
    private String stockName;
    private String stockCode;

    public TaoLi(String stockName){
        this.stockName = stockName;
    }

    public String getStockName(){
        return stockName;
    }
    public void setStockName(String stockName){
        this.stockName = stockName;
    }

    public String getStockCode(){
        return stockCode;
    }
    public void setStockCode(String stockCode){
        this.stockCode = stockCode;
    }
}
