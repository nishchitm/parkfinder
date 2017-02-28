package evolveres.in.parkfinder.user_client;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import evolveres.in.parkfinder.MainMap;
import evolveres.in.parkfinder.R;
import evolveres.in.parkfinder.Config;
import evolveres.in.parkfinder.interfaces.user_get2_API;
import evolveres.in.parkfinder.interfaces.user_get_API;
import evolveres.in.parkfinder.loginandregister.SessionManagement;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nishc on 27-06-2016.
 */
public class client_activity extends AppCompatActivity {

    private String URL_get = Config.URL;
    private String UID;
    private double Lat;
    private double Lon;
    private TextView view_address;
    private TextView view_price;
    private TextView view_description;
    private TextView view_space;
    private TextView view_length;
    private TextView view_breadth;
    private TextView view_height;
    private TextView view_lat;
    private TextView view_lon;
    private StringBuffer stringBuffer;
    private String result;

    private ImageButton btn_book_it;
    private ImageButton btn_book_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);


        view_address = (TextView) findViewById(R.id.addressView);
        view_price = (TextView) findViewById(R.id.priceView);
        view_description = (TextView) findViewById(R.id.descriptionView);
        view_space = (TextView) findViewById(R.id.spaceView);
        view_length = (TextView) findViewById(R.id.lengthView);
        view_breadth = (TextView) findViewById(R.id.breadthView);
        view_height = (TextView) findViewById(R.id.heightView);
        view_lat = (TextView) findViewById(R.id.set_lat_view);
        view_lon = (TextView) findViewById(R.id.set_lon_view);

        btn_book_it = (ImageButton) findViewById(R.id.book);
        btn_book_cancel = (ImageButton) findViewById(R.id.cancel);



        btn_book_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(client_activity.this, "Cancelled", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(client_activity.this,client_map.class));
                clear_data();
                finish();
            }
        });

        btn_book_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(client_activity.this)
                        .setMessage("Please contact the owner from his description.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });


        Intent inte = getIntent();
        Lat=inte.getExtras().getDouble("lat");
        Lon=inte.getExtras().getDouble("lon");

        get_data_array();
    }


    public void get_data_array(){



        RestAdapter adapter=new RestAdapter.Builder().setEndpoint(URL_get).build();
        user_get2_API api=adapter.create(user_get2_API.class);
        api.getdata(
                Lat,
                Lon,
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {

                        BufferedReader reader=null;

                        try {
                            reader=new BufferedReader(new InputStreamReader(response.getBody().in()));

                            int value;
                            stringBuffer=new StringBuffer();
                            while ((value=reader.read())!= -1){
                                stringBuffer.append((char)value);
                            }
                            reader.close();



                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        result=stringBuffer.toString();
                        try {
                            JSONArray jsonArray=new JSONArray(result);

                            JSONObject jsonObject=(JSONObject) jsonArray.get(0);
                            Log.i("Address",(String)jsonObject.get("Address"));
                            view_address.setText(jsonObject.getString("Address"));
                            view_description.setText(jsonObject.getString("Description"));
                            view_breadth.setText(jsonObject.getString("Breadth"));
                            view_height.setText(jsonObject.getString("Height"));
                            view_length.setText(jsonObject.getString("Length"));
                            view_lat.setText(jsonObject.getString("Lat"));
                            view_lon.setText(jsonObject.getString("Lon"));
                            view_price.setText(jsonObject.getString("Price"));
                            view_space.setText(jsonObject.getString("NoOfParkingPlaces"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Log.d("fail",UID);
                    }
                }
        );


    }

    public void clear_data(){

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(client_activity.this, client_map.class));
        finish();

    }
}