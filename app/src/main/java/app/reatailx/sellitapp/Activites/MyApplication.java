package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.google.firebase.iid.FirebaseInstanceIdReceiver;

import app.reatailx.sellitapp.service.MyFirebaseInstanceIDService;
import app.reatailx.sellitapp.service.MyFirebaseMessagingService;

/**
 * Created by dheeraj on 19-Apr-19.
 */

public class MyApplication extends MultiDexApplication {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}