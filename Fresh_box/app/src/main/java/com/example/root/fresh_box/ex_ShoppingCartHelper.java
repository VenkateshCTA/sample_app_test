package com.example.root.fresh_box;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.content.res.Resources;

public class ex_ShoppingCartHelper {
	
	public static final String PRODUCT_INDEX = "PRODUCT_INDEX";
	
	private static List<ex_Product> catalog;
	private static Map<ex_Product, ex_ShoppingCartEntry> cartMap = new HashMap<ex_Product, ex_ShoppingCartEntry>();
	
	public static List<ex_Product> getCatalog(Resources res){
		if(catalog == null) {
			catalog = new Vector<ex_Product>();
			catalog.add(new ex_Product("Tomato", res.getDrawable(R.drawable.veg),"", 29.99));
			catalog.add(new ex_Product("Switch", res.getDrawable(R.drawable.leafy),"", 24.99));
			catalog.add(new ex_Product("Watchmen", res.getDrawable(R.drawable.frrr),"", 14.99));
		}
		
		return catalog;
	}
	
	public static void setQuantity(ex_Product product, int quantity) {
		// Get the current cart entry
		ex_ShoppingCartEntry curEntry = cartMap.get(product);
		
		// If the quantity is zero or less, remove the products
		if(quantity <= 0) {
			if(curEntry != null)
				removeProduct(product);
			return;
		}
		
		// If a current cart entry doesn't exist, create one
		if(curEntry == null) {
			curEntry = new ex_ShoppingCartEntry(product, quantity);
			cartMap.put(product, curEntry);
			return;
		}
		
		// Update the quantity
		curEntry.setQuantity(quantity);
	}
	
	public static int getProductQuantity(ex_Product product) {
		// Get the current cart entry
		ex_ShoppingCartEntry curEntry = cartMap.get(product);
		
		if(curEntry != null)
			return curEntry.getQuantity();
		
		return 0;
	}
	
	public static void removeProduct(ex_Product product) {
		cartMap.remove(product);
	}
	
	public static List<ex_Product> getCartList() {
		List<ex_Product> cartList = new Vector<ex_Product>(cartMap.keySet().size());
		for(ex_Product p : cartMap.keySet()) {
			cartList.add(p);
		}
		
		return cartList;
	}
	

}
