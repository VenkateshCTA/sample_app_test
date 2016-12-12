package com.example.root.fresh_box;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ex_CatalogActivity extends Activity {

	private List<ex_Product> mProductList;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ex_catalog);
		
		// Obtain a reference to the product catalog
		mProductList = ex_ShoppingCartHelper.getCatalog(getResources());
		
		// Create the list
		ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
		listViewCatalog.setAdapter(new ex_ProductAdapter(mProductList, getLayoutInflater(), false));
		
		listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent productDetailsIntent = new Intent(getBaseContext(),ex_ProductDetailsActivity.class);
				productDetailsIntent.putExtra(ex_ShoppingCartHelper.PRODUCT_INDEX, position);
				startActivity(productDetailsIntent);
			}
		});
		
		Button viewShoppingCart = (Button) findViewById(R.id.ButtonViewCart);
		viewShoppingCart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent viewShoppingCartIntent = new Intent(getBaseContext(), ex_ShoppingCartActivity.class);
				startActivity(viewShoppingCartIntent);
			}
		});

	}
}