package com.grace.biometricattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.grace.biometricattendance.adapters.ClassAdapter;
import com.grace.biometricattendance.models.Class;

import java.util.ArrayList;

public class ClassRegistration extends AppCompatActivity {
    private Button addClass;
    private Button setAttendance;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore firestore;
    ArrayList<Class> listOfClasses = new ArrayList<>();

    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_registration);

        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");

        addClass = (Button) findViewById(R.id.add_class);
        setAttendance = (Button) findViewById(R.id.set_attendance);

        firestore = FirebaseFirestore.getInstance();

        if (user_id != null) {
            getClasses(user_id);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (!listOfClasses.isEmpty()) {
            ClassAdapter classAdapter = new ClassAdapter(ClassRegistration.this, listOfClasses);
            recyclerView.setAdapter(classAdapter);
        }

        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassRegistration.this, AddClass.class);
                startActivity(intent);
            }
        });


    }

    private void getClasses(String user_id) {
        firestore.collection("class").whereEqualTo("user_id", user_id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        Class newClass = new Class(documentSnapshot.get("course_title").toString(),
                                documentSnapshot.get("course_code").toString());
                        listOfClasses.add(newClass);
                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
    }
}