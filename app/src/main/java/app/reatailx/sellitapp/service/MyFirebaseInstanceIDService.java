package app.reatailx.sellitapp.service;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import app.reatailx.sellitapp.app.Config;

/**
 * Created by Ravi Tamada on 08/08/16.
 * www.androidhive.info
 */
public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.e("Refreshed token:", token);
        //String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        String refreshedToken = token;
        System.out.println("getting_refreshedToken " + refreshedToken);

        // Saving reg id to shared preferences

            storeRegIdInPref(refreshedToken);

            // sending reg id to your server
            sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + refreshedToken);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

    }

    /*  @Override
      public void onTokenRefresh() {
          super.onTokenRefresh();
          String refreshedToken = FirebaseInstanceId.getInstance().getToken();

          // Saving reg id to shared preferences
          storeRegIdInPref(refreshedToken);

          // sending reg id to your server
          sendRegistrationToServer(refreshedToken);

          // Notify UI that registration has completed, so the progress indicator can be hidden.
          Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
          registrationComplete.putExtra("token", refreshedToken);
          LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
      }
  */
    /*private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
    }*/

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();
    }
}

