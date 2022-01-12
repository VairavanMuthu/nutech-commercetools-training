package com.commercetools.model;

public class Cart {

    private String id;
    private String cartState;
    private String currencyCode;

    
    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCartState() {
		return cartState;
	}


	public void setCartState(String cartState) {
		this.cartState = cartState;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	@Override
    public String toString() {
        return "Employee [Id=" + id + ", cartState=" + cartState + ", currencyCode=" + currencyCode + "]";
    }

}