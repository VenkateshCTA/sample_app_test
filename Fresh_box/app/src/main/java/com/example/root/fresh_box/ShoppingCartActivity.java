package com.example.root.fresh_box;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
	
	private List<Product> mCartList;
	private ProductAdapter mProductAdapter;
	Button veg_but_order;
	TextView org_name;
	String org_name1;
	SharedPrefHandler sharedPrefHandler;


	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shoppingcart);


		toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		sharedPrefHandler=new SharedPrefHandler(this);

		org_name=(TextView)findViewById(R.id.org_TextView01);
		veg_but_order=(Button)findViewById(R.id.veg_But_order);

		org_name1=sharedPrefHandler.getSharedPreferences("p2");
		org_name.setText(org_name1);




		mCartList = ShoppingCartHelper.getCartList();
		
		// Make sure to clear the selections
		for(int i=0; i<mCartList.size(); i++) {
			mCartList.get(i).selected = false;
		}
		
		// Create the list
		final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);

		mProductAdapter = new ProductAdapter(mCartList, getLayoutInflater(), true);
		listViewCatalog.setAdapter(mProductAdapter);


		
		listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent productDetailsIntent = new Intent(getBaseContext(),ProductDetailsActivity.class);
				productDetailsIntent.putExtra(ShoppingCartHelper.PRODUCT_INDEX, position);
				startActivity(productDetailsIntent);
			}
		});





		veg_but_order.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Toast.makeText(ShoppingCartActivity.this, "Ordered_List"+mCartList+org_name1, Toast.LENGTH_SHORT).show();
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
