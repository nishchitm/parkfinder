package evolveres.in.parkfinder.loginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import evolveres.in.parkfinder.Config;
import evolveres.in.parkfinder.MainMap;
import evolveres.in.parkfinder.R;
import evolveres.in.parkfinder.interfaces.setlogin;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String URL_set = Config.URL;
    private StringBuffer stringBuffer ;
    private Button login;
    private EditText eemail;
    private EditText epassword;
    private String email,password,fname,lname,result;
    public String uid;
    private SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login= (Button) findViewById(R.id.login);
        eemail=(EditText) findViewById(R.id.email);
        epassword=(EditText) findViewById(R.id.password);
        session = new SessionManagement(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = eemail.getText().toString();
                password = epassword.getText().toString();

                Intent intent = new Intent(LoginActivity.this,MainMap.class);
                startActivity(intent);
                finish();
                /*
                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(URL_set).build();
                setlogin api = adapter.create(setlogin.class);
                api.insertdata(
                        email,
                        password,
                        new Callback<Response>() {
                            @Override
                            public void success(Response response, Response response2) {
                                BufferedReader reader =null;
                                try
                                {
                                    reader=new BufferedReader(new InputStreamReader(response.getBody().in()));
                                    int value;
                                    stringBuffer = new StringBuffer();
                                    while((value=reader.read())!=-1)
                                    {
                                        stringBuffer.append((char)value);
                                    }
                                    reader.close();
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                                result = stringBuffer.toString();
                                try {
                                    JSONArray jsonArray =new JSONArray(result);
                                    JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                                    fname = (String) jsonObject.get("Fname");
                                    lname = (String) jsonObject.get("Lname");
                                    uid = (String) jsonObject.get("UID");

                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }


                                session.createLoginSession(fname,lname,uid);
                                Intent intent = new Intent(LoginActivity.this,MainMap.class);
                                startActivity(intent);
                                finish();

                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        }
                );
                */


            }
        });
    }


    public void register(View view)
    {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }



    public void invalid(){
        Toast.makeText(getApplicationContext(),"INVALID EMAIL AND PASSWORD",Toast.LENGTH_LONG).show();
    }



}
