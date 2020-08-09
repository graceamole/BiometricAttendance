package com.grace.biometricattendance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.grace.biometricattendance.sourceafis.FingerprintTemplate;

import java.util.HashMap;
import java.util.Map;

import asia.kanopi.fingerscan.Status;

public class StudentDetails extends AppCompatActivity {
    private EditText matriculationNumber, firstName, lastName, level, gender;
    private Button scanFingerPrint;
    private FirebaseFirestore firestore;
    private static final int REQUEST_CODE = 0;
    byte[] img;
    FingerprintTemplate template;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        matriculationNumber = (EditText) findViewById(R.id.matriculation_number);
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        level = (EditText) findViewById(R.id.level);
        gender = (EditText) findViewById(R.id.gender);
        scanFingerPrint = (Button) findViewById(R.id.scan_fingerprint);

        firestore = FirebaseFirestore.getInstance();

        scanFingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });


    }

    private void saveInfo() {


        if (firstName.getText().toString().isEmpty()
                || lastName.getText().toString().isEmpty()
                || level.getText().toString().isEmpty()
                || gender.getText().toString().isEmpty()) {
            return;
        }
        Intent intent = new Intent(StudentDetails.this, FingerPrintPage.class);
        startActivityForResult(intent, REQUEST_CODE);


        Map<String, Object> student = new HashMap<>();
        student.put("first_name", firstName.getText().toString());
        student.put("last_name", lastName.getText().toString());
        student.put("level", level.getText().toString());
        student.put("gender", gender.getText().toString());
        student.put("fingerprint", img.toString());

        firestore.collection("Student").document(matriculationNumber.getText().toString())
                .set(student).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(StudentDetails.this, "Student created successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StudentDetails.this, "Student creation failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int status;


        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    status = data.getIntExtra("status", Status.ERROR);

                    if (status == Status.SUCCESS) {

                        if (status == Status.SUCCESS) {
                            img = data.getByteArrayExtra("img");


                        }
                    }
                }

        }
    }
}
