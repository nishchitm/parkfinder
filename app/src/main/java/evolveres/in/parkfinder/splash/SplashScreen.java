package evolveres.in.parkfinder.splash;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import evolveres.in.parkfinder.R;
import evolveres.in.parkfinder.slider.WelcomeActivity;


public class SplashScreen extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    //for spinner
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        /*//strat loading spinner
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.show();*/

        //spinner on screen

        spinner = (ProgressBar)findViewById(R.id.progressbar1);
        spinner.setVisibility(View.VISIBLE);


        //if net connectivity not avilable
        if (!isNetworkAvailable()) {
            // do something
            Toast.makeText(getApplicationContext(),"Internet not avilable", Toast.LENGTH_LONG).show();
        }
        //to check if gps is on or not
        else if(!islocationAvailable()){
            Toast.makeText(getApplicationContext(),"Gps not available", Toast.LENGTH_LONG).show();
        }
        //to wait for 3 sec and then go to the login activity
        new Handler().postDelayed(new Runnable() {

        /*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, WelcomeActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
            }, SPLASH_TIME_OUT);




    }
    /*to check wheather internet is available or not this method only tells the availability of
    * ibternet and not the availability of any particular internet address*/
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private boolean islocationAvailable(){
        Context context = this;
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}
        return gps_enabled && network_enabled;
    }
}
