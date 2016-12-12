package com.example.root.fresh_box;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by root on 12/10/16.
 */
public class levfy_product_list extends AppCompatActivity
{
    android.support.v7.widget.Toolbar toolbar;
    ListView lv_leafy;
    String sub_leafy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levfy_products_list);

        //getActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lv_leafy=(ListView)findViewById(R.id.lv_leafy);

        lv_leafy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub


                sub_leafy= lv_leafy.getItemAtPosition(arg2).toString();
                //Toast.makeText(getApplication(), lv.getItemAtPosition(arg2).toString(), Toast.LENGTH_LONG).show();
                Intent in = new Intent(getApplication(), Veg_product.class);
                //in.putExtra("sub", sub_val);

                // Log.d("selected_sub", sub_val);
                startActivity(in);


            }
        });
    }
}
