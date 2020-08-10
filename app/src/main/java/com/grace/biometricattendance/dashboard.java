package com.grace.biometricattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboard extends AppCompatActivity {
    private CardView create_class;
    private CardView registerStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");

        registerStudents = (CardView)findViewById(R.id.register_student);
        create_class = (CardView)findViewById(R.id.create_class);

        registerStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, StudentDetails.class);
                startActivity(intent);
            }
        });

        create_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, ClassRegistration.class);
                intent.putExtra("user_id", username);
                startActivity(intent);
            }
        });
    }
}
