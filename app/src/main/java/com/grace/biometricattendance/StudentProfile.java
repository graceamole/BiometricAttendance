package com.grace.biometricattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.grace.biometricattendance.models.Class;
import com.grace.biometricattendance.models.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StudentProfile extends AppCompatActivity {
    TextView name, matriculationNumberText, gender_text, level_text;
    FirebaseFirestore firestore;
    List<Student> listOfStudents = new ArrayList<>();
    Button confirm_attendance;
    FirebaseAuth auth;
    CollectionReference reference;
    List<Class> classes;
    String classDocumentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        name = (TextView) findViewById(R.id.name);
        matriculationNumberText = (TextView) findViewById(R.id.matriculation_number_text);
        gender_text = (TextView) findViewById(R.id.gender_text);
        level_text = (TextView) findViewById(R.id.level_text);
        confirm_attendance = (Button) findViewById(R.id.confirm_attendance);

        firestore = FirebaseFirestore.getInstance();
        reference = firestore.collection("class");
        auth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String classId = intent.getStringExtra("classId");
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        getClassById(classId);
        if (id != null) {
            findStudentById(id);

        }

        confirm_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudentToDataBase();
            }
        });


    }

    private void getClassById(String classId) {
        String adminUserId = auth.getCurrentUser().getUid();
        reference.whereEqualTo("classId", classId).get().addOnSuccessListener
                (new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                           classDocumentId = snapshot.getId();
                            Class newClass = snapshot.toObject(Class.class);
                            listOfStudents = newClass.getListOfStudents();
                        }

//               addStudentToDataBase();
                    }
                });
    }

    private void addStudentToDataBase() {
       reference.document(classDocumentId).update("listOfStudents", listOfStudents);
git
    }

    private void findStudentById(final String id) {
        Toast.makeText(this, "NEW IDDDDDD" + id, Toast.LENGTH_LONG).show();
        firestore.collection("Student").whereEqualTo("studentId", id).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            Toast.makeText(StudentProfile.this, "proceed", Toast.LENGTH_SHORT).show();
                            String level = documentSnapshot.get("level").toString();
                            String gender = documentSnapshot.get("gender").toString();
                            String firstName = documentSnapshot.get("first_name").toString();
                            String lastName = documentSnapshot.get("last_name").toString();
                            String matriculationNumber = documentSnapshot.getId();
                            Student student = new Student(id, level, gender, firstName, lastName);
                            name.setText(firstName + " " + lastName);
                            matriculationNumberText.setText(matriculationNumber);
                            level_text.setText(level);
                            gender_text.setText(gender);
                            listOfStudents.add(student);

                        }


                    }

                });
    }


}