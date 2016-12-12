package com.example.root.fresh_box;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText mno,pass;
    Button login;
    TextView create_new,forgot;
ConnectionDetector connectionDetector;

    String p1,p2;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionDetector=new ConnectionDetector(this);


        mno=(EditText)findViewById(R.id.log_mno);
        pass=(EditText)findViewById(R.id.log_pass);
        login=(Button)findViewById(R.id.log_btn_log);
        create_new=(TextView)findViewById(R.id.tv_log_new);
        forgot=(TextView)findViewById(R.id.tv_log_forg);





        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (connectionDetector.isConnectingToInternet())
                {
                    Intent zz=new Intent(getApplication(),Register.class);
                    startActivity(zz);

                }else {
                    Toast.makeText(MainActivity.this, "Check Your Internet Connect", Toast.LENGTH_SHORT).show();
                }
            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (connectionDetector.isConnectingToInternet())
                {
                    Intent zz=new Intent(getApplication(),Register.class);
                    startActivity(zz);

                }else {
                    Toast.makeText(MainActivity.this, "Check Your Internet Connect", Toast.LENGTH_SHORT).show();
                }
            }
        });



            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                        p1=mno.getText().toString();
                        p2=pass.getText().toString();
                        new Mytask().execute();




                }
            });
    }


    private class Mytask extends AsyncTask<String, Void, String>


    {


        private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        protected void onPreExecute() {
            progressDialog.setMessage("Loading Please a Wait...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    Mytask.this.cancel(true);
                }
            });

        }
        public void postData(String p1,String p2){
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://suchithmahadi.com/college_project/user_login.php");

            try {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("f1", p1));
                nameValuePairs.add(new BasicNameValuePair("f2", p2));

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
                        Intent nex=new Intent(getApplication(),Home.class);
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
            postData(p1,p2);
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
