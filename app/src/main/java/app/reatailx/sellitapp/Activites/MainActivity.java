package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.snackbar.Snackbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kaopiz.kprogresshud.KProgressHUD;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.reatailx.sellitapp.Adapters.Inprogress_Adapter;
import app.reatailx.sellitapp.Adapters.Main_Adapter;
import app.reatailx.sellitapp.Models.Inprogressmodel;
import app.reatailx.sellitapp.Models.Mainactivitymodel;
import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;
import app.reatailx.sellitapp.app.Config;
import app.reatailx.sellitapp.util.NotificationUtils;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    public AppBarConfiguration mAppBarConfiguration;
    public DrawerLayout mDrawerLayout;
    public NavigationView navigationView;
    public SessionManager session;
    public LinearLayoutManager linearLayoutManager, linearLayoutManager2;
    public ArrayList<Mainactivitymodel> modleonefgr_array1 = new ArrayList<Mainactivitymodel>();
    public Mainactivitymodel modleonefgr;
    public Main_Adapter listAdapter;
    public ArrayList<Inprogressmodel> modleonefgr_array2 = new ArrayList<Inprogressmodel>();
    public Inprogressmodel modleonefgr2;
    public Inprogress_Adapter listAdapter2;
    public RecyclerView recyclerView, recyclerViewagent;
    public ProgressBar mprocessingdialog;
    public MainActivity activity;
    public String vendorid, roleid, agentid = "";
    private KProgressHUD kProgressHUD;
    public TextView tv_header;
    public String dateInString = "";
    public int dateInStringint;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        try {
            String pattern = "ddyy";
            // String pattern = "dd-MMM-yy";
            //String pattern = "ddyyyy";
            dateInString = new SimpleDateFormat(pattern).format(new Date());
            System.out.println("getting_dateInString " + dateInString);
            dateInStringint = Integer.parseInt(dateInString);
            System.out.println("getting_dateInString_i " + dateInStringint);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            agentid = user.get(SessionManager.KEY_AGENTID);
            roleid = user.get(SessionManager.KEY_ROLE);
            System.out.println("User_Session_Data_Vendoridmainactivity:::::" + vendorid + "  " + roleid + "  " + agentid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        swipeRefreshLayout = findViewById(R.id.swiperefreshmain);
        tv_header = findViewById(R.id.tv_header);
        mprocessingdialog = findViewById(R.id.pbar_userorder);
        recyclerView = findViewById(R.id.recycle_userorderlist);
        recyclerViewagent = findViewById(R.id.recycle_agentlist);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (roleid.equalsIgnoreCase("ajent")) {
            recyclerViewagent.setVisibility(View.VISIBLE);
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_Available).setVisible(false);
            nav_Menu.findItem(R.id.nav_transaction).setVisible(false);
            nav_Menu.findItem(R.id.nav_payment).setVisible(false);
            nav_Menu.findItem(R.id.nav_tobefailed).setVisible(false);
            nav_Menu.findItem(R.id.nav_complete).setVisible(false);
            nav_Menu.findItem(R.id.nav_failed).setVisible(false);
            nav_Menu.findItem(R.id.nav_agent).setVisible(false);
            tv_header.setText("In Progress");
            if (networkInfo != null && networkInfo.isConnected()) {
                // fetch data
                progressDialog();
                getJSONAgentdata();
            } else {
                mprocessingdialog.setVisibility(View.GONE);
                Snackbar.make(this.findViewById(android.R.id.content), getResources().getString(R.string.connecttointenet), Snackbar.LENGTH_LONG).show();
            }
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tv_header.setText("Available");
            if (networkInfo != null && networkInfo.isConnected()) {
                // fetch data
                progressDialog();
                getJSON();
            } else {
                mprocessingdialog.setVisibility(View.GONE);
                Snackbar.make(this.findViewById(android.R.id.content), getResources().getString(R.string.connecttointenet), Snackbar.LENGTH_LONG).show();
            }


            mRegistrationBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    // checking for type intent filter
                    if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                        // gcm successfully registered
                        // now subscribe to `global` topic to receive app wide notifications
                        FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                        displayFirebaseRegId();

                    } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                        // new push notification is received

                        String message = intent.getStringExtra("message");

                        Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                        //txtMessage.setText(message);
                    }
                }
            };

            displayFirebaseRegId();
        }


        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        swipeRefreshLayout.setRefreshing(true);
                        if (roleid.equalsIgnoreCase("ajent")) {
                            modleonefgr_array2.clear();
                            getJSONAgentdata();
                        } else {
                            modleonefgr_array1.clear();
                            getJSON();
                        }

                    }
                }
        );

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener((OnNavigationItemSelectedListener) this);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        listAdapter = new Main_Adapter(modleonefgr_array1, getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);

        linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        listAdapter2 = new Inprogress_Adapter(modleonefgr_array2, getApplicationContext());
        recyclerViewagent.setLayoutManager(linearLayoutManager2);
        recyclerViewagent.setItemAnimator(new DefaultItemAnimator());
        recyclerViewagent.setHasFixedSize(true);
        recyclerViewagent.setAdapter(listAdapter2);
    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);
        //Toast.makeText(getApplicationContext(),"reg id"+regId,Toast.LENGTH_LONG).show();

        if (!TextUtils.isEmpty(regId))

            // txtRegId.setText("Firebase Reg Id: " + regId);
            Toast.makeText(getApplicationContext(), "Firebase Reg Id: " + regId, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Firebase Reg Id is not received yet! " + regId, Toast.LENGTH_LONG).show();
        // txtRegId.setText("Firebase Reg Id is not received yet!");
    }

    private void getJSONAgentdata() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/ajantleads.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Result_verify_fragment_onemain:::::" + response);
                        if (response == null) {
                            swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                            dismissDialog();
                        } else {
                            String s = response.trim();
                            System.out.println("msg_fonemain:::" + s);
                            dismissDialog();
                            swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                if (status.equalsIgnoreCase("1")) {
                                    JSONArray jarray = jsonObject.getJSONArray("leads_information");
                                    for (int hk = 0; hk < jarray.length(); hk++) {
                                        JSONObject d = jarray.getJSONObject(hk);
                                        modleonefgr2 = new Inprogressmodel();
                                        modleonefgr2.setLead_id(d.get("lead_id").toString());
                                        modleonefgr2.setVarientname(d.get("varientname").toString());
                                        modleonefgr2.setVendorname(d.get("vendorname").toString());
                                        modleonefgr2.setAjentname(d.get("ajentname").toString());
                                        modleonefgr2.setModel_name(d.get("model_name").toString());
                                        modleonefgr2.setImageurl(d.get("imageurl").toString());
                                        modleonefgr2.setPrice(d.get("price").toString());
                                        modleonefgr2.setLead_pick_status(d.get("lead_pick_status").toString());
                                        modleonefgr2.setLead_pick_date(d.get("lead_pick_date").toString());
                                        modleonefgr2.setLead_pick_time(d.get("lead_pick_time").toString());
                                        modleonefgr2.setLead_pick_month(d.get("lead_pick_month").toString());
                                        modleonefgr2.setLead_pick_year(d.get("lead_pick_year").toString());
                                        modleonefgr2.setModify_date(d.get("modify_date").toString());
                                        modleonefgr2.setCity(d.get("city").toString());
                                        modleonefgr2.setLeadstatus(d.get("lead_status").toString());
                                        modleonefgr2.setRole(roleid);
                                        //modleonefgr.setDatecustome(dateInString);
                                        modleonefgr2.setDatecustome(String.valueOf(dateInStringint));
                                        modleonefgr_array2.add(modleonefgr2);
                                        listAdapter2.notifyDataSetChanged();
                                    }
                                } else {
                                    swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                        dismissDialog();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("vendorid", vendorid);
                params.put("ajentid", agentid);
                params.put("flag", "Inprogress");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void progressDialog() {
        kProgressHUD = new KProgressHUD(MainActivity.this);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait...")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    private void dismissDialog() {
        if (kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
    }

    private void getJSON() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, " https://sellit.co.in/logisticapi/v1/allleads.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Result_verify_fragment_onemain:::::" + response);
                        if (response == null) {
                            swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                            dismissDialog();
                        } else {
                            String s = response.trim();
                            System.out.println("msg_fonemain:::" + s);
                            dismissDialog();
                            swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");
                                //String message = jsonObject.getString("message");
                                if (status.equalsIgnoreCase("1")) {
                                    JSONArray jarray = jsonObject.getJSONArray("leads_information");
                                    for (int hk = 0; hk < jarray.length(); hk++) {
                                        JSONObject d = jarray.getJSONObject(hk);
                                        modleonefgr = new Mainactivitymodel();
                                        modleonefgr.setLead_id(d.get("lead_id").toString());
                                        modleonefgr.setVarientname(d.get("varientname").toString());
                                        modleonefgr.setModel_name(d.get("model_name").toString());
                                        modleonefgr.setImageurl(d.get("imageurl").toString());
                                        modleonefgr.setPrice(d.get("price").toString());
                                        modleonefgr.setLead_pick_status(d.get("lead_pick_status").toString());
                                        modleonefgr.setLead_pick_date(d.get("lead_pick_date").toString());
                                        modleonefgr.setLead_pick_time(d.get("lead_pick_time").toString());
                                        modleonefgr.setLead_pick_month(d.get("lead_pick_month").toString());
                                        modleonefgr.setLead_pick_year(d.get("lead_pick_year").toString());
                                        modleonefgr.setModify_date(d.get("modify_date").toString());
                                        modleonefgr.setCity(d.get("city").toString());
                                        modleonefgr.setLeadstatus(d.get("lead_status").toString());
                                        modleonefgr.setLeadstatus(d.get("lead_status").toString());
                                        modleonefgr.setRole(roleid);
                                        //modleonefgr.setDatecustome(dateInString);
                                        modleonefgr.setDatecustome(String.valueOf(dateInStringint));
                                        modleonefgr_array1.add(modleonefgr);
                                        listAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                                    Toast.makeText(getApplicationContext(), "No Record Found !!", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                        dismissDialog();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("vendorid", vendorid);
                params.put("flag", "Available");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mDrawerLayout.closeDrawers();
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_Available:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_inprogress:
                if (roleid.equalsIgnoreCase("vendor")) {
                    Intent intentip = new Intent(MainActivity.this, InprogressActivity.class);
                    startActivity(intentip);
                } else {
                    Intent intentip = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intentip);
                }
                break;
            case R.id.nav_agent:
                Intent intenta = new Intent(MainActivity.this, AgentMainActivity.class);
                startActivity(intenta);
                break;
            case R.id.nav_today:
                Intent intentto = new Intent(MainActivity.this, TodaysTommarrowLeadActivity.class);
                intentto.putExtra("date", "today");
                intentto.putExtra("header", "Today");
                startActivity(intentto);
                break;
            case R.id.nav_tomorrow:
                Intent intentom = new Intent(MainActivity.this, TodaysTommarrowLeadActivity.class);
                intentom.putExtra("date", "tomorrow");
                intentom.putExtra("header", "Tomorrow");
                startActivity(intentom);
                break;
            case R.id.nav_payment:
                //Intent intentpaycw = new Intent(MainActivity.this, PaymentCommisionWalletActivity.class);
                Intent intentpaycw = new Intent(MainActivity.this, WalletMainPaymentActivity.class);
                startActivity(intentpaycw);
                break;
            case R.id.nav_complete:
                Intent intentcl = new Intent(MainActivity.this, CompleteLeadActivity.class);
                startActivity(intentcl);
                break;
            case R.id.nav_tobefailed:
                Intent intenttbf = new Intent(MainActivity.this, TobeFailedLeadActivity.class);
                startActivity(intenttbf);
                break;
            case R.id.nav_profile:
                Intent intentp = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intentp);
                break;
            case R.id.nav_transaction:
                Intent intentrn = new Intent(MainActivity.this, TransactionActivity.class);
                startActivity(intentrn);
                break;
            case R.id.nav_logout:
                try {
                    @SuppressLint("SdCardPath") File sharedPreferenceFile = new File("/data/data/" + getPackageName() + "/shared_prefs/");
                    File[] listFiles = sharedPreferenceFile.listFiles();
                    for (File file : listFiles) {
                        file.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    File dir = getApplicationContext().getCacheDir();
                    deleteDir(dir);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intentlogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentlogin);
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    private boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
