package com.example.root.fresh_box;

public class ex_ShoppingCartEntry {
	
	private ex_Product mProduct;
	private int mQuantity;
	
	public ex_ShoppingCartEntry(ex_Product product, int quantity) {
		mProduct = product;
		mQuantity = quantity;
	}
	
	public ex_Product getProduct() {
		return mProduct;
	}
	
	public int getQuantity() {
		return mQuantity;
	}
	
	public void setQuantity(int quantity) {
		mQuantity = quantity;
	}

}
