package evolveres.in.parkfinder.loginandregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

import evolveres.in.parkfinder.MainMap;

public class SessionManagement {

    private SharedPreferences pref;
    private Editor editor;
    private Context _context;
    private int PRIVATEMODE =0;
    private static final String PREFNAME= "evolveres.in.parkfinder.loginandregister";
    private static final String ISLOGIN ="IsLoggedIn";
    public static final String KEYFNAME = "fname";
    public static final String KEYLNAME = "lname";
    public static final String KEYUID = "uid";

    public SessionManagement(Context context)
    {
        this._context= context;
        pref = _context.getSharedPreferences(PREFNAME,PRIVATEMODE);
        editor = pref.edit();
    }

    public void createLoginSession(String fname, String lname, String uid)
    {
        editor.putBoolean(ISLOGIN,true);
        editor.putString(KEYFNAME, fname);
        editor.putString(KEYLNAME,lname);
        editor.putString(KEYUID, uid);
        editor.commit();
    }

    public void checkLogin()
    {
        if(!this.isLoggedIn())
        {
            Intent i = new Intent(_context,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }else {
            Intent i =new Intent(_context,MainMap.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);

        }
    }
    public HashMap<String , String> getUserDetails()
    {
        HashMap<String,String> user = new HashMap<String, String>();
        user.put(KEYFNAME,pref.getString(KEYFNAME,null));
        user.put(KEYLNAME,pref.getString(KEYLNAME,null));
        user.put(KEYUID,pref.getString(KEYUID,null));
        return user;
    }

    public void logoutUser()
    {
        editor.clear();
        editor.commit();
        Intent i =new Intent(_context,LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }
    public boolean isLoggedIn()
    {
        return pref.getBoolean(ISLOGIN,false);
    }
}
