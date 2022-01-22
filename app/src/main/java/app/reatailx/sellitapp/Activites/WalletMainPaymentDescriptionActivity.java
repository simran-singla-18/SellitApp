package app.reatailx.sellitapp.Activites;


import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;

import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class WalletMainPaymentDescriptionActivity extends Activity {
    private static final String TAG = WalletMainPaymentDescriptionActivity.class.getSimpleName();
    private String vendorid, lead_id, vendoremail, vendormobile, amount, id, razorpayaccountid;
    private SessionManager session;
    private RelativeLayout relativeLayout;
    private String orderId, paymentId, signature;
    private KProgressHUD kProgressHUD;
    private String description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.paymetwallet_description_activity);
        session = new SessionManager(getApplicationContext());
        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            vendoremail = user.get(SessionManager.KEY_VENDOREMAIL);
            vendormobile = user.get(SessionManager.KEY_VENDORMOBILE);
            razorpayaccountid = user.get(SessionManager.KEY_RAZORPAYACCOUNTID);
            System.out.println("User_Session_Data_Vendorid_payment:::::" + vendorid + "  " + vendoremail + "  " + vendormobile + "  " + razorpayaccountid);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
