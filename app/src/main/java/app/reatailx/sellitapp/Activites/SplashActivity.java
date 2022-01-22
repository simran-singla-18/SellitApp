package app.reatailx.sellitapp.Activites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class SplashActivity extends AppCompatActivity {
    private Timer t;
    private String versionid;
    public SessionManager session;
    public String vendorid = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            System.out.println("User_Session_Data_Vendorid:::::" + vendorid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (vendorid == null) {
                    Intent intenttureone = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intenttureone);
                    finish();
                } else {
                    Intent intenttureonemain = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intenttureonemain);
                    finish();
                }

            }
        }, 1500);


    }
}