package com.example.root.fresh_box;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ex_ProductDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ex_productdetails);

		List<ex_Product> catalog = ex_ShoppingCartHelper.getCatalog(getResources());

		int productIndex = getIntent().getExtras().getInt(ex_ShoppingCartHelper.PRODUCT_INDEX);
		final ex_Product selectedProduct = catalog.get(productIndex);

		// Set the proper image and text
		ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct);
		productImageView.setImageDrawable(selectedProduct.productImage);
		TextView productTitleTextView = (TextView) findViewById(R.id.TextViewProductTitle);
		productTitleTextView.setText(selectedProduct.title);
		TextView productDetailsTextView = (TextView) findViewById(R.id.TextViewProductDetails);
		productDetailsTextView.setText(selectedProduct.description);

		// Update the current quantity in the cart
		TextView textViewCurrentQuantity = (TextView) findViewById(R.id.textViewCurrentlyInCart);
		textViewCurrentQuantity.setText("Currently in Cart: " + ex_ShoppingCartHelper.getProductQuantity(selectedProduct));

		// Save a reference to the quantity edit text
		final EditText editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);

		Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart);
		addToCartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Check to see that a valid quantity was entered
				int quantity = 0;
				try {
					quantity = Integer.parseInt(editTextQuantity.getText()
							.toString());

					if (quantity < 0) {
						Toast.makeText(getBaseContext(),"Please enter a quantity of 0 or higher",Toast.LENGTH_SHORT).show();
						return;
					}

				} catch (Exception e) {
					Toast.makeText(getBaseContext(),"Please enter a numeric quantity",Toast.LENGTH_SHORT).show();

					return;
				}

				// If we make it here, a valid quantity was entered
				ex_ShoppingCartHelper.setQuantity(selectedProduct, quantity);

				// Close the activity
				finish();
			}
		});

	}

}
