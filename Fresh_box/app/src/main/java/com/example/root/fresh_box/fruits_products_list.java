package com.example.root.fresh_box;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by root on 12/10/16.
 */
public class fruits_products_list extends AppCompatActivity
{
    android.support.v7.widget.Toolbar toolbar;
    ListView lv_frt;
    String sub_frt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruits_products_list);


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

       // getActionBar().setDisplayHomeAsUpEnabled(true);


        lv_frt=(ListView)findViewById(R.id.lv_fr);

        lv_frt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub


                sub_frt= lv_frt.getItemAtPosition(arg2).toString();
                //Toast.makeText(getApplication(), lv.getItemAtPosition(arg2).toString(), Toast.LENGTH_LONG).show();
                Intent in = new Intent(getApplication(), Veg_product.class);
                //in.putExtra("sub", sub_val);

               // Log.d("selected_sub", sub_val);
                startActivity(in);


            }
        });
    }
}
