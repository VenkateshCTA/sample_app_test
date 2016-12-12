package com.example.root.fresh_box;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 12/9/16.
 */
public class Veg_product extends AppCompatActivity
{
    public   ArrayList<String> mylist = new ArrayList<String>();
    android.support.v7.widget.Toolbar toolbar;
    TextView veg_p1_name,veg_p1_subname,Veg_p1_price,Veg_p1_qty_text;
    EditText p1_pice;
    Button plus,minus;
    String result;

     Button cart,finall;
int number=1;
    SharedPrefHandler sharedPrefHandler;
    String op_pce;
    String product_name,qty_string;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veg_products);
        progressDialog=new ProgressDialog(this);

        sharedPrefHandler=new SharedPrefHandler(this);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        veg_p1_name=(TextView)findViewById(R.id.veg_p1);
        veg_p1_subname=(TextView)findViewById(R.id.veg_p1_sub1);
        Veg_p1_price=(TextView)findViewById(R.id.veg_p1_price);

        Veg_p1_qty_text=(TextView)findViewById(R.id.veg_qty);


        p1_pice=(EditText)findViewById(R.id.veg_p1_pice);

        plus=(Button)findViewById(R.id.veg_p1_plus);
        minus=(Button)findViewById(R.id.veg_p1_minu);


        cart=(Button)findViewById(R.id.veg_place_cart);


        String lv_p1=sharedPrefHandler.getSharedPreferences("veg_key");
        veg_p1_name.setText(lv_p1);

        cart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                product_name=veg_p1_name.getText().toString();
                op_pce=p1_pice.getText().toString();
                qty_string=Veg_p1_qty_text.getText().toString();

                mylist.add(product_name); //this adds an element to the list.
                mylist.add(op_pce);
                mylist.add(qty_string);

                Toast.makeText(Veg_product.this, "Product Added In Cart List"+mylist, Toast.LENGTH_SHORT).show();
                Log.d("cart",""+mylist);

                //sharedPrefHandler.setSharedPreferences("mycart",mylist);

                Intent qq=new Intent(getApplication(),Home.class);
                startActivity(qq);

            }
        });





        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String op_plus=p1_pice.getText().toString();

//                    int op_pice = Integer.parseInt(op_plus);
//                    op_pice++;
//                    op_pce = String.valueOf(op_pice);
                    p1_pice.setText(""+number++);
                }

        });



        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String op_min=p1_pice.getText().toString();
//                int op_minm=Integer.parseInt(op_min);
//                op_minm--;
//                op_pce=String.valueOf(op_minm);
                p1_pice.setText(""+number--);
            }
        });


        new MyAsync().execute();
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build());



    }


    private class MyAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            progressDialog.setMessage("Loading Selected Product Details...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    MyAsync.this.cancel(true);
                }
            });

        }


        public void postData() {

            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://suchithmahadi.com/freshboxx/veg_details.php");

            try {


                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                //nameValuePairs.add(new BasicNameValuePair("nw_pass", nwpass));
               // nameValuePairs.add(new BasicNameValuePair("f1", cat));

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                Log.d("nameValuePairs", "" + nameValuePairs);
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();


                // If the response does not enclose an entity, there is no need
                if (entity != null) {
                    InputStream instream = entity.getContent();

                    String result;
                    result = convertStreamToString(instream);
                    //Log.d("aaa","" + result);


                    JSONArray arr = new JSONArray(result);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonObject = arr.getJSONObject(i);
                        String price = jsonObject.getString("price");
                        String sub_name = jsonObject.getString("desc");
                        String qty_text = jsonObject.getString("qty_text");



                        Log.d("select",""+price+sub_name);

                      setdata(sub_name,price,qty_text);


                    }


                }


            } catch (Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Something went wrong.Please try again.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

        public void setdata(final String sub_name, final String price,final String qty_text) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //stuff that updates ui
                    veg_p1_subname.setText(sub_name);
                    Veg_p1_price.setText(price);
                    Veg_p1_qty_text.setText(qty_text);

                    //tsem.setText(sem);




                }
            });
        }

        @Override
        protected String doInBackground(String... params) {
            postData();
            //start loading proggress dialog
            //pd= ProgressDialog.show(Chg_Password.this, "Loading...","");
            progressDialog.dismiss();

            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //dissmiss
            //pd.dismiss();

            //list_subject.setVisibility(View.GONE);
            //list_subject.setVisibility(View.VISIBLE);
        }

        private String convertStreamToString(InputStream is) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (Exception ex) {
                Toast.makeText(getApplication(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            result = sb.toString().trim();


            result = result.substring(1, result.length() - 1);

            if (!result.trim().equals("Error")) {
                String[] r = result.split("-");


            } else
                Toast.makeText(getApplication(), "Internal Marks Information Not Found", Toast.LENGTH_LONG).show();
            return sb.toString();

        }

        private void showToast(final String res) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //stuff that updates ui
                    if (res.equals("1")) {
                        Toast.makeText(getApplicationContext(), " Successfull", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), " Failed ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }






    }
