package com.example.root.fresh_box;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

	Button plus,minus;

	Toolbar toolbar;
	int quantity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.productdetails);


		toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);


		List<Product> catalog = ShoppingCartHelper.getCatalog(getResources());

		int productIndex = getIntent().getExtras().getInt(ShoppingCartHelper.PRODUCT_INDEX);
		final Product selectedProduct = catalog.get(productIndex);

		// Set the proper image and text
		ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct);

		productImageView.setImageDrawable(selectedProduct.productImage);

		TextView productTitleTextView = (TextView) findViewById(R.id.TextViewProductTitle);

		productTitleTextView.setText(selectedProduct.title);

		TextView productDetailsTextView = (TextView) findViewById(R.id.TextViewProductDetails);

		productDetailsTextView.setText(selectedProduct.description);

		// Update the current quantity in the cart
		TextView textViewCurrentQuantity = (TextView) findViewById(R.id.textViewCurrentlyInCart);
		textViewCurrentQuantity.setText("Currently in Cart: " + ShoppingCartHelper.getProductQuantity(selectedProduct));

		// Save a reference to the quantity edit text
		final EditText editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);

		plus=(Button)findViewById(R.id.plus);
		minus=(Button)findViewById(R.id.minus);
		 plus.setOnClickListener(new OnClickListener() {
			 @Override
			 public void onClick(View v) {
				 quantity = Integer.parseInt(editTextQuantity.getText().toString());
				 quantity++;

			 }
		 });

		minus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				quantity = Integer.parseInt(editTextQuantity.getText().toString());
				quantity--;

			}
		});




		Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart);
		addToCartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Check to see that a valid quantity was entered
				 quantity = 0;
				try {
					quantity = Integer.parseInt(editTextQuantity.getText().toString());

					if (quantity < 0) {
						Toast.makeText(getBaseContext(),"Please enter a quantity of 0 or higher", Toast.LENGTH_SHORT).show();
						return;
					}

				} catch (Exception e) {
					Toast.makeText(getBaseContext(), "Please enter a numeric quantity",Toast.LENGTH_SHORT).show();

					return;
				}

				// If we make it here, a valid quantity was entered
				ShoppingCartHelper.setQuantity(selectedProduct, quantity);

				// Close the activity
				finish();
			}
		});

	}

}
