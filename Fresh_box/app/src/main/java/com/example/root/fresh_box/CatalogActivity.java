package com.example.root.fresh_box;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class CatalogActivity extends AppCompatActivity {

	private List<Product> mProductList;

	/** Called when the activity is first created. */

	android.support.v7.widget.Toolbar toolbar;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.catalog);
		
		// Obtain a reference to the product catalog

		toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		mProductList = ShoppingCartHelper.getCatalog(getResources());
		
		// Create the list
		ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
		listViewCatalog.setAdapter(new ProductAdapter(mProductList, getLayoutInflater(), false));
		
		listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent productDetailsIntent = new Intent(getBaseContext(),ProductDetailsActivity.class);
				productDetailsIntent.putExtra(ShoppingCartHelper.PRODUCT_INDEX, position);
				startActivity(productDetailsIntent);
			}
		});
		
		Button viewShoppingCart = (Button) findViewById(R.id.ButtonViewCart);
		viewShoppingCart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent viewShoppingCartIntent = new Intent(getBaseContext(), ShoppingCartActivity.class);
				startActivity(viewShoppingCartIntent);
			}
		});

	}
}