package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class CompleteleadItemdescriptionActivity extends AppCompatActivity implements View.OnClickListener {
    public RelativeLayout back_des;
    public SessionManager session;
    public String vendorid, agentid, role, lead_id, ajentassign = "", model_name, imageurl, price, location, lead_pick_time, bank_name, accountnumber, ifsccode, beneficiary_name, upiid;
    private KProgressHUD kProgressHUD;
    public LinearLayout linearLayout;
    public LinearLayout iv_captureone, iv_capturetwo, iv_capturethree, iv_capturefour, iv_capturefiveadharcard, iv_capturefiveadharcardback;
    public ImageView civ_mobileone, civ_mobiletwo, civ_mobilethree, civ_mobilefour, civ_mobilefiveadharcard, civ_mobilefiveadharcardback;
    public File file1, file2, file3, file4;
    public Uri outPutfileUri1, outPutfileUri2, outPutfileUri3, outPutfileUri4;
    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE1 = 100;
    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE2 = 101;
    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE3 = 102;
    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE4 = 103;

    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE4ADHARCARD = 104;
    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE4ADHARCARDBACK = 105;

    public Bitmap bitmap1, bitmap2, bitmap3, bitmap4, bitmap4adharcard, bitmap4adharcardback;
    private Bitmap bitmapmobilephoto1, bitmapmobilephoto2, bitmapmobilephoto3, bitmapmobilephoto4, bitmapmobilephoto4back;
    public String mobilepicturePath1, mobilepicturePath2, mobilepicturePath3, mobilepicturePath4, mobilepicturePath4adharcrad, mobilepicturePath4adharcradback;
    public ImageView image_descld;
    public TextView tv_productname_descld, tv_pickup_categorycld, tv_pickup_conditioncld, tv_phone_price_descld, tv_pickup_locationcld, tv_pickup_timedateyearcld, tv_beneficiaryname_descld, tv_accountnumber_descld, tv_ifsccode_descld, tv_bankname_descld, tv_upiid_cld, tv_payableamountcld;
    public Button btn_completedcld, btn_addextraamount;
    public EditText et_addextraamount_cld, et_enterimeinumber;
    public String jsonString;
    public String extraamount = "", imeinumber;
    public CardView cv_beneficiarydetail, cv_upiiddetail;
    public TextView tv_amountcldone, tv_extraamountcldone;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completelead_item_list_detail);
        session = new SessionManager(getApplicationContext());
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (shouldAskPermissions()) {
            askPermissions();
        }

        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            agentid = user.get(SessionManager.KEY_AGENTID);
            role = user.get(SessionManager.KEY_ROLE);
            System.out.println("User_Session_Data_Vendorid:::::" + vendorid + "  " + agentid + "  " + role);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Bundle intent = getIntent().getExtras();
        if (intent != null) {
            lead_id = intent.get("lead_id").toString();
            ajentassign = intent.get("ajentassign").toString();

            model_name = intent.get("model_name").toString();
            imageurl = intent.get("imageurl").toString();
            price = intent.get("price").toString();
            location = intent.get("location").toString();
            lead_pick_time = intent.get("lead_pick_time").toString();
            bank_name = intent.get("bank_name").toString();
            accountnumber = intent.get("accountnumber").toString();
            ifsccode = intent.get("ifsccode").toString();
            beneficiary_name = intent.get("beneficiary_name").toString();
            upiid = intent.get("upiid").toString();
            System.out.println("getting_lead_id " + lead_id + "::::::::" + ajentassign + "  ::::::::: " + lead_pick_time);
        }
        cv_beneficiarydetail = findViewById(R.id.cv_beneficiarydetail);
        cv_upiiddetail = findViewById(R.id.cv_upiiddetail);

        iv_captureone = findViewById(R.id.iv_captureone);
        iv_capturetwo = findViewById(R.id.iv_capturetwo);
        iv_capturethree = findViewById(R.id.iv_capturethree);
        iv_capturefour = findViewById(R.id.iv_capturefour);

        iv_capturefiveadharcard = findViewById(R.id.iv_captureadharcardimage);
        iv_capturefiveadharcardback = findViewById(R.id.iv_captureadharcardimageback);

        civ_mobileone = findViewById(R.id.civ_mobileone);
        civ_mobiletwo = findViewById(R.id.civ_mobiletwo);
        civ_mobilethree = findViewById(R.id.civ_mobilethree);
        civ_mobilefour = findViewById(R.id.civ_mobilefour);

        civ_mobilefiveadharcard = findViewById(R.id.civ_adharcardimage);
        civ_mobilefiveadharcardback = findViewById(R.id.civ_adharcardimageback);

        tv_productname_descld = findViewById(R.id.tv_productname_descld);
        tv_pickup_categorycld = findViewById(R.id.tv_pickup_categorycld);
        tv_pickup_conditioncld = findViewById(R.id.tv_pickup_conditioncld);
        tv_phone_price_descld = findViewById(R.id.tv_phone_price_descld);
        tv_pickup_locationcld = findViewById(R.id.tv_pickup_locationcld);
        tv_pickup_timedateyearcld = findViewById(R.id.tv_pickup_timedateyearcld);
        tv_beneficiaryname_descld = findViewById(R.id.tv_beneficiaryname_descld);
        tv_accountnumber_descld = findViewById(R.id.tv_accountnumber_descld);
        tv_ifsccode_descld = findViewById(R.id.tv_ifsccode_descld);

        tv_bankname_descld = findViewById(R.id.tv_bankname_descld);
        tv_upiid_cld = findViewById(R.id.tv_upiid_cld);
        tv_payableamountcld = findViewById(R.id.tv_payableamountcld);
        btn_addextraamount = findViewById(R.id.btn_addextraamount);
        btn_completedcld = findViewById(R.id.btn_completedcld);
        et_addextraamount_cld = findViewById(R.id.et_addextraamount_cld);
        et_enterimeinumber = findViewById(R.id.et_enterimeinumber);

        tv_amountcldone = findViewById(R.id.tv_amountcldone);
        tv_extraamountcldone = findViewById(R.id.tv_extraamountcldone);

        tv_amountcldone.setText(price);
        tv_productname_descld.setText("");

        tv_productname_descld.setText(model_name);
        tv_pickup_categorycld.setText("Mobile");
        tv_pickup_conditioncld.setText("NA");
        tv_phone_price_descld.setText("Rs. " + price);
        tv_pickup_locationcld.setText(location);
        tv_pickup_timedateyearcld.setText(lead_pick_time);
        tv_beneficiaryname_descld.setText(beneficiary_name);
        tv_accountnumber_descld.setText(accountnumber);
        tv_ifsccode_descld.setText(ifsccode);
        tv_bankname_descld.setText(bank_name);
        if (upiid == null || upiid.equals("null") || upiid.isEmpty()) {//000000000000000000
            tv_upiid_cld.setText("");
            cv_upiiddetail.setVisibility(View.GONE);
            cv_beneficiarydetail.setVisibility(View.VISIBLE);
        } else {
            tv_upiid_cld.setText(upiid);
            cv_upiiddetail.setVisibility(View.VISIBLE);
            cv_beneficiarydetail.setVisibility(View.GONE);
        }

        tv_payableamountcld.setText("Rs. " + price);

        back_des = findViewById(R.id.back_descld);
        back_des.setOnClickListener(this);
        btn_completedcld.setOnClickListener(this);

        btn_addextraamount.setOnClickListener(new View.OnClickListener() {//111111111111111111111111111111111111111
            @Override
            public void onClick(View v) {
                extraamount = et_addextraamount_cld.getText().toString();
                tv_extraamountcldone.setText(extraamount);
                int sum = Integer.parseInt(price) + Integer.parseInt(extraamount);
                System.out.println("getting_amountsumdata " + sum);
                tv_payableamountcld.setText(String.valueOf(sum));
            }
        });

        /* if (isNetworkAvailable()) {
            progressDialog();
            return;
        } else {
            dismissDialog();
            Toast.makeText(CompleteleadItemdescriptionActivity.this, getResources().getString(R.string.connecttointenet), Toast.LENGTH_LONG).show();
        }*/

        iv_captureone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE1);
                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file1 = new File(Environment.getExternalStorageDirectory(), "MyPhoto1.jpg");
                outPutfileUri1 = Uri.fromFile(file1);
                System.out.println("getting_captureprofle  " + outPutfileUri1);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri1);
                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE1);*/
            }
        });
        iv_capturetwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE2);
               /* Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file2 = new File(Environment.getExternalStorageDirectory(), "MyPhoto2.jpg");
                outPutfileUri2 = Uri.fromFile(file2);
                System.out.println("getting_captureprofle  " + outPutfileUri2);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri2);
                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE2);*/
            }
        });
        iv_capturethree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE3);
                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file3 = new File(Environment.getExternalStorageDirectory(), "MyPhoto3.jpg");
                outPutfileUri3 = Uri.fromFile(file3);
                System.out.println("getting_captureprofle  " + outPutfileUri3);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri3);
                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE3);*/
            }
        });
        iv_capturefour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE4);
                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file4 = new File(Environment.getExternalStorageDirectory(), "MyPhoto4.jpg");
                outPutfileUri4 = Uri.fromFile(file4);
                System.out.println("getting_captureprofle  " + outPutfileUri4);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri4);
                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE4);*/
            }
        });
        iv_capturefiveadharcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE4ADHARCARD);
                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file4 = new File(Environment.getExternalStorageDirectory(), "MyPhoto4.jpg");
                outPutfileUri4 = Uri.fromFile(file4);
                System.out.println("getting_captureprofle  " + outPutfileUri4);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri4);
                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE4);*/
            }
        });
        iv_capturefiveadharcardback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE4ADHARCARDBACK);
                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file4 = new File(Environment.getExternalStorageDirectory(), "MyPhoto4.jpg");
                outPutfileUri4 = Uri.fromFile(file4);
                System.out.println("getting_captureprofle  " + outPutfileUri4);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri4);
                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE4);*/
            }
        });
    }

    /*create end*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("getting_requestCode  " + requestCode);
        System.out.println("getting_resultCode  " + resultCode);

        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE1 && resultCode == -1) {
            try {
                //bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri1);
                bitmap1 = (Bitmap) data.getExtras().get("data");

                /*int compressionRatio = 4; //1 == originalImage, 2 = 50% compression, 4=25% compress
                File file = new File(String.valueOf(bitmap1));
                try {
                    bitmapmobilephoto1 = BitmapFactory.decodeFile(file.getPath());
                    bitmapmobilephoto1.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                    getResizedBitmap(bitmapmobilephoto1, 200);
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }*/
                //mobilepicturePath1 = getResizedBitmap(bitmapmobilephoto1, 100); // old code
                //mobilepicturePath1 = String.valueOf(bitmapmobilephoto1);
                mobilepicturePath1 = String.valueOf(bitmap1);
                System.out.println("getting_mobilepicturePath1 " + mobilepicturePath1);
               /* BitmapFactory.Options bounds = new BitmapFactory.Options();
                bounds.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file1.toString(), bounds);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(file1.toString(), opts);
                ExifInterface exif = new ExifInterface(file1.toString());
                String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                int rotationAngle = 0;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
                Matrix matrix = new Matrix();
                matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                bitmapmobilephoto1 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);*/
                ImageView imageView = findViewById(R.id.civ_mobileone);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE2 && resultCode == -1) {
            try {
                //bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri2);
                bitmap2 = (Bitmap) data.getExtras().get("data");
/*
                int compressionRatio = 4; //1 == originalImage, 2 = 50% compression, 4=25% compress
                File file = new File(String.valueOf(bitmap2));

                try {
                    bitmapmobilephoto2 = BitmapFactory.decodeFile(file.getPath());
                    bitmapmobilephoto2.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                    getResizedBitmap(bitmapmobilephoto2, 200);
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }*/
                //mobilepicturePath2 = getResizedBitmap(bitmapmobilephoto2, 100); // old code
                //mobilepicturePath2 = String.valueOf(bitmapmobilephoto2);
                mobilepicturePath2 = String.valueOf(bitmap2);
              /*  BitmapFactory.Options bounds = new BitmapFactory.Options();
                bounds.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file2.toString(), bounds);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(file2.toString(), opts);
                ExifInterface exif = new ExifInterface(file2.toString());
                String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                int rotationAngle = 0;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
                Matrix matrix = new Matrix();
                matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                bitmapmobilephoto2 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);*/
                ImageView imageView = findViewById(R.id.civ_mobiletwo);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE3 && resultCode == -1) {
            try {
                // bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri3);
                bitmap3 = (Bitmap) data.getExtras().get("data");
/*

                int compressionRatio = 4; //1 == originalImage, 2 = 50% compression, 4=25% compress
                File file = new File(String.valueOf(bitmap3));
                try {
                    bitmapmobilephoto3 = BitmapFactory.decodeFile(file.getPath());
                    bitmapmobilephoto3.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                    getResizedBitmap(bitmapmobilephoto3, 200);
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }
*/
                // bitmapprofilephoto = getResizedBitmap(bitmap1, 200); // old code
                //mobilepicturePath3 = getResizedBitmap(bitmapmobilephoto3, 100); // old code
                //mobilepicturePath3 = String.valueOf(bitmapmobilephoto3);
                mobilepicturePath3 = String.valueOf(bitmap3);
                /*BitmapFactory.Options bounds = new BitmapFactory.Options();
                bounds.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file3.toString(), bounds);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(file3.toString(), opts);
                ExifInterface exif = new ExifInterface(file3.toString());
                String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                int rotationAngle = 0;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
                Matrix matrix = new Matrix();
                matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                bitmapmobilephoto3 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);*/
                ImageView imageView = findViewById(R.id.civ_mobilethree);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE4 && resultCode == -1) {
            try {
                //bitmap4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri4);
                bitmap4 = (Bitmap) data.getExtras().get("data");

               /* int compressionRatio = 4; //1 == originalImage, 2 = 50% compression, 4=25% compress
                File file = new File(String.valueOf(bitmap2));
                try {
                    bitmapmobilephoto4 = BitmapFactory.decodeFile(file.getPath());
                    bitmapmobilephoto4.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                    getResizedBitmap(bitmapmobilephoto4, 200); // old code
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }*/
                // bitmapprofilephoto = getResizedBitmap(bitmap1, 200); // old code
                //mobilepicturePath4 = getResizedBitmap(bitmapmobilephoto4, 100); // old code
                mobilepicturePath4 = String.valueOf(bitmap4);
               /* BitmapFactory.Options bounds = new BitmapFactory.Options();
                bounds.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file4.toString(), bounds);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(file4.toString(), opts);
                ExifInterface exif = new ExifInterface(file4.toString());
                String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                int rotationAngle = 0;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
                Matrix matrix = new Matrix();
                matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                bitmapmobilephoto4 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);*/
                ImageView imageView = findViewById(R.id.civ_mobilefour);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap4);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE4ADHARCARD && resultCode == -1) {
            try {
                //bitmap4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri4);
                bitmap4adharcard = (Bitmap) data.getExtras().get("data");

               /* int compressionRatio = 4; //1 == originalImage, 2 = 50% compression, 4=25% compress
                File file = new File(String.valueOf(bitmap2));
                try {
                    bitmapmobilephoto4 = BitmapFactory.decodeFile(file.getPath());
                    bitmapmobilephoto4.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                    getResizedBitmap(bitmapmobilephoto4, 200); // old code
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }*/
                // bitmapprofilephoto = getResizedBitmap(bitmap1, 200); // old code
                //mobilepicturePath4 = getResizedBitmap(bitmapmobilephoto4, 100); // old code
                mobilepicturePath4adharcrad = String.valueOf(bitmap4adharcard);
               /* BitmapFactory.Options bounds = new BitmapFactory.Options();
                bounds.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file4.toString(), bounds);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(file4.toString(), opts);
                ExifInterface exif = new ExifInterface(file4.toString());
                String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                int rotationAngle = 0;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
                Matrix matrix = new Matrix();
                matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                bitmapmobilephoto4 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);*/
                ImageView imageView = findViewById(R.id.civ_adharcardimage);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap4adharcard);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE4ADHARCARDBACK && resultCode == -1) {
            try {
                //bitmap4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri4);
                bitmap4adharcardback = (Bitmap) data.getExtras().get("data");

               /* int compressionRatio = 4; //1 == originalImage, 2 = 50% compression, 4=25% compress
                File file = new File(String.valueOf(bitmap2));
                try {
                    bitmapmobilephoto4 = BitmapFactory.decodeFile(file.getPath());
                    bitmapmobilephoto4.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                    getResizedBitmap(bitmapmobilephoto4, 200); // old code
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }*/
                // bitmapprofilephoto = getResizedBitmap(bitmap1, 200); // old code
                //mobilepicturePath4 = getResizedBitmap(bitmapmobilephoto4, 100); // old code
                mobilepicturePath4adharcradback = String.valueOf(bitmap4adharcardback);
               /* BitmapFactory.Options bounds = new BitmapFactory.Options();
                bounds.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file4.toString(), bounds);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(file4.toString(), opts);
                ExifInterface exif = new ExifInterface(file4.toString());
                String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                int rotationAngle = 0;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
                Matrix matrix = new Matrix();
                matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                bitmapmobilephoto4 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);*/
                ImageView imageView = findViewById(R.id.civ_adharcardimageback);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap4adharcardback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "File Not Found , Please Re-Capture !!", Toast.LENGTH_SHORT).show();
        }
    }

    private String getResizedBitmap(Bitmap bitmap1, int i) {
        int width = bitmap1.getWidth();
        int height = bitmap1.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = i;
            height = (int) (width / bitmapRatio);
        } else {
            height = i;
            width = (int) (height * bitmapRatio);
        }
        return String.valueOf(Bitmap.createScaledBitmap(bitmap1, width, height, true));
    }
    /*create end*/

    private void progressDialog() {
        kProgressHUD = new KProgressHUD(CompleteleadItemdescriptionActivity.this);
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.CAMERA",
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_descld:
                finish();
                break;
            case R.id.btn_completedcld:

                extraamount = et_addextraamount_cld.getText().toString();
                imeinumber = et_enterimeinumber.getText().toString();
                System.out.println("getting_extraamount " + extraamount);
                System.out.println("getting_imeinumber " + imeinumber);
                if (et_enterimeinumber.getText().toString().length() == 0) {
                    et_addextraamount_cld.setError("Please enter extra amount");
                    et_addextraamount_cld.requestFocus();
                } else if (mobilepicturePath1 == null || mobilepicturePath1.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please capture all images", Toast.LENGTH_LONG).show();
                } else if (mobilepicturePath2 == null || mobilepicturePath2.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please capture all images", Toast.LENGTH_LONG).show();
                } else if (mobilepicturePath3 == null || mobilepicturePath3.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please capture all images", Toast.LENGTH_LONG).show();
                } else if (mobilepicturePath4 == null || mobilepicturePath4.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please capture all images", Toast.LENGTH_LONG).show();
                } else if (mobilepicturePath4adharcrad == null || mobilepicturePath4adharcrad.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please capture front image of adharcard", Toast.LENGTH_LONG).show();
                } else if (mobilepicturePath4adharcradback == null || mobilepicturePath4adharcradback.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please capture back images of adharcard", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog();
                    senddataforcomplete();
                }
                break;
            default:
                break;
        }
    }

    private void senddataforcomplete() {
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/completeleads.php",
                //VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, "https://postman-echo.com/post",
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            jsonString = new String(response.data, "UTF-8");
                            System.out.println("getting_data_from_server" + jsonString);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (jsonString == null) {
                            dismissDialog();
                        } else {
                            System.out.println("getting_response" + jsonString);
                            dismissDialog();
                            try {
                                JSONObject jsonObject = new JSONObject(jsonString);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                if ((status.equals("1"))) {
                                    dismissDialog();
                                    Intent intent = new Intent(getApplicationContext(), CompleteLeadActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                } else {
                                    dismissDialog();
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Please Try again !", Toast.LENGTH_SHORT).show();
                        dismissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>(); // candidatename
                params.put("vendorid", vendorid);
                params.put("lead_id", lead_id);
                //params.put("flag", "inprogress");
                params.put("flag", "Complete");
                params.put("IMEI", imeinumber);
                params.put("ajent_id", ajentassign);
                params.put("extraamount", extraamount);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                params.put("pic1", new DataPart(mobilepicturePath1 + ".png", getFileDataFromDrawable(bitmap1)));
                params.put("pic2", new DataPart(mobilepicturePath2 + ".png", getFileDataFromDrawable(bitmap2)));
                params.put("pic3", new DataPart(mobilepicturePath3 + ".png", getFileDataFromDrawable(bitmap3)));
                params.put("pic4", new DataPart(mobilepicturePath4 + ".png", getFileDataFromDrawable(bitmap4)));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).
                addToRequestQueue(volleyMultipartRequest);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

}

