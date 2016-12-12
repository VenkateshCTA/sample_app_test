package com.example.root.fresh_box;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by root on 12/8/16.
 */
public class Flash_activity extends AppCompatActivity
{
    SharedPrefHandler sharedPrefHandler;
    private Context ctx;
    private ConnectionDetector connectionDetector;
    TextView txt_dev,txt_inw,txt_col,txt_kncol,txt_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.flash_page);

        sharedPrefHandler = new SharedPrefHandler(this);
        connectionDetector = new ConnectionDetector(this);
        ctx = Flash_activity.this;


        if (!connectionDetector.isConnectingToInternet()) {

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            // Setting Dialog Title
            alertDialog.setTitle("No Internet connection");

            // Setting Dialog Message
            alertDialog.setMessage("Please connect to Internet.");


            // Setting alert dialog icon
            //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

            // Setting OK Button

            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            // Showing Alert Message
            alertDialog.show();
        } else {

            Thread background = new Thread() {
                public void run() {

                    try {
                        // Thread will sleep for 5 seconds
                        sleep(2000);

                        sharedPrefHandler = new SharedPrefHandler(getApplicationContext());

                        //calling introduction slides
                        if (sharedPrefHandler.getSharedPreferences("login").equals("NF")) {
                            sharedPrefHandler.setSharedPreferences("login", "false");
                            Intent inwhizz_gcm = new Intent(Flash_activity.this, MainActivity.class);
                            startActivity(inwhizz_gcm);
                        }
                        /*//once introduction slides finished
                        else if (sharedPrefHandler.getSharedPreferences("login").equals("false")) {
                            Intent inwhizz_gcm = new Intent(flashactivity.this, branch_select.class);
                            startActivity(inwhizz_gcm);

//                        }*/
//                    else if (sharedPrefHandler.getSharedPreferences("login").equals("false")) {
//                        Intent inwhizz_gcm = new Intent(Flash_activity.this,choose_course_main.class);
//                        startActivity(inwhizz_gcm);


//                      //  on successfull login
//                        else if (sharedPrefHandler.getSharedPreferences("login").equals("false")) {
//
//                            Intent i = new Intent(getBaseContext(), Home.class);
//                            startActivity(i);
//                        }


                        else if (sharedPrefHandler.getSharedPreferences("login").equals("false")) {

                            Intent i = new Intent(getBaseContext(), Home.class);
                            startActivity(i);

                        }

                        finish();

                    } catch (Exception e) {

                    }

                }
            };
            background.start();
        }
    }

    public void setLocale(String lang) {

        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Locale.setDefault(myLocale);

    }
}