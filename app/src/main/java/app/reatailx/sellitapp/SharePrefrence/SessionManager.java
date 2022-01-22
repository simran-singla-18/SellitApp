package app.reatailx.sellitapp.SharePrefrence;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by dheeraj on 1/24/2019.
 */

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside
    public static final String KEY_VERDORID = "vendorid";
    public static final String KEY_AGENTID = "agentid";
    public static final String KEY_ROLE = "role";
    public static final String KEY_VENDOREMAIL = "vendoremail";
    public static final String KEY_VENDORMOBILE = "vendorrole";
    public static final String KEY_RAZORPAYACCOUNTID = "razorpayaccountid";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createuserdataSession(String vendorid, String role, String agentid, String vendoremail, String vendormobile, String razorpayaccountid) {
        editor.putString(KEY_VERDORID, vendorid);
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_AGENTID, agentid);
        editor.putString(KEY_VENDOREMAIL, vendoremail);
        editor.putString(KEY_VENDORMOBILE, vendormobile);
        editor.putString(KEY_RAZORPAYACCOUNTID, razorpayaccountid);
        editor.commit();
    }

    public HashMap<String, String> getUserdata() {
        HashMap<String, String> userdata = new HashMap<String, String>();
        userdata.put(KEY_VERDORID, pref.getString(KEY_VERDORID, null));
        userdata.put(KEY_ROLE, pref.getString(KEY_ROLE, null));
        userdata.put(KEY_AGENTID, pref.getString(KEY_AGENTID, null));
        userdata.put(KEY_VENDOREMAIL, pref.getString(KEY_VENDOREMAIL, null));
        userdata.put(KEY_VENDORMOBILE, pref.getString(KEY_VENDORMOBILE, null));
        userdata.put(KEY_RAZORPAYACCOUNTID, pref.getString(KEY_RAZORPAYACCOUNTID, null));
        return userdata;
    }


}
