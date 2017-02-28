package evolveres.in.parkfinder.loginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import evolveres.in.parkfinder.Config;
import evolveres.in.parkfinder.R;
import evolveres.in.parkfinder.interfaces.set_details;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends AppCompatActivity {

    private static final String URL_set = Config.URL;
    private EditText efname,elname,eemail,epassword,ecpassword,esecurityquestion,eanswer,ephonenumber;
    private String fname,lname,email,password,cpassword,securityquestion,answer,phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        efname =(EditText) findViewById(R.id.fname);
        elname =(EditText) findViewById(R.id.lname);
        eemail =(EditText) findViewById(R.id.email);
        epassword =(EditText) findViewById(R.id.password);
        ecpassword = (EditText) findViewById(R.id.cpassword);
        esecurityquestion =(EditText) findViewById(R.id.securityquestion);
        eanswer =(EditText) findViewById(R.id.answer);
        ephonenumber =(EditText) findViewById(R.id.phonenumber);

    }
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


    public void register(View view)
    {
        fname = efname.getText().toString();
        lname = elname.getText().toString();
        email = eemail.getText().toString();
        password = epassword.getText().toString();
        cpassword = ecpassword.getText().toString();
        securityquestion = esecurityquestion.getText().toString();
        answer = eanswer.getText().toString();
        phonenumber = ephonenumber.getText().toString();

        if(isValidEmailAddress(email) && fname != null && !fname.isEmpty()
                && lname != null && !lname.isEmpty()
                && email != null && !email.isEmpty()
                && password != null && !password.isEmpty()
                && cpassword!=null && cpassword.equals(password)
                && securityquestion != null && !securityquestion.isEmpty()
                && answer != null && !answer.isEmpty()
                && phonenumber != null && !phonenumber.isEmpty() && phonenumber.length()==10) {

            RestAdapter adapter =new RestAdapter.Builder()
                    .setEndpoint(URL_set)
                    .build();

            set_details api =adapter.create(set_details.class);

            api.insertdata(
                    fname,
                    lname,
                    email,
                    password,
                    securityquestion,
                    answer,
                    phonenumber,
                    new Callback<Response>() {
                        @Override
                        public void success(Response response, Response response2) {
                            BufferedReader reader=null;
                            String output ="";
                            try {
                                reader=new BufferedReader(new InputStreamReader(response.getBody().in()));
                                output=reader.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(RegisterActivity.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }
        else if (!isValidEmailAddress(email))
        {
            Toast.makeText(getApplicationContext(),"invalid email",Toast.LENGTH_SHORT).show();
        }
        else if((phonenumber.length())!=10)
        {
            Toast.makeText(getApplicationContext(),"invalid phone number",Toast.LENGTH_SHORT).show();
        }
        else if(!cpassword.equals(password)){
            Toast.makeText(getApplicationContext(),"Passwords don't match !",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Fill All Fields",Toast.LENGTH_SHORT).show();
        }

    }



}

