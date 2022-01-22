package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class RequoteActivity extends AppCompatActivity implements View.OnClickListener {
    private KProgressHUD kProgressHUD;
    public SessionManager session;
    public String vendorid, agentid = "", role, warrenty = "";
    public Button btn_completeddcrtyt;
    public RelativeLayout relativeLayout;
    public RadioGroup radioGroupcopydisplay;
    public RadioButton radiobuttoncopydisplay;
    public RadioButton radio_yescopydisplay, radio_nocopydisplay;
    public RadioGroup radioGroupmakeacall;
    public RadioButton radiobuttonup;
    public RadioGroup radioGrouptouchscreenworking;
    public RadioButton radiobuttoncd;
    public RadioGroup radioGroupsn;
    public RadioGroup radioGroupdmobileage;
    public RadioButton radiobuttonmobileage;
    public RadioGroup radioGroupdeadpixel;
    public RadioButton radiobuttondeadpixel;
    public RadioGroup radioGroupdvisiblelines;
    public RadioButton radiobuttonvisiblelines;
    public RadioGroup radioGroupdscreenphysical;
    public RadioButton radiobuttonscreenphysical;
    public RadioGroup radioGroupdscratchedon;
    public RadioButton radiobuttonscratchedon;
    public RadioGroup radioGroupddentonphone;
    public RadioButton radiobuttonddentonphone;
    public RadioGroup radioGroupdphonesideback;
    public RadioButton radiobuttonphonesideback;
    public RadioGroup radioGroupdbentphone;
    public RadioButton radiobuttondbentphone;
    public LinearLayout lnr_frontcameramain, lnr_backcameramain, lnr_wifimain, lnr_volumebuttonmain, lnr_batterymain, lnr_speakermain, lnr_fingertouchmain, lnr_bluetoothmain, lnr_charging_portmain, lnr_powerbuttonmain, lnr_facesensormain, lnr_audioicmain, lnr_cameraglassmain, lnr_silent_buttonmain, lnr_microphonemain, lnr_vibratormain, lnr_earphonemain, lnr_validbillmain, lnr_chargermain, lnr_boxmain;
    public CheckBox cb_mobileswitchon, cb_froncamera, cb_backcamera, cb_wifi, cb_volumebutton, cb_battery, cb_speaker, cb_fingertouch, cb_bluetooth, cb_charging_porter, cb_charging_port, cb_powerbutton, cb_facesensor, cb_audioic, cd_cameraglass, cb_silent_button, cb_microphone, cb_vibrator, cb_copydisplay, cb_earphone, cb_validbill, cb_charger, cb_box, cb_underthreemonth, cb_underthreesixmonth, cb_undersixelelevenmonth, cb_aboveelevenmonth, cb_largeheavy, cb_multiplevisible, cb_minordiscoloration, cb_displayfaded, cb_multipleline, cb_nolineondisplay, cb_screencracked, cb_damagedtorn, cb_heavyscratch, cb_scratchfourtwoonscreen, cb_noscratchonscreen, cb_majorscratched, cb_lessthentwoscratched, cb_noscratched, cb_multipleheavyvisiblebodydent, cb_twoorlessminorbodydents, cb_nodents, cb_crackedbrokensideorbackpanel, cb_missingbacksidebackpanel, cb_nodefectonsideorbackpanel, cb_bentcurvedpanel, cb_loosescreengapinscreenandbody, cb_phonenotbent;
    public String mobileswitchon = "", frontcamera = "", backcamera = "", wifi = "", volumebutton = "", battery = "", speaker = "", fingertouch = "", bluetooth = "", charging_porter = "", charging_port = "", powerbutton = "", facesensor = "", audioic = "", cameraglass = "", silent_button = "", microphone = "", vibrator = "", copydisplay = "", earphone = "", validbill = "", charger = "", box = "", bodybent = "", bodysidebackpanel = "", bodydents = "", bodyscratches = "", screenphysicalcondition = "", screenlines = "", screenspots = "", mobileage = "", touchscreen = "", callrecieve = "", underthreemonth = "", underthreesixmonth = "", undersixelelevenmonth = "", aboveelevenmonth = "", largeheavy = "", multiplevisible = "", minordiscoloration = "", displayfaded = "", multipleline = "", nolineondisplay = "", screencracked = "", damagedtorn = "", heavyscratch = "", scratchfourtwoonscreen = "", noscratchonscreen = "", majorscratched = "", lessthentwoscratched = "", noscratched = "", multipleheavyvisiblebodydent = "", twoorlessminorbodydents = "", nodents = "", crackedbrokensideorbackpanel = "", missingbacksidebackpanel = "", nodefectonsideorbackpanel = "", bentcurvedpanel = "", loosescreengapinscreenandbody = "", phonenotbent = "";
    public Button button_submit;
    public String warrentyserver = "", cat_id = "", deviceid = "", varientid = "", brandid = "", lead_id = "", front_camera, back_camera, wifi_, volume_button, battery_, speaker_, finger_touch, bluetooth_, charging_porter_, charging_port_, powerbutton_, copy_display = "", facesensor_, audioic_, cameraglass_, silentbutton_, microphone_, vibrator_, earphone_, validbill_, charger_, box_, mobileage_, abletotakecall_, touchworking_, display_spot_, screenlines_, screen_Condition_, body_scratches_, body_dents_, side_back_panel_, body_bents_;
    public TextView tv_frontcamera, tv_backcamera, tv_wifi, tv_volumebutton, tv_batteryworking, tv_speakingworking, tv_fingersensorgworking, tv_bluetoothworking, tv_charninggworking, tv_workingporter, tv_powerbutton, tv_facesensor, tv_audioic, tv_cameraglass, tv_silentbutton, tv_microphone, tv_vibrater, tv_copydisplay, tv_earphone, tv_validbill, tv_charge, tv_box;
    public RadioButton rb_underthree, rb_underthreesix, rb_undersixeleven, rb_underaboveleven;
    public RadioButton radio_yesabletoreceivecall, radio_noabletoreceivecall;
    public RadioButton radio_yestouchworking, radio_notouchworking;
    public RadioButton rb_largeheavyvisiblespotsonscreens, rb_multiplevisiblespotsonscreens, rb_minordiscolorationorsmallspotsonscreens, rb_nospotsonscreens;
    public RadioButton rb_displayfadedaloncornerscreen, rb_multiplelineondisplay, rb_nolinesondisplay;
    public RadioButton rb_screencrackedglassbroken, rb_damagtornscreenonedges, rb_heavyscartchedonscreen, rb_twofourscratchedonscreen, rb_noscratchedonscreen;
    public RadioButton rb_majorscratched, rb_lessthentwoscratched, rb_noscratched;
    public RadioButton rb_multipleheavyvisiblebodydents, rb_twoorlessminorbodydents, rb_nodents;
    public RadioButton rb_crackedbrokensideorbackpanel, rb_missingbacksideorbackpanel, rb_nodefectonsideorbackpanel;
    public RadioButton rb_bentcurvedpanel, rb_loosescreengapinscreenandbody, rb_phonenotbent;
    public LinearLayout lnr_frontcamera;
    public LinearLayout lnr_overclick;

    List<String> containreqoutedata = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requote_item_detail);

        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            agentid = user.get(SessionManager.KEY_AGENTID);
            role = user.get(SessionManager.KEY_ROLE);
            System.out.println("User_Session_Data_requote:::::" + vendorid + "   " + agentid + "   " + role);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Bundle intent = getIntent().getExtras();
        if (intent != null) {
            cat_id = intent.get("cat_id").toString().trim();
            deviceid = intent.get("deviceid").toString().trim();
            varientid = intent.get("varientid").toString().trim();
            brandid = intent.get("brandid").toString().trim();
            lead_id = intent.get("lead_id").toString().trim();
            front_camera = intent.get("front_camera").toString().trim();
            back_camera = intent.get("back_camera").toString().trim();
            wifi_ = intent.get("wifi").toString().trim();
            volume_button = intent.get("volume_button").toString().trim();
            battery_ = intent.get("battery").toString().trim();
            speaker_ = intent.get("speaker").toString().trim();
            finger_touch = intent.get("finger_touch").toString().trim();
            bluetooth_ = intent.get("bluetooth").toString().trim();
            charging_port_ = intent.get("charging_port").toString().trim();
            powerbutton_ = intent.get("powerbutton").toString().trim();
            facesensor_ = intent.get("facesensor").toString().trim();
            copydisplay = intent.get("copy_display").toString().trim();
            audioic_ = intent.get("audioic").toString().trim();
            cameraglass_ = intent.get("cameraglass").toString().trim();
            silentbutton_ = intent.get("silentbutton").toString().trim();
            microphone_ = intent.get("microphone").toString().trim();
            vibrator_ = intent.get("vibrator").toString().trim();
            earphone_ = intent.get("earphone").toString().trim();
            validbill_ = intent.get("validbill").toString().trim();
            charger_ = intent.get("charger").toString().trim();
            box_ = intent.get("box").toString().trim();
            mobileage_ = intent.get("mobileage").toString().trim();
            mobileage = intent.get("mobileage").toString().trim();
            callrecieve = intent.get("abletotakecall").toString().trim();
            touchscreen = intent.get("touchworking").toString().trim();
            screenspots = intent.get("display_spot").toString().trim();
            screenlines = intent.get("screenlines").toString().trim();
            screenphysicalcondition = intent.get("screen_Condition").toString().trim();
            bodyscratches = intent.get("body_scratches").toString().trim();
            bodydents = intent.get("body_dents").toString().trim();
            bodysidebackpanel = intent.get("side_back_panel").toString().trim();
            bodybent = intent.get("body_bents").toString().trim();
            warrentyserver = intent.get("warrenty").toString().trim();
        }

        containreqoutedata.add("WiFi not working");
        containreqoutedata.add("Volume Button not working");
        containreqoutedata.add("Battery not working");
        containreqoutedata.add("Speaker not working");
        containreqoutedata.add("Finger Touch not working");
        containreqoutedata.add("Bluetooth not working");
        containreqoutedata.add("Charging Port not working");
        containreqoutedata.add("Power Button not working");
        containreqoutedata.add("Face Sensor not working");
        containreqoutedata.add("Audio Receiver not working");
        containreqoutedata.add("Camera Glass Broken");
        containreqoutedata.add("Silent Button not working");
        containreqoutedata.add("Microphone is not working");
        containreqoutedata.add("Vibrator is not working");
        containreqoutedata.add("Copy Display");
        containreqoutedata.add("Bill with same IMEI");
        containreqoutedata.add("Above 11 Months");
        containreqoutedata.add("Not Able To Take Calls");
        containreqoutedata.add("Touch Faulty");
        containreqoutedata.add("Large/ heavy visible spots on screen");
        containreqoutedata.add("Multiple visible spots on screen");
        containreqoutedata.add("Minor discoloration or small spots on screen");
        containreqoutedata.add("Display faded along corners ");
        containreqoutedata.add("Multiple lines on Display");
        containreqoutedata.add("Screen cracked/ glass broken");
        containreqoutedata.add("Damaged/ Torn screen on edges");
        containreqoutedata.add("Heavy scratches on screen");
        containreqoutedata.add("1-2 scratches on screen");
        containreqoutedata.add("Cracked/ broken side or back panel");
        containreqoutedata.add("Missing side or back panel");
        containreqoutedata.add("Bent/ curved panel");
        containreqoutedata.add("Loose screen (Gap in screen and body)");

        lnr_frontcameramain = findViewById(R.id.lnr_frontcameramain);
        lnr_backcameramain = findViewById(R.id.lnr_backcameramain);
        lnr_wifimain = findViewById(R.id.lnr_wifimain);
        lnr_volumebuttonmain = findViewById(R.id.lnr_volumebuttonmain);
        lnr_batterymain = findViewById(R.id.lnr_batterymain);
        lnr_speakermain = findViewById(R.id.lnr_speakermain);
        lnr_fingertouchmain = findViewById(R.id.lnr_fingertouchmain);
        lnr_bluetoothmain = findViewById(R.id.lnr_bluetoothmain);
        lnr_charging_portmain = findViewById(R.id.lnr_charging_portmain);
        lnr_powerbuttonmain = findViewById(R.id.lnr_powerbuttonmain);
        lnr_facesensormain = findViewById(R.id.lnr_facesensormain);
        lnr_audioicmain = findViewById(R.id.lnr_audioicmain);
        lnr_cameraglassmain = findViewById(R.id.lnr_cameraglassmain);
        lnr_silent_buttonmain = findViewById(R.id.lnr_silent_buttonmain);
        lnr_microphonemain = findViewById(R.id.lnr_microphonemain);
        lnr_vibratormain = findViewById(R.id.lnr_vibratormain);

        lnr_earphonemain = findViewById(R.id.lnr_earphonemain);
        lnr_validbillmain = findViewById(R.id.lnr_validbillmain);
        lnr_chargermain = findViewById(R.id.lnr_chargermain);
        lnr_boxmain = findViewById(R.id.lnr_boxmain);

        lnr_frontcamera = findViewById(R.id.lnr_frontcamera);
        rb_underthree = findViewById(R.id.rb_underthree);
        rb_underthreesix = findViewById(R.id.rb_underthreesix);
        rb_undersixeleven = findViewById(R.id.rb_undersixeleven);
        rb_underaboveleven = findViewById(R.id.rb_underaboveleven);

        radio_yescopydisplay = findViewById(R.id.radio_yescopydisplay);
        radio_nocopydisplay = findViewById(R.id.radio_nocopydisplay);

        radio_yesabletoreceivecall = findViewById(R.id.radio_yesabletoreceivecall);
        radio_noabletoreceivecall = findViewById(R.id.radio_noabletoreceivecall);
        radio_yestouchworking = findViewById(R.id.radio_yestouchworking);
        radio_notouchworking = findViewById(R.id.radio_notouchworking);
        rb_largeheavyvisiblespotsonscreens = findViewById(R.id.rb_largeheavyvisiblespotsonscreens);
        rb_multiplevisiblespotsonscreens = findViewById(R.id.rb_multiplevisiblespotsonscreens);
        rb_minordiscolorationorsmallspotsonscreens = findViewById(R.id.rb_minordiscolorationorsmallspotsonscreens);
        rb_nospotsonscreens = findViewById(R.id.rb_nospotsonscreens);
        rb_displayfadedaloncornerscreen = findViewById(R.id.rb_displayfadedaloncornerscreen);
        rb_multiplelineondisplay = findViewById(R.id.rb_multiplelineondisplay);
        rb_nolinesondisplay = findViewById(R.id.rb_nolinesondisplay);
        rb_screencrackedglassbroken = findViewById(R.id.rb_screencrackedglassbroken);
        rb_damagtornscreenonedges = findViewById(R.id.rb_damagtornscreenonedges);
        rb_heavyscartchedonscreen = findViewById(R.id.rb_heavyscartchedonscreen);
        rb_twofourscratchedonscreen = findViewById(R.id.rb_twofourscratchedonscreen);
        rb_noscratchedonscreen = findViewById(R.id.rb_noscratchedonscreen);
        rb_majorscratched = findViewById(R.id.rb_majorscratched);
        rb_lessthentwoscratched = findViewById(R.id.rb_lessthentwoscratched);
        rb_noscratched = findViewById(R.id.rb_noscratched);
        rb_multipleheavyvisiblebodydents = findViewById(R.id.rb_multipleheavyvisiblebodydents);
        rb_twoorlessminorbodydents = findViewById(R.id.rb_twoorlessminorbodydents);
        rb_nodents = findViewById(R.id.rb_nodents);
        rb_crackedbrokensideorbackpanel = findViewById(R.id.rb_crackedbrokensideorbackpanel);
        rb_missingbacksideorbackpanel = findViewById(R.id.rb_missingbacksideorbackpanel);
        rb_nodefectonsideorbackpanel = findViewById(R.id.rb_nodefectonsideorbackpanel);
        rb_bentcurvedpanel = findViewById(R.id.rb_bentcurvedpanel);
        rb_loosescreengapinscreenandbody = findViewById(R.id.rb_loosescreengapinscreenandbody);
        rb_phonenotbent = findViewById(R.id.rb_phonenotbent);
        tv_frontcamera = findViewById(R.id.tv_frontcamera);
        tv_backcamera = findViewById(R.id.tv_backcamera);
        tv_wifi = findViewById(R.id.tv_wifi);
        tv_volumebutton = findViewById(R.id.tv_volumebutton);
        tv_batteryworking = findViewById(R.id.tv_batteryworking);
        tv_speakingworking = findViewById(R.id.tv_speakingworking);
        tv_fingersensorgworking = findViewById(R.id.tv_fingersensorgworking);
        tv_bluetoothworking = findViewById(R.id.tv_bluetoothworking);
        tv_charninggworking = findViewById(R.id.tv_charninggworking);
        tv_workingporter = findViewById(R.id.tv_workingporter);
        tv_powerbutton = findViewById(R.id.tv_powerbutton);
        tv_facesensor = findViewById(R.id.tv_facesensor);
        tv_audioic = findViewById(R.id.tv_audioic);
        tv_cameraglass = findViewById(R.id.tv_cameraglass);
        tv_silentbutton = findViewById(R.id.tv_silentbutton);
        tv_microphone = findViewById(R.id.tv_microphone);
        tv_vibrater = findViewById(R.id.tv_vibrater);
        tv_copydisplay = findViewById(R.id.tv_copydisplay);
        tv_earphone = findViewById(R.id.tv_earphone);
        tv_validbill = findViewById(R.id.tv_validbill);
        tv_charge = findViewById(R.id.tv_charge);
        tv_box = findViewById(R.id.tv_box);
        button_submit = findViewById(R.id.btn_completeddcrtyt);
        button_submit.setOnClickListener(this);
        radioGroupcopydisplay = findViewById(R.id.radioGroupcopydisplay);
        radioGroupmakeacall = findViewById(R.id.radioGroupablemakecall);
        radioGroupsn = findViewById(R.id.radioGroupsn);
        radioGrouptouchscreenworking = findViewById(R.id.radioGroupcoptouchscreenwproperly);
        radioGroupdmobileage = findViewById(R.id.radioGroupdmobileage);
        radioGroupdeadpixel = findViewById(R.id.radioGroupdeadpixel);
        radioGroupdvisiblelines = findViewById(R.id.radioGroupdvisiblelines);
        radioGroupdscreenphysical = findViewById(R.id.radioGroupdscreenphysical);
        radioGroupdscratchedon = findViewById(R.id.radioGroupdscratchedon);
        radioGroupddentonphone = findViewById(R.id.radioGroupddentonphone);
        radioGroupdphonesideback = findViewById(R.id.radioGroupdphonesideback);
        radioGroupdbentphone = findViewById(R.id.radioGroupdbentphone);
        btn_completeddcrtyt = findViewById(R.id.btn_completeddcrtyt);
        cb_mobileswitchon = findViewById(R.id.cb_mobileswitchon);
        cb_froncamera = findViewById(R.id.cb_froncamera);
        cb_backcamera = findViewById(R.id.cb_backcamera);
        cb_wifi = findViewById(R.id.cb_wifi);
        cb_volumebutton = findViewById(R.id.cb_volumebutton);
        cb_battery = findViewById(R.id.cb_battery);
        cb_speaker = findViewById(R.id.cb_speaker);
        cb_fingertouch = findViewById(R.id.cb_fingertouch);
        cb_bluetooth = findViewById(R.id.cb_bluetooth);
        cb_charging_porter = findViewById(R.id.cb_charging_porter);
        cb_charging_port = findViewById(R.id.cb_charging_port);
        cb_powerbutton = findViewById(R.id.cb_powerbutton);
        cb_facesensor = findViewById(R.id.cb_facesensor);
        cb_audioic = findViewById(R.id.cb_audioic);
        cd_cameraglass = findViewById(R.id.cd_cameraglass);
        cb_silent_button = findViewById(R.id.cb_silent_button);
        cb_microphone = findViewById(R.id.cb_microphone);
        cb_vibrator = findViewById(R.id.cb_vibrator);
        cb_copydisplay = findViewById(R.id.cb_copydisplay);
        cb_earphone = findViewById(R.id.cb_earphone);
        cb_validbill = findViewById(R.id.cb_validbill);
        cb_charger = findViewById(R.id.cb_charger);
        cb_box = findViewById(R.id.cb_box);
        cb_underthreemonth = findViewById(R.id.cb_underthreemonth);
        cb_underthreesixmonth = findViewById(R.id.cb_underthreesixmonth);
        cb_undersixelelevenmonth = findViewById(R.id.cb_undersixelelevenmonth);
        cb_aboveelevenmonth = findViewById(R.id.cb_aboveelevenmonth);
        cb_largeheavy = findViewById(R.id.cb_largeheavy);
        cb_multiplevisible = findViewById(R.id.cb_multiplevisible);
        cb_minordiscoloration = findViewById(R.id.cb_minordiscoloration);
        cb_displayfaded = findViewById(R.id.cb_displayfaded);
        cb_multipleline = findViewById(R.id.cb_multipleline);
        cb_nolineondisplay = findViewById(R.id.cb_nolineondisplay);
        cb_screencracked = findViewById(R.id.cb_screencracked);
        cb_damagedtorn = findViewById(R.id.cb_damagedtorn);
        cb_heavyscratch = findViewById(R.id.cb_heavyscratch);
        cb_scratchfourtwoonscreen = findViewById(R.id.cb_scratchfourtwoonscreen);
        cb_noscratchonscreen = findViewById(R.id.cb_noscratchonscreen);
        cb_majorscratched = findViewById(R.id.cb_majorscratched);
        cb_lessthentwoscratched = findViewById(R.id.cb_lessthentwoscratched);
        cb_noscratched = findViewById(R.id.cb_noscratched);
        cb_multipleheavyvisiblebodydent = findViewById(R.id.cb_multipleheavyvisiblebodydent);
        cb_twoorlessminorbodydents = findViewById(R.id.cb_twoorlessminorbodydents);
        cb_nodents = findViewById(R.id.cb_nodents);
        cb_crackedbrokensideorbackpanel = findViewById(R.id.cb_crackedbrokensideorbackpanel);
        cb_missingbacksidebackpanel = findViewById(R.id.cb_missingbacksidebackpanel);
        cb_nodefectonsideorbackpanel = findViewById(R.id.cb_nodefectonsideorbackpanel);
        cb_bentcurvedpanel = findViewById(R.id.cb_bentcurvedpanel);
        cb_loosescreengapinscreenandbody = findViewById(R.id.cb_loosescreengapinscreenandbody);
        cb_phonenotbent = findViewById(R.id.cb_phonenotbent);

        if (copydisplay.equalsIgnoreCase("Copy Display")) {//Positive
            radio_yescopydisplay.setChecked(true);
            radio_nocopydisplay.setChecked(false);
            warrenty = "Mobile Out of Warranty";
        } else {// negative
            radio_yescopydisplay.setChecked(false);
            radio_nocopydisplay.setChecked(true);
            warrenty = "Mobile Under Warranty";
        }

        if (touchscreen.equalsIgnoreCase("Touch Working")) {
            radio_yestouchworking.setChecked(true);
            radio_notouchworking.setChecked(false);
            warrenty = "Mobile Under Warranty";
        } else {
            radio_yestouchworking.setChecked(false);
            radio_notouchworking.setChecked(true);
            warrenty = "Mobile Out of Warranty";
        }
        if (callrecieve.equalsIgnoreCase("Able To Take Calls")) {
            radio_yesabletoreceivecall.setChecked(true);
            radio_noabletoreceivecall.setChecked(false);
            warrenty = "Mobile Under Warranty";
        } else {
            radio_yesabletoreceivecall.setChecked(false);
            radio_noabletoreceivecall.setChecked(true);
            warrenty = "Mobile Out of Warranty";
        }

        if (warrentyserver.equalsIgnoreCase("Mobile Out of Warranty")) {
            mobileage = "Above 11 Months";
            rb_underaboveleven.setChecked(true);
            warrenty = "Mobile Out of Warranty";
        } else {
            if (mobileage.equalsIgnoreCase("Under 3 Months")) {
                rb_underthree.setChecked(true);
                rb_underthreesix.setChecked(false);
                rb_undersixeleven.setChecked(false);
                rb_underaboveleven.setChecked(false);
                warrenty = "Mobile Under Warranty";
            }
            if (mobileage.equalsIgnoreCase("3 To 6 Months")) {
                rb_underthree.setChecked(false);
                rb_underthreesix.setChecked(true);
                rb_undersixeleven.setChecked(false);
                rb_underaboveleven.setChecked(false);
                warrenty = "Mobile Under Warranty";
            }
            if (mobileage.equalsIgnoreCase("6 To 11 Months")) {
                rb_underthree.setChecked(false);
                rb_underthreesix.setChecked(false);
                rb_undersixeleven.setChecked(true);
                rb_underaboveleven.setChecked(false);
                warrenty = "Mobile Under Warranty";
            }
            if (mobileage.equalsIgnoreCase("Above 11 Months")) {
                rb_underthree.setChecked(false);
                rb_underthreesix.setChecked(false);
                rb_undersixeleven.setChecked(false);
                rb_underaboveleven.setChecked(true);
                mobileage = "Above 11 Months";
                warrenty = "Mobile Out of Warranty";
            }
        }

        if (screenspots.equalsIgnoreCase("Large/ heavy visible spots on screen")) {
            rb_largeheavyvisiblespotsonscreens.setChecked(true);
            rb_multiplevisiblespotsonscreens.setChecked(false);
            rb_minordiscolorationorsmallspotsonscreens.setChecked(false);
            rb_nospotsonscreens.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (screenspots.equalsIgnoreCase("Multiple visible spots on screen")) {
            rb_largeheavyvisiblespotsonscreens.setChecked(false);
            rb_multiplevisiblespotsonscreens.setChecked(true);
            rb_minordiscolorationorsmallspotsonscreens.setChecked(false);
            rb_nospotsonscreens.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (screenspots.equalsIgnoreCase("Minor discoloration or small spots on screen")) {
            rb_largeheavyvisiblespotsonscreens.setChecked(false);
            rb_multiplevisiblespotsonscreens.setChecked(false);
            rb_minordiscolorationorsmallspotsonscreens.setChecked(true);
            rb_nospotsonscreens.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (screenspots.equalsIgnoreCase("No spots on screen") || screenspots.equalsIgnoreCase("") || screenspots.isEmpty()) {
            rb_largeheavyvisiblespotsonscreens.setChecked(false);
            rb_multiplevisiblespotsonscreens.setChecked(false);
            rb_minordiscolorationorsmallspotsonscreens.setChecked(false);
            rb_nospotsonscreens.setChecked(true);
            warrenty = "Mobile Under Warranty";
        }
        if (screenlines.equalsIgnoreCase("Display faded along corners")) {
            rb_displayfadedaloncornerscreen.setChecked(true);
            rb_multiplelineondisplay.setChecked(false);
            rb_nolinesondisplay.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (screenlines.equalsIgnoreCase("Multiple lines on Display")) {
            rb_displayfadedaloncornerscreen.setChecked(false);
            rb_multiplelineondisplay.setChecked(true);
            rb_nolinesondisplay.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (screenlines.equalsIgnoreCase("No line(s) on Display") || screenlines.equalsIgnoreCase("") || screenlines.isEmpty()) {
            rb_displayfadedaloncornerscreen.setChecked(false);
            rb_multiplelineondisplay.setChecked(false);
            rb_nolinesondisplay.setChecked(true);
            warrenty = "Mobile Under Warranty";
        }
        if (screenphysicalcondition.equalsIgnoreCase("Screen cracked/ glass broken")) {
            rb_screencrackedglassbroken.setChecked(true);
            rb_damagtornscreenonedges.setChecked(false);
            rb_heavyscartchedonscreen.setChecked(false);
            rb_twofourscratchedonscreen.setChecked(false);
            rb_noscratchedonscreen.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (screenphysicalcondition.equalsIgnoreCase("Damaged/ Torn screen on edges")) {
            rb_screencrackedglassbroken.setChecked(false);
            rb_damagtornscreenonedges.setChecked(true);
            rb_heavyscartchedonscreen.setChecked(false);
            rb_twofourscratchedonscreen.setChecked(false);
            rb_noscratchedonscreen.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (screenphysicalcondition.equalsIgnoreCase("Heavy scratches on screen")) {
            rb_screencrackedglassbroken.setChecked(false);
            rb_damagtornscreenonedges.setChecked(false);
            rb_heavyscartchedonscreen.setChecked(true);
            rb_twofourscratchedonscreen.setChecked(false);
            rb_noscratchedonscreen.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (screenphysicalcondition.equalsIgnoreCase("1-2 scratches on screen")) {
            rb_screencrackedglassbroken.setChecked(false);
            rb_damagtornscreenonedges.setChecked(false);
            rb_heavyscartchedonscreen.setChecked(false);
            rb_twofourscratchedonscreen.setChecked(true);
            rb_noscratchedonscreen.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (screenphysicalcondition.equalsIgnoreCase("No scratches on screen") || screenphysicalcondition.equalsIgnoreCase("") || screenphysicalcondition.isEmpty()) {
            rb_screencrackedglassbroken.setChecked(false);
            rb_damagtornscreenonedges.setChecked(false);
            rb_heavyscartchedonscreen.setChecked(false);
            rb_twofourscratchedonscreen.setChecked(false);
            rb_noscratchedonscreen.setChecked(true);
            warrenty = "Mobile Under Warranty";
        }
        if (bodyscratches.equalsIgnoreCase("Major scratches")) {
            rb_majorscratched.setChecked(true);
            rb_lessthentwoscratched.setChecked(false);
            rb_noscratched.setChecked(false);
            warrenty = "Mobile Under Warranty";
        }
        if (bodyscratches.equalsIgnoreCase("Less than 2 scratches")) {
            rb_majorscratched.setChecked(false);
            rb_lessthentwoscratched.setChecked(true);
            rb_noscratched.setChecked(false);
            warrenty = "Mobile Under Warranty";
        }
        if (bodyscratches.equalsIgnoreCase("No scratches") || bodyscratches.equalsIgnoreCase("") || bodyscratches.isEmpty()) {
            rb_majorscratched.setChecked(false);
            rb_lessthentwoscratched.setChecked(false);
            rb_noscratched.setChecked(true);
            warrenty = "Mobile Under Warranty";
        }
        if (bodydents.equalsIgnoreCase("Multiple/heavy visible body dents")) {
            rb_multipleheavyvisiblebodydents.setChecked(true);
            rb_twoorlessminorbodydents.setChecked(false);
            rb_nodents.setChecked(false);
            warrenty = "Mobile Under Warranty";
        }
        if (bodydents.equalsIgnoreCase("2 or less minor body dents")) {
            rb_multipleheavyvisiblebodydents.setChecked(false);
            rb_twoorlessminorbodydents.setChecked(true);
            rb_nodents.setChecked(false);
            warrenty = "Mobile Under Warranty";
        }
        if (bodydents.equalsIgnoreCase("No dents") || bodydents.equalsIgnoreCase("") || bodydents.isEmpty()) {
            rb_multipleheavyvisiblebodydents.setChecked(false);
            rb_twoorlessminorbodydents.setChecked(false);
            rb_nodents.setChecked(true);
            warrenty = "Mobile Under Warranty";
        }
        if (bodysidebackpanel.equalsIgnoreCase("Cracked/ broken side or back panel")) {
            rb_crackedbrokensideorbackpanel.setChecked(true);
            rb_missingbacksideorbackpanel.setChecked(false);
            rb_nodefectonsideorbackpanel.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (bodysidebackpanel.equalsIgnoreCase("Missing side or back panel")) {
            rb_crackedbrokensideorbackpanel.setChecked(false);
            rb_missingbacksideorbackpanel.setChecked(true);
            rb_nodefectonsideorbackpanel.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (bodysidebackpanel.equalsIgnoreCase("No defect on side or back panel") || bodysidebackpanel.equalsIgnoreCase("") || bodysidebackpanel.isEmpty()) {
            rb_crackedbrokensideorbackpanel.setChecked(false);
            rb_missingbacksideorbackpanel.setChecked(false);
            rb_nodefectonsideorbackpanel.setChecked(true);
            warrenty = "Mobile Under Warranty";
        }
        if (bodybent.equalsIgnoreCase("Bent/ curved panel")) {
            rb_bentcurvedpanel.setChecked(true);
            rb_loosescreengapinscreenandbody.setChecked(false);
            rb_phonenotbent.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (bodybent.equalsIgnoreCase("Loose screen (Gap in screen and body)")) {
            rb_bentcurvedpanel.setChecked(false);
            rb_loosescreengapinscreenandbody.setChecked(true);
            rb_phonenotbent.setChecked(false);
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
        }
        if (bodybent.equalsIgnoreCase("Phone not bent") || bodybent.equalsIgnoreCase("") || bodybent.isEmpty()) {
            rb_bentcurvedpanel.setChecked(false);
            rb_loosescreengapinscreenandbody.setChecked(false);
            rb_phonenotbent.setChecked(true);
            warrenty = "Mobile Under Warranty";
        }
        if (front_camera.equalsIgnoreCase("Front Camera not working")) {
            frontcamera = "Front Camera not working";
            tv_frontcamera.setText("Front Camera Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            cb_froncamera.setChecked(true);
            frontcamera = "";
            tv_frontcamera.setText("Front Camera Working");
            warrenty = "Mobile Under Warranty";
        }
        if (back_camera.equalsIgnoreCase("Back Camera not working")) {
            backcamera = "Back Camera not working";
            tv_backcamera.setText("Back Camera Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            backcamera = "";
            cb_backcamera.setChecked(true);
            tv_backcamera.setText("Back Camera Working");
            warrenty = "Mobile Under Warranty";
        }
        if (wifi_.equalsIgnoreCase("WiFi not working")) {
            wifi = "WiFi not working";
            tv_wifi.setText("WiFi Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            cb_wifi.setChecked(true);
            wifi = "";
            tv_wifi.setText("WiFi Working");
            warrenty = "Mobile Under Warranty";
        }
        if (volume_button.equalsIgnoreCase("Volume Button not working")) {
            volumebutton = "Volume Button not working";
            tv_volumebutton.setText("Volume Button Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            volumebutton = "";
            cb_volumebutton.setChecked(true);
            tv_volumebutton.setText("Volume Button Working");
            warrenty = "Mobile Under Warranty";
        }
        if (battery_.equalsIgnoreCase("Battery not working")) {
            battery = "Battery not working";
            tv_batteryworking.setText("Battery Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            battery = "";
            cb_battery.setChecked(true);
            tv_batteryworking.setText("Battery Working");
            warrenty = "Mobile Under Warranty";
        }
        if (speaker_.equalsIgnoreCase("Speaker not working")) {
            speaker = "Speaker not working";
            tv_speakingworking.setText("Speaker Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            speaker = "";
            cb_speaker.setChecked(true);
            tv_speakingworking.setText("Speaker Working");
            warrenty = "Mobile Under Warranty";
        }
        if (finger_touch.equalsIgnoreCase("Finger Touch not working")) {
            fingertouch = "Finger Touch not working";
            tv_fingersensorgworking.setText("Finger Touch Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            fingertouch = "";
            cb_fingertouch.setChecked(true);
            tv_fingersensorgworking.setText("Finger Touch Working");
            warrenty = "Mobile Under Warranty";
        }
        if (bluetooth_.equalsIgnoreCase("Bluetooth not working")) {
            bluetooth = "Bluetooth not working";
            tv_bluetoothworking.setText("Bluetooth Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            bluetooth = "";
            cb_bluetooth.setChecked(true);
            tv_bluetoothworking.setText("Bluetooth Working");
            warrenty = "Mobile Under Warranty";
        }
        if (charging_port_.equalsIgnoreCase("Charging Port not working")) {
            charging_port = "Charging Port not working";
            tv_charninggworking.setText("Charging Port Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            charging_port = "";
            cb_charging_port.setChecked(true);
            tv_charninggworking.setText("Charging Port Working");
            warrenty = "Mobile Under Warranty";
        }
        if (powerbutton_.equalsIgnoreCase("Power Button not working")) {
            powerbutton = "Power Button not working";
            tv_powerbutton.setText("Power Button Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            powerbutton = "";
            cb_powerbutton.setChecked(true);
            tv_powerbutton.setText("Power Button Working");
            warrenty = "Mobile Under Warranty";
        }
        if (facesensor_.equalsIgnoreCase("Face Sensor not working")) {
            facesensor = "Face Sensor not working";
            tv_facesensor.setText("Face Sensor Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            facesensor = "";
            cb_facesensor.setChecked(true);
            tv_facesensor.setText("Face Sensor Working");
            warrenty = "Mobile Under Warranty";
        }
        if (audioic_.equalsIgnoreCase("Audio Receiver not working")) {
            audioic = "Audio Receiver not working";
            tv_audioic.setText("Audio Receiver Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            audioic = "";
            cb_audioic.setChecked(true);
            tv_audioic.setText("Audio Receiver Working");
            warrenty = "Mobile Under Warranty";
        }
        if (cameraglass_.equalsIgnoreCase("Camera Glass Broken")) {
            cameraglass = "Camera Glass Broken";
            tv_cameraglass.setText("Camera Glass Not Broken");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            cd_cameraglass.setChecked(true);
            cameraglass = "";
            tv_cameraglass.setText("Camera Glass Not Broken");
            warrenty = "Mobile Under Warranty";
        }
        if (silentbutton_.equalsIgnoreCase("Silent Button not working")) {
            silent_button = "Silent Button not working";
            tv_silentbutton.setText("Silent Button Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            silent_button = "";
            cb_silent_button.setChecked(true);
            tv_silentbutton.setText("Silent Button Working");
            warrenty = "Mobile Under Warranty";
        }
        if (microphone_.equalsIgnoreCase("Microphone is not working")) {
            microphone = "Microphone is not working";
            tv_microphone.setText("Microphone Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            microphone = "";
            cb_microphone.setChecked(true);
            tv_microphone.setText("Microphone Working");
            warrenty = "Mobile Under Warranty";
        }
        if (vibrator_.equalsIgnoreCase("Vibrator is not working")) {
            vibrator = "Vibrator is not working";
            tv_vibrater.setText("Vibrator Working");
            warrenty = "Mobile Out of Warranty";
            mobileage = "Above 11 Months";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            vibrator = "";
            cb_vibrator.setChecked(true);
            tv_vibrater.setText("Vibrator Working");
            warrenty = "Mobile Under Warranty";
        }

        // till now
        if (earphone_.equalsIgnoreCase("Not Available")) {
            earphone = "";
            tv_earphone.setText("Original Earphones of Device");
            warrenty = "Mobile Under Warranty";
        } else {
            earphone = "Original Earphones of Device";
            cb_earphone.setChecked(true);
            tv_earphone.setText("Original Earphones of Device");
            warrenty = "Mobile Under Warranty";
        }
        if (validbill_.equalsIgnoreCase("Not Available")) {
            validbill = "";
            tv_validbill.setText("Bill with same IMEI");
            mobileage = "Above 11 Months";
            warrenty = "Mobile Out of Warranty";
            rb_underthree.setChecked(false);
            rb_underthreesix.setChecked(false);
            rb_undersixeleven.setChecked(false);
            rb_underaboveleven.setChecked(true);
        } else {
            validbill = "Bill with same IMEI";
            cb_validbill.setChecked(true);
            tv_validbill.setText("Bill with same IMEI");
            warrenty = "Mobile Under Warranty";
        }
        if (charger_.equalsIgnoreCase("Not Available")) {
            charger = "";
            tv_charge.setText("Original Charger of Device");
            warrenty = "Mobile Under Warranty";
        } else {
            charger = "Original Charger of Device";
            cb_charger.setChecked(true);
            tv_charge.setText("Original Charger of Device");
            warrenty = "Mobile Under Warranty";
        }
        if (box_.equalsIgnoreCase("Not Available")) {
            box = "";
            tv_box.setText("Box with same IMEI");
            warrenty = "Mobile Under Warranty";
        } else {
            box = "Box with same IMEI";
            cb_box.setChecked(true);
            tv_box.setText("Box with same IMEI");
            warrenty = "Mobile Under Warranty";
        }

        relativeLayout = findViewById(R.id.rlback_requote);
        relativeLayout.setOnClickListener(this);

        radioGroupcopydisplay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                             @Override
                                                             public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                 int selectedId = radioGroupcopydisplay.getCheckedRadioButtonId();
                                                                 radiobuttoncopydisplay = (RadioButton) findViewById(selectedId);
                                                                 String copydisplayy = radiobuttoncopydisplay.getText().toString();
                                                                 System.out.println("getting_actionupcopydisplay " + copydisplayy);
                                                                 if (copydisplayy.equalsIgnoreCase("Yes")) {
                                                                     copydisplay = "Copy Display";
                                                                     System.out.println("getting_copydisplayyes " + callrecieve);
                                                                     for (String option : containreqoutedata) {
                                                                         if (option.equalsIgnoreCase("Copy Display")) {
                                                                             mobileage = "Above 11 Months";
                                                                             rb_underthree.setChecked(false);
                                                                             rb_underthreesix.setChecked(false);
                                                                             rb_undersixeleven.setChecked(false);
                                                                             rb_underaboveleven.setChecked(true);
                                                                             warrenty = "Mobile Out of Warranty";
                                                                             break;
                                                                         }
                                                                     }
                                                                 } else {
                                                                     copydisplay = "";
                                                                     warrenty = "Mobile Under Warranty";
                                                                 }
                                                             }
                                                         }
        );

        radioGroupmakeacall.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                           @Override
                                                           public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                               int selectedId = radioGroupmakeacall.getCheckedRadioButtonId();
                                                               radiobuttonup = (RadioButton) findViewById(selectedId);
                                                               String callrecievee = radiobuttonup.getText().toString();
                                                               System.out.println("getting_actionup " + callrecievee);
                                                               if (callrecievee.equalsIgnoreCase("Yes")) {
                                                                   callrecieve = "Able To Take Calls";
                                                                   System.out.println("getting_callrecieveyes " + callrecieve);
                                                                   warrenty = "Mobile Under Warranty";
                                                               } else {
                                                                   callrecieve = "Not Able To Take Calls";
                                                                   System.out.println("getting_callrecieveno " + callrecieve);
                                                                   for (String option : containreqoutedata) {
                                                                       if (option.equalsIgnoreCase("Not Able To Take Calls")) {
                                                                           mobileage = "Above 11 Months";
                                                                           rb_underthree.setChecked(false);
                                                                           rb_underthreesix.setChecked(false);
                                                                           rb_undersixeleven.setChecked(false);
                                                                           rb_underaboveleven.setChecked(true);
                                                                           warrenty = "Mobile Out of Warranty";
                                                                           break;
                                                                       }
                                                                   }
                                                               }
                                                           }
                                                       }
        );
        radioGrouptouchscreenworking.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                                    @Override
                                                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                        int selectedId = radioGrouptouchscreenworking.getCheckedRadioButtonId();
                                                                        radiobuttoncd = (RadioButton) findViewById(selectedId);
                                                                        String touchscreenn = radiobuttoncd.getText().toString();
                                                                        System.out.println("getting_actioncd " + touchscreenn);
                                                                        if (touchscreenn.equalsIgnoreCase("Yes")) {
                                                                            touchscreen = "Touch Working";
                                                                            System.out.println("getting_touchscreenyes " + touchscreen);
                                                                            warrenty = "Mobile Under Warranty";
                                                                        } else {
                                                                            touchscreen = "Touch Faulty";
                                                                            System.out.println("getting_touchscreenno " + touchscreen);
                                                                            for (String option : containreqoutedata) {
                                                                                if (option.equalsIgnoreCase("Touch Faulty")) {
                                                                                    mobileage = "Above 11 Months";
                                                                                    rb_underthree.setChecked(false);
                                                                                    rb_underthreesix.setChecked(false);
                                                                                    rb_undersixeleven.setChecked(false);
                                                                                    rb_underaboveleven.setChecked(true);
                                                                                    warrenty = "Mobile Out of Warranty";
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
        );

        radioGroupdmobileage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                            @Override
                                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                int selectedId = radioGroupdmobileage.getCheckedRadioButtonId();
                                                                radiobuttonmobileage = (RadioButton) findViewById(selectedId);
                                                                mobileage = radiobuttonmobileage.getText().toString();
                                                                if (screenspots.equalsIgnoreCase("Minor discoloration or small spots on screen") || (screenspots.equalsIgnoreCase("Large/ heavy visible spots on screen") || screenspots.equalsIgnoreCase("Multiple visible spots on screen")) || validbill.equals("") || callrecieve.equalsIgnoreCase("Not Able To Take Calls") || copydisplay.equalsIgnoreCase("copy display") || bodybent.equalsIgnoreCase("Bent/ curved panel") || bodybent.equalsIgnoreCase("Loose screen (Gap in screen and body)") || touchscreen.equalsIgnoreCase("Touch Faulty") || screenlines.equalsIgnoreCase("Display faded along corners") || screenlines.equalsIgnoreCase("Multiple lines on Display") || screenphysicalcondition.equalsIgnoreCase("Screen cracked/ glass broken") || screenphysicalcondition.equalsIgnoreCase("Damaged/ Torn screen on edges") || screenphysicalcondition.equalsIgnoreCase("Heavy scratches on screen") || screenphysicalcondition.equalsIgnoreCase("1-2 scratches on screen") || bodysidebackpanel.equalsIgnoreCase("Cracked/ broken side or back panel") || bodysidebackpanel.equalsIgnoreCase("Missing side or back panel") || frontcamera.equals("Front Camera not working") || backcamera.equals("Back Camera not working") || wifi.equals("WiFi not working") || volumebutton.equals("Volume Button not working") || battery.equals("Battery not working") || speaker.equals("Speaker not working") || fingertouch.equals("Finger Touch not working") || bluetooth.equals("Bluetooth not working") || charging_port.equals("Charging Port not working") || powerbutton.equals("Power Button not working") || facesensor.equals("Face Sensor not working") || audioic.equals("Audio Receiver not working") || cameraglass.equals("Camera Glass Broken") || silent_button.equals("Silent Button not working") || microphone.equals("Microphone is not working") || vibrator.equals("Vibrator is not working")) {
                                                                    warrenty = "Mobile Out of Warranty";
                                                                    mobileage = "Above 11 Months";
                                                                } else {
                                                                    mobileage = radiobuttonmobileage.getText().toString();
                                                                    System.out.println("getting_mobilegarehere " + mobileage);
                                                                    warrenty = "Mobile Under Warranty";
                                                                }
                                                            }
                                                        }
        );
        radioGroupdeadpixel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                           @Override
                                                           public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                               int selectedId = radioGroupdeadpixel.getCheckedRadioButtonId();
                                                               radiobuttondeadpixel = (RadioButton) findViewById(selectedId);
                                                               screenspots = radiobuttondeadpixel.getText().toString();
                                                               if (screenspots.equalsIgnoreCase("Large/ heavy visible spots on screen") || screenspots.equalsIgnoreCase("Multiple visible spots on screen") || screenspots.equalsIgnoreCase("Minor discoloration or small spots on screen")) {
                                                                   mobileage = "Above 11 Months";
                                                                   rb_underthree.setChecked(false);
                                                                   rb_underthreesix.setChecked(false);
                                                                   rb_undersixeleven.setChecked(false);
                                                                   rb_underaboveleven.setChecked(true);
                                                                   warrenty = "Mobile Out of Warranty";
                                                               } else {
                                                                   warrenty = "Mobile Under Warranty";
                                                               }
                                                           }
                                                       }
        );
        radioGroupdvisiblelines.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                               @Override
                                                               public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                   int selectedId = radioGroupdvisiblelines.getCheckedRadioButtonId();
                                                                   radiobuttonvisiblelines = (RadioButton) findViewById(selectedId);
                                                                   screenlines = radiobuttonvisiblelines.getText().toString();
                                                                   System.out.println("getting_screenlines " + screenlines);
                                                                   if (screenlines.equalsIgnoreCase("Display faded along corners") || screenlines.equalsIgnoreCase("Multiple lines on Display")) {
                                                                       mobileage = "Above 11 Months";
                                                                       warrenty = "Mobile Out of Warranty";
                                                                       rb_underthree.setChecked(false);
                                                                       rb_underthreesix.setChecked(false);
                                                                       rb_undersixeleven.setChecked(false);
                                                                       rb_underaboveleven.setChecked(true);
                                                                   } else {
                                                                       warrenty = "Mobile Under Warranty";
                                                                   }
                                                               }
                                                           }
        );
        radioGroupdscreenphysical.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                                 @Override
                                                                 public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                     int selectedId = radioGroupdscreenphysical.getCheckedRadioButtonId();
                                                                     radiobuttonscreenphysical = (RadioButton) findViewById(selectedId);
                                                                     screenphysicalcondition = radiobuttonscreenphysical.getText().toString();
                                                                     if (screenphysicalcondition.equalsIgnoreCase("Screen cracked/ glass broken") || screenphysicalcondition.equalsIgnoreCase("Damaged/ Torn screen on edges") || screenphysicalcondition.equalsIgnoreCase("Heavy scratches on screen") || screenphysicalcondition.equalsIgnoreCase("1-2 scratches on screen")) {
                                                                         mobileage = "Above 11 Months";
                                                                         rb_underthree.setChecked(false);
                                                                         rb_underthreesix.setChecked(false);
                                                                         rb_undersixeleven.setChecked(false);
                                                                         rb_underaboveleven.setChecked(true);
                                                                         warrenty = "Mobile Out of Warranty";
                                                                     } else {
                                                                         warrenty = "Mobile Under Warranty";
                                                                     }
                                                                 }
                                                             }
        );
        radioGroupdscratchedon.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                              @Override
                                                              public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                  int selectedId = radioGroupdscratchedon.getCheckedRadioButtonId();
                                                                  radiobuttonscratchedon = (RadioButton) findViewById(selectedId);
                                                                  bodyscratches = radiobuttonscratchedon.getText().toString();
                                                                  warrenty = "Mobile Under Warranty";
                                                              }
                                                          }
        );
        radioGroupddentonphone.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                              @Override
                                                              public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                  int selectedId = radioGroupddentonphone.getCheckedRadioButtonId();
                                                                  radiobuttonddentonphone = (RadioButton) findViewById(selectedId);
                                                                  bodydents = radiobuttonddentonphone.getText().toString();
                                                                  warrenty = "Mobile Under Warranty";
                                                              }
                                                          }
        );

        radioGroupdphonesideback.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                                @Override
                                                                public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                    int selectedId = radioGroupdphonesideback.getCheckedRadioButtonId();
                                                                    radiobuttonphonesideback = (RadioButton) findViewById(selectedId);
                                                                    bodysidebackpanel = radiobuttonphonesideback.getText().toString();
                                                                    if (bodysidebackpanel.equalsIgnoreCase("Cracked/ broken side or back panel") || bodysidebackpanel.equalsIgnoreCase("Missing side or back panel")) {
                                                                        mobileage = "Above 11 Months";
                                                                        rb_underthree.setChecked(false);
                                                                        rb_underthreesix.setChecked(false);
                                                                        rb_undersixeleven.setChecked(false);
                                                                        rb_underaboveleven.setChecked(true);
                                                                        warrenty = "Mobile Out of Warranty";
                                                                    } else {
                                                                        warrenty = "Mobile Under Warranty";
                                                                    }
                                                                }
                                                            }
        );

        radioGroupdbentphone.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                            @Override
                                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                int selectedId = radioGroupdbentphone.getCheckedRadioButtonId();
                                                                radiobuttondbentphone = (RadioButton) findViewById(selectedId);
                                                                bodybent = radiobuttondbentphone.getText().toString();
                                                                if (bodybent.equalsIgnoreCase("Bent/ curved panel") || bodybent.equalsIgnoreCase("Loose screen (Gap in screen and body)")) {
                                                                    mobileage = "Above 11 Months";
                                                                    rb_underthree.setChecked(false);
                                                                    rb_underthreesix.setChecked(false);
                                                                    rb_undersixeleven.setChecked(false);
                                                                    rb_underaboveleven.setChecked(true);
                                                                    warrenty = "Mobile Out of Warranty";
                                                                } else {
                                                                    warrenty = "Mobile Under Warranty";
                                                                }
                                                            }
                                                        }
        );

        cb_froncamera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_frontcameramain.setOnClickListener(new View.OnClickListener() {//1 cb_froncamera
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_froncamera.isChecked()) {
                    cb_froncamera.setChecked(false);
                    warrenty = "Mobile Out of Warranty";
                    mobileage = "Above 11 Months";
                    frontcamera = "Front Camera not working";
                    tv_frontcamera.setText("Front Camera Working");
                    rb_underthree.setChecked(false);
                    rb_underthreesix.setChecked(false);
                    rb_undersixeleven.setChecked(false);
                    rb_underaboveleven.setChecked(true);
                } else if (!(cb_froncamera.isChecked())) {
                    cb_froncamera.setChecked(true);
                    frontcamera = "";
                    tv_frontcamera.setText("Front Camera Working");
                    warrenty = "Mobile Under Warranty";
                }
            }
        });

        cb_backcamera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_backcameramain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_backcamera.isChecked()) {
                    cb_backcamera.setChecked(false);
                    warrenty = "Mobile Out of Warranty";
                    mobileage = "Above 11 Months";
                    backcamera = "Back Camera not working";
                    tv_backcamera.setText("Back Camera Working");
                    rb_underthree.setChecked(false);
                    rb_underthreesix.setChecked(false);
                    rb_undersixeleven.setChecked(false);
                    rb_underaboveleven.setChecked(true);


                } else if (!(cb_backcamera.isChecked())) {
                    cb_backcamera.setChecked(true);
                    backcamera = "";
                    warrenty = "Mobile Under Warranty";
                    tv_backcamera.setText("Back Camera Working");
                }
            }
        });

        cb_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_wifimain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_wifi.isChecked()) {
                    cb_wifi.setChecked(false);
                    wifi = "WiFi not working";
                    mobileage = "Above 11 Months";
                    warrenty = "Mobile Out of Warranty";
                    tv_wifi.setText("WiFi Working");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("WiFi not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }
                } else if (!(cb_wifi.isChecked())) {
                    cb_wifi.setChecked(true);
                    wifi = "";
                    warrenty = "Mobile Under Warranty";
                    tv_wifi.setText("WiFi Working");
                }
            }
        });

        cb_volumebutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_volumebuttonmain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_volumebutton.isChecked()) {
                    cb_volumebutton.setChecked(false);
                    warrenty = "Mobile Out of Warranty";
                    volumebutton = "Volume Button not working";
                    tv_volumebutton.setText("Volume Button Working");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Volume Button not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }
                } else if (!(cb_volumebutton.isChecked())) {
                    cb_volumebutton.setChecked(true);
                    volumebutton = "";
                    warrenty = "Mobile Under Warranty";
                    tv_volumebutton.setText("Volume Button Working");
                }
            }
        });

        cb_battery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_batterymain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_battery.isChecked()) {
                    cb_battery.setChecked(false);
                    battery = "Battery not working";
                    warrenty = "Mobile Out of Warranty";
                    tv_batteryworking.setText("Battery Working");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Battery not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }
                } else if (!(cb_battery.isChecked())) {
                    cb_battery.setChecked(true);
                    battery = "";
                    warrenty = "Mobile Under Warranty";
                    tv_batteryworking.setText("Battery Working");
                }
            }
        });

        cb_speaker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_speakermain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_speaker.isChecked()) {
                    cb_speaker.setChecked(false);
                    speaker = "Speaker not working";
                    warrenty = "Mobile Out of Warranty";
                    tv_speakingworking.setText("Speaker Working");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Speaker not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }

                } else if (!(cb_speaker.isChecked())) {
                    cb_speaker.setChecked(true);
                    speaker = "";
                    warrenty = "Mobile Under Warranty";
                    tv_speakingworking.setText("Speaker Working");
                }
            }
        });

        cb_fingertouch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });

        lnr_fingertouchmain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_fingertouch.isChecked()) {
                    cb_fingertouch.setChecked(false);
                    warrenty = "Mobile Out of Warranty";
                    fingertouch = "Finger Touch not working";
                    tv_fingersensorgworking.setText("Finger Touch Working");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Finger Touch not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }
                } else if (!(cb_fingertouch.isChecked())) {
                    cb_fingertouch.setChecked(true);
                    fingertouch = "";
                    warrenty = "Mobile Under Warranty";
                    tv_fingersensorgworking.setText("Finger Touch Working");
                }
            }
        });//cb_bluetooth

        cb_bluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_bluetoothmain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_bluetooth.isChecked()) {
                    cb_bluetooth.setChecked(false);
                    bluetooth = "Bluetooth not working";
                    warrenty = "Mobile Out of Warranty";
                    tv_bluetoothworking.setText("Bluetooth Working");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Bluetooth not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }
                } else if (!(cb_bluetooth.isChecked())) {
                    cb_bluetooth.setChecked(true);
                    bluetooth = "";
                    warrenty = "Mobile Under Warranty";
                    tv_bluetoothworking.setText("Bluetooth Working");
                }
            }
        });

        cb_charging_port.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_charging_portmain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_charging_port.isChecked()) {
                    cb_charging_port.setChecked(false);
                    warrenty = "Mobile Out of Warranty";
                    charging_port = "Charging Port not working";
                    tv_charninggworking.setText("Charging Port Working");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Charging Port not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }

                } else if (!(cb_charging_port.isChecked())) {
                    cb_charging_port.setChecked(true);
                    charging_port = "";
                    warrenty = "Mobile Under Warranty";
                    tv_charninggworking.setText("Charging Port Working");
                }
            }
        });
        cb_powerbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_powerbuttonmain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_powerbutton.isChecked()) {
                    cb_powerbutton.setChecked(false);
                    warrenty = "Mobile Out of Warranty";
                    powerbutton = "Power Button not working";
                    tv_powerbutton.setText("Power Button Working");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Power Button not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }
                } else if (!(cb_powerbutton.isChecked())) {
                    cb_powerbutton.setChecked(true);
                    powerbutton = "";
                    warrenty = "Mobile Under Warranty";
                    tv_powerbutton.setText("Power Button Working");
                }
            }
        });

        cb_facesensor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_facesensormain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_facesensor.isChecked()) {
                    cb_facesensor.setChecked(false);
                    facesensor = "Face Sensor not working";
                    warrenty = "Mobile Out of Warranty";
                    tv_facesensor.setText("Face Sensor Working");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Face Sensor not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }
                } else if (!(cb_facesensor.isChecked())) {
                    cb_facesensor.setChecked(true);
                    facesensor = "";
                    warrenty = "Mobile Under Warranty";
                    tv_facesensor.setText("Face Sensor Working");
                }
            }
        });

        cb_audioic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_audioicmain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_audioic.isChecked()) {
                    cb_audioic.setChecked(false);
                    audioic = "Audio Receiver not working";
                    tv_audioic.setText("Audio Receiver Working");
                    warrenty = "Mobile Out of Warranty";
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Audio Receiver not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }
                } else if (!(cb_audioic.isChecked())) {
                    cb_audioic.setChecked(true);
                    audioic = "";
                    warrenty = "Mobile Under Warranty";
                    tv_audioic.setText("Audio Receiver Working");
                }
            }
        });

        cd_cameraglass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_cameraglassmain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cd_cameraglass.isChecked()) {
                    cd_cameraglass.setChecked(false);
                    cameraglass = "Camera Glass Broken";
                    tv_cameraglass.setText("Camera Glass Not Broken");
                    warrenty = "Mobile Out of Warranty";
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Camera Glass Broken")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }

                } else if (!(cd_cameraglass.isChecked())) {

                    cd_cameraglass.setChecked(true);
                    cameraglass = "";
                    warrenty = "Mobile Under Warranty";
                    tv_cameraglass.setText("Camera Glass Not Broken");
                }
            }
        });

        cb_silent_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_silent_buttonmain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_silent_button.isChecked()) {
                    cb_silent_button.setChecked(false);
                    silent_button = "Silent Button not working";
                    warrenty = "Mobile Out of Warranty";
                    tv_silentbutton.setText("Silent Button Working");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Silent Button not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }


                } else if (!(cb_silent_button.isChecked())) {
                    cb_silent_button.setChecked(true);
                    silent_button = "";
                    warrenty = "Mobile Under Warranty";
                    tv_silentbutton.setText("Silent Button Working");
                }
            }
        });

        cb_microphone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_microphonemain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_microphone.isChecked()) {
                    cb_microphone.setChecked(false);
                    microphone = "Microphone is not working";
                    tv_microphone.setText("Microphone Working");
                    warrenty = "Mobile Out of Warranty";
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Microphone is not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }
                } else if (!(cb_microphone.isChecked())) {
                    cb_microphone.setChecked(true);
                    microphone = "";
                    warrenty = "Mobile Under Warranty";
                    tv_microphone.setText("Microphone Working");
                }
            }
        });
        cb_vibrator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });
        lnr_vibratormain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_vibrator.isChecked()) {
                    cb_vibrator.setChecked(false);
                    vibrator = "Vibrator is not working";
                    warrenty = "Mobile Out of Warranty";
                    tv_vibrater.setText("Vibrator Working");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Vibrator is not working")) {
                            mobileage = "Above 11 Months";
                            rb_underthree.setChecked(false);
                            rb_underthreesix.setChecked(false);
                            rb_undersixeleven.setChecked(false);
                            rb_underaboveleven.setChecked(true);
                            break;
                        }
                    }
                } else if (!(cb_vibrator.isChecked())) {
                    cb_vibrator.setChecked(true);
                    vibrator = "";
                    warrenty = "Mobile Under Warranty";
                    tv_vibrater.setText("Vibrator Working");
                }
            }
        });

        cb_earphone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });

        lnr_earphonemain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_earphone.isChecked()) {
                    cb_earphone.setChecked(false);
                    earphone = "";
                    warrenty = "Mobile Under Warranty";
                    tv_earphone.setText("Original Earphones of Device");
                } else if (!(cb_earphone.isChecked())) {
                    cb_earphone.setChecked(true);
                    warrenty = "Mobile Under Warranty";
                    earphone = "Original Earphones of Device";
                    tv_earphone.setText("Original Earphones of Device");
                }
            }
        });

        cb_validbill.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });

        lnr_validbillmain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_validbill.isChecked()) {
                    cb_validbill.setChecked(false);
                    validbill = "";
                    warrenty = "Mobile Out of Warranty";
                    tv_validbill.setText("Bill with same IMEI");
                    mobileage = "Above 11 Months";
                    rb_underthree.setChecked(false);
                    rb_underthreesix.setChecked(false);
                    rb_undersixeleven.setChecked(false);
                    rb_underaboveleven.setChecked(true);
                } else if (!(cb_validbill.isChecked())) {
                    cb_validbill.setChecked(true);
                    validbill = "Bill with same IMEI";
                    warrenty = "Mobile Under Warranty";
                    tv_validbill.setText("Bill with same IMEI");
                    for (String option : containreqoutedata) {
                        if (option.equalsIgnoreCase("Bill with same IMEI")) {
                            break;
                        }
                    }
                }
            }
        });
        cb_charger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });

        lnr_chargermain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_charger.isChecked()) {
                    cb_charger.setChecked(false);
                    charger = "";
                    warrenty = "Mobile Under Warranty";
                    tv_charge.setText("Original Charger of Device");
                } else if (!(cb_charger.isChecked())) {
                    cb_charger.setChecked(true);
                    charger = "Original Charger of Device";
                    tv_charge.setText("Original Charger of Device");
                    warrenty = "Mobile Under Warranty";
                }
            }
        });

        cb_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // Toast.makeText(getApplicationContext(), "Cb_FrontCamera", Toast.LENGTH_SHORT).show();
                // You don't need to use onCheckChangeListener.
            }
        });

        lnr_boxmain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (cb_box.isChecked()) {
                    cb_box.setChecked(false);
                    box = "";
                    warrenty = "Mobile Under Warranty";
                    tv_box.setText("Box with same IMEI");
                } else if (!(cb_box.isChecked())) {
                    cb_box.setChecked(true);
                    box = "Box with same IMEI";
                    warrenty = "Mobile Under Warranty";
                    tv_box.setText("Box with same IMEI");
                }
            }
        });
    }

    private void progressDialog() {
        kProgressHUD = new KProgressHUD(RequoteActivity.this);
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

    @Override
    public void onClick(View v) {//btn_completeddcrtyt
        switch (v.getId()) {
            case R.id.rlback_requote:
                Intent intent = new Intent(getApplicationContext(), InprogressActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_completeddcrtyt:
                progressDialog();
                submitdata();
                break;
            default:
                break;
        }
    }

    private void submitdata() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/mobilereqoute.php",
                //StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://postman-echo.com/post",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response == null) {
                            dismissDialog();
                        } else {
                            String s = response.trim();
                            System.out.println("msg_fonemain:::" + s);
                            dismissDialog();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                if (status.equalsIgnoreCase("1")) {
                                    Intent intent = new Intent(getApplicationContext(), InprogressActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                } else {
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
                        dismissDialog();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("frontcamera", frontcamera.trim());
                params.put("backcamera", backcamera.trim());
                params.put("wifi", wifi.trim());
                params.put("volumebutton", volumebutton.trim());
                params.put("battery", battery.trim());
                params.put("speaker", speaker.trim());
                params.put("fingertouch", fingertouch.trim());
                params.put("bluetooth", bluetooth.trim());
                params.put("chargingport", charging_port.trim());
                params.put("powerbutton", powerbutton.trim());
                params.put("facesensor", facesensor.trim());
                params.put("audioreciever", audioic.trim());
                params.put("cameraglass", cameraglass.trim());
                params.put("silentbutton", silent_button.trim());
                params.put("microphone", microphone.trim());
                params.put("vibrate", vibrator.trim());
                params.put("copydisplay", copydisplay.trim());
                params.put("earphone", earphone.trim());
                params.put("billimei", validbill.trim());
                params.put("charger", charger.trim());
                params.put("boximei", box.trim());
                params.put("mobileage", mobileage.trim());
                params.put("callrecieve", callrecieve.trim());
                params.put("touchscreen", touchscreen.trim());
                params.put("screenspot", screenspots.trim());
                params.put("screenlines", screenlines.trim());
                params.put("screenphysicalcondition", screenphysicalcondition.trim());
                params.put("bodyscratches", bodyscratches.trim());
                params.put("bodydents", bodydents.trim());
                params.put("bodysidebackpanel", bodysidebackpanel.trim());
                params.put("bodybent", bodybent.trim());
                params.put("vendorid", vendorid);
                params.put("lead_id", lead_id);
                params.put("cat_id", cat_id);
                params.put("deviceid", deviceid);
                params.put("varientid", varientid);
                params.put("brandid", brandid);
                params.put("ajentid", agentid);
                params.put("warrin", warrenty);
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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), InprogressActivity.class);
        startActivity(intent);
        finish();
    }

}