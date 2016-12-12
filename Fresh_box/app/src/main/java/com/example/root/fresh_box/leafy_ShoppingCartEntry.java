package com.example.root.fresh_box;

public class leafy_ShoppingCartEntry {
	
	private leafy_Product mProduct;
	private int mQuantity;
	
	public leafy_ShoppingCartEntry(leafy_Product product, int quantity) {
		mProduct = product;
		mQuantity = quantity;
	}
	
	public leafy_Product getProduct() {
		return mProduct;
	}
	
	public int getQuantity() {
		return mQuantity;
	}
	
	public void setQuantity(int quantity) {
		mQuantity = quantity;
	}

}
