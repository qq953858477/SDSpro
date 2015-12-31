package com.xinyusoft.sdspro.bean;

/**
 * Created by zzy on 2015/12/30.
 */
public class AutoCompleteStock {

    private String name;
    private String symbol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public AutoCompleteStock() {

    }
    public AutoCompleteStock(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * 这个重写toString是为了方便获得adapter的getItem的股票代码
     * @return
     */
    @Override
    public String toString() {
        return symbol;
    }
}
