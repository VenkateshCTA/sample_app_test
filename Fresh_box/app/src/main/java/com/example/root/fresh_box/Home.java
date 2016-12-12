package com.example.root.fresh_box;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by inwhizz on 12/25/2015.
 */
public class Home extends AppCompatActivity {

    ImageView img_v1, img_v2, img_v3, img_v4;
    TextView txt;
    CardView cd_tap;
    String org_name;

    Button place_od;

    private static final String PREF_FIRSTLAUNCH_HELP = "helpDisplayed";
    private boolean helpDisplayed = false;
    DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    Fragment mFragment = null;
    FragmentManager mFragmentManager;
    ConnectionDetector connectionDetector;


    SharedPrefHandler sharedPrefHandler;




    RelativeLayout rl;
    Gallery gal;
    ImageSwitcher img;
    RelativeLayout.LayoutParams params;



    int imgs[] =
            {
                    R.drawable.veg,
                    R.drawable.frrr,
                    R.drawable.leafy,

            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout);
        place_od=(Button)findViewById(R.id.place_od);


        place_od.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                //Intent qq=new Intent(getApplication(),Review_order.class);
                //startActivity(qq);
            }
        });


        rl = (RelativeLayout) findViewById(R.id.rl);
        gal = (Gallery) findViewById(R.id.gallery1);

        img = new ImageSwitcher (Home.this);

        gal.setAdapter(new ImageAdapter(this));



        img.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));

        img.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

        params = new RelativeLayout.LayoutParams((int) DrawerLayout.LayoutParams.WRAP_CONTENT, (int) DrawerLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        img.setLayoutParams(params);

        rl.addView(img);

//        gal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//                // TODO Auto-generated method stub
//
//                img.setImageResource(imgs[arg2]);
//            }
//        });



        sharedPrefHandler=new SharedPrefHandler(this);
        org_name=sharedPrefHandler.getSharedPreferences("p2");

        Toast.makeText(Home.this, "selected Name"+org_name, Toast.LENGTH_SHORT).show();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
//
//
//
        connectionDetector=new ConnectionDetector(this);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                //Home






                //gfgc website
                if (menuItem.getItemId() == R.id.nav_item_profi) {

                     if (connectionDetector.isConnectingToInternet()) {


                    Intent i = new Intent(getApplicationContext(), Register.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    } else {
                    Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                    }


                }


                //kud website
                if (menuItem.getItemId() == R.id.nav_item_veg) {

                    // if (connectionDetector.isConnectingToInternet()) {

                    //  sharedPrefHandler.setSharedPreferences("key", "kud");
                    Intent i = new Intent(getApplicationContext(), CatalogActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    // } else {
                    //Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                    // }


                }


                //desclaimer
                if (menuItem.getItemId() == R.id.nav_item_frt) {

                    if (connectionDetector.isConnectingToInternet()) {
                    //sharedPrefHandler.setSharedPreferences("key", "des");
                    Intent i = new Intent(getApplicationContext(), frut_CatalogActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);

                     } else {

                     Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }

                //feedback
                if (menuItem.getItemId() == R.id.nav_item_levfy) {

                    if (connectionDetector.isConnectingToInternet())
                    {

                        Intent i = new Intent(getApplicationContext(), leafy_CatalogActivity.class);
                        startActivity(i);
                    } else {



                        Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }


                if (menuItem.getItemId() == R.id.nav_item_exost) {

                    if (connectionDetector.isConnectingToInternet())
                    {

                        Intent i = new Intent(getApplicationContext(), ex_CatalogActivity.class);
                        startActivity(i);
                    } else {



                        Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }


                if (menuItem.getItemId() == R.id.nav_item_order) {

                    if (connectionDetector.isConnectingToInternet())
                    {

                        Intent i = new Intent(getApplicationContext(), exostic_product_list.class);
                        startActivity(i);
                    } else {



                        Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }

                if (menuItem.getItemId() == R.id.nav_item_logt) {

                    if (connectionDetector.isConnectingToInternet())
                    {

                        //mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        //mFragment = new Feedback();
                        Intent i = new Intent(getApplicationContext(), Register.class);
                        startActivity(i);
                    } else {



                        Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }

                //logout
                if (menuItem.getItemId() == R.id.nav_item_call) {

                    AlertDialog diaBox = callOption();
                    diaBox.show();

                }

                if (mFragment != null) {

                    // FragmentManager mFragmentManager = getSupportFragmentManager();
                    mFragmentManager
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.containerView,
                                    mFragment).commit();

                }


                return false;
            }

        });

//        /**
//         * Setup Drawer Toggle of the Toolbar
//         */
//
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
//
//        connectionDetector=new ConnectionDetector(this);
//
//        txt = (TextView)findViewById(R.id.txt_msg);
        img_v1 = (ImageView)findViewById(R.id.im_subject);
        img_v2 = (ImageView)findViewById(R.id.im_quep);
        img_v3 = (ImageView)findViewById(R.id.im_noti);
        img_v4 = (ImageView)findViewById(R.id.im_howstudy);
//

        //subject list
        img_v1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent in = new Intent(getApplicationContext(),veg_boxx.class);
                startActivity(in);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });

        //solved question paper
        img_v2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


               // if (connectionDetector.isConnectingToInternet()) {
                   // sharedPrefHandler.setSharedPreferences("key","prev");
                    Intent in = new Intent(getApplicationContext(),frut_CatalogActivity.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);

              //  } else {
                   // Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                //}

            }
        });

        //Notification
        img_v3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent in = new Intent(getApplicationContext(),leafy_CatalogActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });

        //how to study
        img_v4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (connectionDetector.isConnectingToInternet()) {
                   // sharedPrefHandler.setSharedPreferences("key", "hts");
                    Intent in = new Intent(getApplicationContext(), ex_CatalogActivity.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);

                } else {
                    Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }






