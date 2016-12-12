package com.example.root.fresh_box;

public class ShoppingCartEntry {
	
	public Product mProduct;
	public int mQuantity;
	
	public ShoppingCartEntry(Product product, int quantity) {
		mProduct = product;
		mQuantity = quantity;
	}
	
	public Product getProduct() {
		return mProduct;
	}
	
	public int getQuantity() {
		return mQuantity;
	}
	
	public void setQuantity(int quantity) {
		mQuantity = quantity;
	}

}
