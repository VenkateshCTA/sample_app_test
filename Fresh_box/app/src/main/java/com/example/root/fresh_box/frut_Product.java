package com.example.root.fresh_box;

import android.graphics.drawable.Drawable;

public class frut_Product {

	public String title;
	public Drawable productImage;
	public String description;
	public double price;
	public boolean selected;

	public frut_Product(String title, Drawable productImage, String description,double price) {
		this.title = title;
		this.productImage = productImage;
		this.description = description;
		this.price = price;
	}

}
