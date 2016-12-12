package com.example.root.fresh_box;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.content.res.Resources;

public class 	frut_ShoppingCartHelper {
	
	public static final String PRODUCT_INDEX = "PRODUCT_INDEX";
	
	private static List<frut_Product> catalog;
	private static Map<frut_Product, frut_ShoppingCartEntry> cartMap = new HashMap<frut_Product, frut_ShoppingCartEntry>();
	
	public static List<frut_Product> getCatalog(Resources res){
		if(catalog == null) {
			catalog = new Vector<frut_Product>();
			catalog.add(new frut_Product("Tomato", res.getDrawable(R.drawable.veg),"", 29.99));
			catalog.add(new frut_Product("Switch", res.getDrawable(R.drawable.leafy),"", 24.99));
			catalog.add(new frut_Product("Watchmen", res.getDrawable(R.drawable.frrr),"", 14.99));
		}
		
		return catalog;
	}
	
	public static void setQuantity(frut_Product product, int quantity) {
		// Get the current cart entry
		frut_ShoppingCartEntry curEntry = cartMap.get(product);
		
		// If the quantity is zero or less, remove the products
		if(quantity <= 0) {
			if(curEntry != null)
				removeProduct(product);
			return;
		}
		
		// If a current cart entry doesn't exist, create one
		if(curEntry == null) {
			curEntry = new frut_ShoppingCartEntry(product, quantity);
			cartMap.put(product, curEntry);
			return;
		}
		
		// Update the quantity
		curEntry.setQuantity(quantity);
	}
	
	public static int getProductQuantity(frut_Product product) {
		// Get the current cart entry
		frut_ShoppingCartEntry curEntry = cartMap.get(product);
		
		if(curEntry != null)
			return curEntry.getQuantity();
		
		return 0;
	}
	
	public static void removeProduct(frut_Product product) {
		cartMap.remove(product);
	}
	
	public static List<frut_Product> getCartList() {
		List<frut_Product> cartList = new Vector<frut_Product>(cartMap.keySet().size());
		for(frut_Product p : cartMap.keySet()) {
			cartList.add(p);
		}
		
		return cartList;
	}
	

}
