package com.example.root.fresh_box;

public class frut_ShoppingCartEntry {
	
	private frut_Product mProduct;
	private int mQuantity;
	
	public frut_ShoppingCartEntry(frut_Product product, int quantity) {
		mProduct = product;
		mQuantity = quantity;
	}
	
	public frut_Product getProduct() {
		return mProduct;
	}
	
	public int getQuantity() {
		return mQuantity;
	}
	
	public void setQuantity(int quantity) {
		mQuantity = quantity;
	}

}
