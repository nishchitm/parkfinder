package evolveres.in.parkfinder.user_vendor;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import evolveres.in.parkfinder.Config;
import evolveres.in.parkfinder.MainMap;
import evolveres.in.parkfinder.R;
import evolveres.in.parkfinder.interfaces.user_set_API;
import evolveres.in.parkfinder.loginandregister.LoginActivity;
import evolveres.in.parkfinder.loginandregister.SessionManagement;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nishc on 27-06-2016.
 */
public class vendor_activity extends AppCompatActivity {

    ImageButton btn_to_map;

    TextView setlat;
    TextView setlon;

    EditText set_address;
    EditText set_price;
    EditText set_description;
    EditText set_length;
    EditText set_breadth;
    EditText set_height;
    EditText set_space;

    String user_address, user_description;
    String user_uid;
    Integer user_price, user_length, user_breadth, user_height,user_space;
    Double user_lat;
    Double user_long;

    String longitude;
    String latitude;


    public static final String URL_set = Config.URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        btn_to_map = (ImageButton) findViewById(R.id.setLatLang);
        setlat = (TextView) findViewById(R.id.set_lat);
        setlon = (TextView) findViewById(R.id.set_lon);
        set_address = (EditText) findViewById(R.id.addressText);
        set_price = (EditText) findViewById(R.id.priceText);
        set_description = (EditText) findViewById(R.id.descriptionText);
        set_length = (EditText) findViewById(R.id.lengthText);
        set_breadth = (EditText) findViewById(R.id.breadthText);
        set_height = (EditText) findViewById(R.id.heightText);
        set_space = (EditText) findViewById(R.id.parkingplaceText);

        btn_to_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), vendor_map.class);
                startActivityForResult(i, 2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            latitude = data.getStringExtra("Latiude is:");
            setlat.setText(latitude);
            longitude = data.getStringExtra("Longitude is:");
            setlon.setText(longitude);
        }

    }

    public void lets_save_it(View v) {

        user_address = set_address.getText().toString();
        user_description = set_description.getText().toString();

        try {
            user_length = Integer.parseInt(set_length.getText().toString());
            user_breadth = Integer.parseInt(set_breadth.getText().toString());
            user_height = Integer.parseInt(set_height.getText().toString());
            user_space = Integer.parseInt(set_space.getText().toString());
            user_price = Integer.parseInt(set_price.getText().toString());
            user_lat = Double.parseDouble(latitude.toString());
            user_long = Double.parseDouble(longitude.toString());

        } catch (NumberFormatException e) {
            Log.d("Exception", "");
        }
        if(user_address!=null && !user_address.isEmpty()
                && user_description!=null && !user_description.isEmpty()
                && user_lat!=null && !user_lat.toString().isEmpty()
                && user_long!=null && !user_long.toString().isEmpty()
                && user_price!=null && !user_price.toString().isEmpty()
                && user_space!=null && !user_price.toString().isEmpty()){

            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(URL_set) //Setting the Root URL
                    .build();

            user_set_API api = adapter.create(user_set_API.class);

            SessionManagement sessionManagement=new SessionManagement(getApplicationContext());
            HashMap<String,String> user=sessionManagement.getUserDetails();
            user_uid=user.get(SessionManagement.KEYUID);



            //Defining the method insertuser of our interface
            api.insertData(
                    user_uid,
                    user_address,
                    user_price,
                    user_description,
                    user_lat,
                    user_long,
                    user_length,
                    user_breadth,
                    user_height,
                    user_space,

                    new Callback<Response>() {
                        @Override
                        public void success(Response result, Response responce) {
                            BufferedReader reader = null;

                            String output = "";

                            try {
                                reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                                //Reading the output in the string
                                output = reader.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(vendor_activity.this, output, Toast.LENGTH_SHORT).show();
                            Log.i("onething:",output);
                            startActivity(new Intent(vendor_activity.this, MainMap.class));
                            finish();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            //If any error occured displaying the error as toast
                            Toast.makeText(vendor_activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }else{
            Toast.makeText(vendor_activity.this,"Please complete all the field !",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(vendor_activity.this,MainMap.class));
        finish();
    }
}
