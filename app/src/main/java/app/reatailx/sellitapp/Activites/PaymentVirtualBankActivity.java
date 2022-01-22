package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class PaymentVirtualBankActivity extends AppCompatActivity implements View.OnClickListener {

    public SessionManager session;
    public RelativeLayout relativeLayout_back;
    public Button btn_banktransfer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.virtualbank_activity);
        session = new SessionManager(getApplicationContext());
        btn_banktransfer = findViewById(R.id.btn_banktransfer);
        relativeLayout_back = findViewById(R.id.back_paymentvirtualbank);
        relativeLayout_back.setOnClickListener(this);
        btn_banktransfer.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_paymentvirtualbank:
                Intent intent1 = new Intent(getApplicationContext(), PaymentCommisionWalletActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.btn_banktransfer:
                Intent intent = new Intent(getApplicationContext(), BankTransferPayOnlineActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(getApplicationContext(), PaymentCommisionWalletActivity.class);
        startActivity(intent1);
        finish();
    }
}

