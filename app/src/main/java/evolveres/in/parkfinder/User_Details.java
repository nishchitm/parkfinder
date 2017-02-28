package evolveres.in.parkfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;

import evolveres.in.parkfinder.loginandregister.SessionManagement;

public class User_Details extends AppCompatActivity {
    private TextView use_fname;
    private TextView use_lanme;
    private TextView use_UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__details);
        SessionManagement sessionManagement =new SessionManagement(getApplicationContext());
        HashMap<String,String> user=sessionManagement.getUserDetails();

        use_fname=(TextView)findViewById(R.id.use_fname);
        use_lanme=(TextView)findViewById(R.id.use_lname);
        use_UID=(TextView)findViewById(R.id.use_uid);

        use_fname.setText(user.get(SessionManagement.KEYFNAME));
        use_lanme.setText(user.get(SessionManagement.KEYLNAME));
        use_UID.setText(user.get(SessionManagement.KEYUID));

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainMap.class));
        super.onBackPressed();

    }
}
