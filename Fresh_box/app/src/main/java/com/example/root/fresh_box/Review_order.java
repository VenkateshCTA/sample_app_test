package com.example.root.fresh_box;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by root on 12/10/16.
 */
public class Review_order extends AppCompatActivity
{
    TextView orname,pro_name,qty;
    String p1,p2,p3;
    SharedPrefHandler sharedPrefHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_order);

        sharedPrefHandler=new SharedPrefHandler(this);

        orname=(TextView)findViewById(R.id.org_name);
        pro_name=(TextView)findViewById(R.id.pr_name);
        qty=(TextView)findViewById(R.id.qyt);

        p1=sharedPrefHandler.getSharedPreferences("p2");
        p2=sharedPrefHandler.getSharedPreferences("product_name");
        p3=sharedPrefHandler.getSharedPreferences("nopice");


        orname.setText(p1);
        pro_name.setText(p2);
        qty.setText(p3);

    }
}
