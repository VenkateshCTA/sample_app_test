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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 12/12/16.
 */
public class veg_boxx extends AppCompatActivity {

    String myJSON;

    android.support.v7.widget.Toolbar toolbar;
    ArrayList<HashMap<String, String>> personList;
    ArrayList<String> lst_sub = new ArrayList<String>();
    ListView list_subject;
    String result;
    public static String branch, sub_val;
    ArrayAdapter<String> adp_sub;
    ConnectionDetector connectionDetector;
    public ProgressDialog progressDialog;
    ListView list;

    String cat="Vegetable";

    SharedPrefHandler sharedPrefHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veg_box);

        sharedPrefHandler=new SharedPrefHandler(this);


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressDialog=new ProgressDialog(this);
        new MyAsyncia().execute();
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build());


        adp_sub = new ArrayAdapter<String>(veg_boxx.this, android.R.layout.simple_list_item_1, lst_sub);
        list_subject = (ListView) findViewById(R.id.listView);
       list_subject.setAdapter(adp_sub);

        //ListAdapter adapter = new SimpleAdapter(veg_boxx.this, lst_sub, R.layout.veg_box_list_item,new String[]{"p_name","price","desc"}, new int[]{R.id.id, R.id.name, R.id.address});

        //list_subject.setAdapter(adapter);

        list_subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub


               String sub_leafy= list_subject.getItemAtPosition(arg2).toString();

                sharedPrefHandler.setSharedPreferences("veg_key",sub_leafy);
               // Toast.makeText(getApplication(), lv.getItemAtPosition(arg2).toString(), Toast.LENGTH_LONG).show();
                Intent in = new Intent(getApplication(), Veg_product.class);

                startActivity(in);


            }
        });





    }


//    protected void showList(){
//        try {
//            JSONObject jsonObj = new JSONObject(myJSON);
//            peoples = jsonObj.getJSONArray(TAG_RESULTS);
//
//            for(int i=0;i<peoples.length();i++){
//                JSONObject c = peoples.getJSONObject(i);
//                String id = c.getString(TAG_ID);
//                String name = c.getString(TAG_NAME);
//                String address = c.getString(TAG_ADD);
//
//                HashMap<String,String> persons = new HashMap<String,String>();
//
//                persons.put(TAG_ID,id);
//                persons.put(TAG_NAME,name);
//                persons.put(TAG_ADD,address);
//
//                personList.add(persons);
//            }
//
//            ListAdapter adapter = new SimpleAdapter(veg_boxx.this, personList, R.layout.veg_box_list_item,new String[]{TAG_ID,TAG_NAME,TAG_ADD}, new int[]{R.id.id, R.id.name, R.id.address});
//
//            list.setAdapter(adapter);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

    private class MyAsyncia extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            progressDialog.setMessage("Loading All Vegetables Products..");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    MyAsyncia.this.cancel(true);
                }
            });

        }


        public void postData(String cat) {

            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://suchithmahadi.com/freshboxx/veg_list.php");

            try {


                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                //nameValuePairs.add(new BasicNameValuePair("nw_pass", nwpass));
                nameValuePairs.add(new BasicNameValuePair("f1", cat));

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
                        String pname = jsonObject.getString("p_name");

                       // String sub1 = jsonObject.getString("p_name");

                        HashMap<String,String> persons = new HashMap<String,String>();

                        persons.put("p_name",pname);

                       // persons.put("",address);

                       // lst_sub.add(sub1);
                        lst_sub.add(pname);


                        Log.d("list_sub", "" + lst_sub);
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

        @Override
        protected String doInBackground(String... params) {
            postData(cat);
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
            adp_sub.notifyDataSetChanged();
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
