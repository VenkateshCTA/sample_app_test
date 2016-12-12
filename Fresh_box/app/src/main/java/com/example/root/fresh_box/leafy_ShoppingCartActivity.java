package com.example.root.fresh_box;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class leafy_ShoppingCartActivity extends Activity {
	
	private List<leafy_Product> mCartList;
	private leafy_ProductAdapter mProductAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leafy_shoppingcart);
		
		
		mCartList = leafy_ShoppingCartHelper.getCartList();
		
		// Make sure to clear the selections
		for(int i=0; i<mCartList.size(); i++) {
			mCartList.get(i).selected = false;
		}
		
		// Create the list
		final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
		mProductAdapter = new leafy_ProductAdapter(mCartList, getLayoutInflater(), true);
		listViewCatalog.setAdapter(mProductAdapter);
		
		listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent productDetailsIntent = new Intent(getBaseContext(),leafy_ProductDetailsActivity.class);
				productDetailsIntent.putExtra(leafy_ShoppingCartHelper.PRODUCT_INDEX, position);
				startActivity(productDetailsIntent);
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		// Refresh the data
		if(mProductAdapter != null) {
			mProductAdapter.notifyDataSetChanged();
		}
	}

}