private AlertDialog callOption() {
    AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
            .setTitle("Confirm Call")
            .setIcon(R.drawable.phone)
            .setMessage("Sure you want to make a call to Mr.Sagar Dalabanjan To Enquir??")

            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:9035292096"));
                    startActivity(callIntent);

                }
            })
            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    dialog.dismiss();
                }
            })
            .create();
    return myQuittingDialogBox;

}

//
//
//
//    //show menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    //onclick menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent City_search in AndroidManifest.xml.
        // the nav drawer indicator touch event
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.menu_logout) {

            AlertDialog diaBox = AskOption();
            diaBox.show();
            return true;

        }*/

        if(id == R.id.menu_share){

           Intent aa=new Intent(getApplication(),Review_order.class);
            startActivity(aa);


        }
//
//        if(id == R.id.menu_help){
//
//           // showHelp();
//        }



//        if(id == R.id.menu_feed){
//
////            Intent i = new Intent(getApplicationContext(),Feedback.class);
////            startActivity(i);
//            //overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
//            overridePendingTransition(R.anim.left_in, R.anim.left_out);
//        }
//
//        if(id == R.id.change_cource){
//
//            //Intent i = new Intent(getApplicationContext(),choose_course.class);
//           // startActivity(i);
//            //overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
//            overridePendingTransition(R.anim.left_in, R.anim.left_out);
//        }


        return super.onOptionsItemSelected(item);
    }

    private AlertDialog AskOption() {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                .setTitle("Confirm Logout")
                .setIcon(R.drawable.ic_settings_power_black)
                .setMessage("Are you sure to logout?")

                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                       // sharedPrefHandler = new SharedPrefHandler(getApplicationContext());
                       // sharedPrefHandler.setSharedPreferences("login", "false");
                       // Intent inwhizz_gcm = new Intent(getApplication(), login.class);
                        finish();
                       // startActivity(inwhizz_gcm);
                        overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }

//    private AlertDialog askOption() {
//        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
//                .setTitle("What's new")
//                .setIcon(R.drawable.appicon)
//                .setView(R.layout.whats_new)
//
//                .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        dialog.dismiss();
//                    }
//                })
//                .create();
//        return myQuittingDialogBox;
//
//    }





    //back pressed
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        int count = getSupportFragmentManager().getBackStackEntryCount();

       //check drawer is opened or closed
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            finish();
            System.exit(0);
        }
    }





    public class ImageAdapter extends BaseAdapter {
        private Context ctx;

        public ImageAdapter(Context c) {
            ctx = c;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imgs.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            ImageView iView = new ImageView(ctx);
            iView.setImageResource(imgs[position]);
            iView.setScaleType(ImageView.ScaleType.FIT_XY);
            iView.setLayoutParams(new Gallery.LayoutParams(200, 150));

            return iView;
        }




    }
}
