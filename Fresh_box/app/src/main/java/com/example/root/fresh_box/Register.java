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
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by root on 12/8/16.
 */
public class Register extends AppCompatActivity
{
    EditText name,orgas,address,mno,pass,con_pass;
    String p1,p2,p3,p4,p5,p6;
    Button register;
    ConnectionDetector connectionDetector;
    SharedPrefHandler sharedPrefHandler;
    String result;

    String status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        connectionDetector = new ConnectionDetector(this);
        sharedPrefHandler=new SharedPrefHandler(this);

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build());

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("Customer Register");

        name=(EditText)findViewById(R.id.rfr_full);
        orgas=(EditText)findViewById(R.id.rfr_org);
        address=(EditText)findViewById(R.id.rfr_addr);
        mno=(EditText)findViewById(R.id.rfr_mno);
        pass=(EditText)findViewById(R.id.rfr_pass1);
        con_pass=(EditText)findViewById(R.id.rfr_pass2);

        register=(Button)findViewById(R.id.rfrbtn_reg);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p1 = name.getText().toString();
                p2 = orgas.getText().toString();
                p3 = address.getText().toString();
                p4 = mno.getText().toString();
                p5 = pass.getText().toString();
                p6 = con_pass.getText().toString();


               sharedPrefHandler.setSharedPreferences("p2", p2);


                new MyAsyncTask().execute();


            }
        });
    }






    private class MyAsyncTask extends AsyncTask<String, Void, String>


    {


        private ProgressDialog progressDialog = new ProgressDialog(Register.this);

        protected void onPreExecute() {
            progressDialog.setMessage("Loading Please a Wait...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    MyAsyncTask.this.cancel(true);
                }
            });

        }
        public void postData(String p1,String p2,String p3,String p4,String p5,String p6){
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://suchithmahadi.com/college_project/user_signup.php");

            try {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("f1", p1));
                nameValuePairs.add(new BasicNameValuePair("f2", p2));
                nameValuePairs.add(new BasicNameValuePair("f3", p3));
                nameValuePairs.add(new BasicNameValuePair("f4", p4));
                nameValuePairs.add(new BasicNameValuePair("f5", p5));
                nameValuePairs.add(new BasicNameValuePair("f6", p6));

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                Log.d("nameValuePairs",""+nameValuePairs);
                HttpResponse response = httpclient.execute(httppost);

                HttpEntity entity = response.getEntity();


                // If the response does not enclose an entity, there is no need
                if (entity != null) {
                    InputStream instream = entity.getContent();

                    String result;
                    result = convertStreamToString(instream);
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.getString("success");
                    Log.d("status", "" + status);

                    if (status.equals("1"))
                    {

                        Log.d("Insertion", "Successful!");
                        Intent nex=new Intent(getApplication(),MainActivity.class);
                        startActivity(nex);
                        showToast(status);
                    }
                    else
                    {

                        Log.d("Insertion","Failed");
                    }

                }


            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground (String...params)
        {
            postData(p1,p2,p3,p4,p5,p6);
            return null;
        }
        private  String convertStreamToString(InputStream is)
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    is.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
    }
    private void showToast(final String res)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                //stuff that updates ui
                if (res.equals("1"))
                {
                    Toast.makeText(getApplicationContext(), "  Register Successful ", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Register Failed ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}


