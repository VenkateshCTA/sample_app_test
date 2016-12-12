package com.example.root.fresh_box;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

/**
 * Created by root on 12/9/16.
 */
public class veg_product_list extends AppCompatActivity
{
    android.support.v7.widget.Toolbar toolbar;
    ListView veg;
    String sub_val;
    SharedPrefHandler sharedPrefHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veg_product_list);

        sharedPrefHandler=new SharedPrefHandler(this);
        veg=(ListView)findViewById(R.id.lv);

         toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        veg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub


                sub_val = veg.getItemAtPosition(arg2).toString();
                sharedPrefHandler.setSharedPreferences("veg_p1",sub_val);
                Intent in = new Intent(getApplication(), Veg_product.class);
                startActivity(in);


            }
        });
    }
}
