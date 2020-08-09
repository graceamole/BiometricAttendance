package com.grace.biometricattendance;

import androidx.appcompat.app.AppCompatActivity;
import asia.kanopi.fingerscan.Fingerprint;
import asia.kanopi.fingerscan.Status;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

public class FingerPrintPage extends AppCompatActivity {

    private Fingerprint fingerprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);

        fingerprint = new Fingerprint();
    }

    Handler printHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            byte[] image;
            String errorMessage = "empty";
            int status = msg.getData().getInt("status");
            Intent intent = new Intent();
            intent.putExtra("status", status);
            if (status == Status.SUCCESS) {
                image = msg.getData().getByteArray("img");
                intent.putExtra("img", image);

            } else {
                errorMessage = msg.getData().getString("errorMessage");
                intent.putExtra("errorMessage", errorMessage);
            }
            setResult(RESULT_OK, intent);
            finish();


        }
    };

    Handler updateHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            int status = msg.getData().getInt("status");
            switch (status){
                case Status.INITIALISED:
                    Toast.makeText(FingerPrintPage.this, "Setting up reader", Toast.LENGTH_SHORT).show();
                    break;
                case Status.SCANNER_POWERED_ON:
                    break;
                case Status.READY_TO_SCAN:
                    Toast.makeText(FingerPrintPage.this, "Ready to scan", Toast.LENGTH_SHORT).show();
                    break;
                case Status.FINGER_DETECTED:
                    Toast.makeText(FingerPrintPage.this, "Finger detected", Toast.LENGTH_SHORT).show();
                    break;
                case Status.RECEIVING_IMAGE:
                    Toast.makeText(FingerPrintPage.this, "Receiving Image", Toast.LENGTH_SHORT).show();
                    break;
                case Status.FINGER_LIFTED:
                    break;
                case Status.SCANNER_POWERED_OFF:
                    Toast.makeText(FingerPrintPage.this, "Scanner Off", Toast.LENGTH_SHORT).show();
                    break;
                case Status.SUCCESS:
                    Toast.makeText(FingerPrintPage.this, "FingerPrint successfully captured", Toast.LENGTH_SHORT).show();
                    break;
                case Status.ERROR:
                    Toast.makeText(FingerPrintPage.this, msg.getData().getString("errorMessage"), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(FingerPrintPage.this, String.valueOf(status) + msg.getData().getString("errorMessage"), Toast.LENGTH_SHORT).show();


            }
        }
    };



    @Override
    protected void onStart() {
        fingerprint.scan(this,printHandler,updateHandler);
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        fingerprint.turnOffReader();
    }
}
