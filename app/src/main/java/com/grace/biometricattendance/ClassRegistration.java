package com.grace.biometricattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.grace.biometricattendance.models.Class;

import java.util.ArrayList;

public class ClassRegistration extends AppCompatActivity {
    private Button addClass;
    private Button setAttendance;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore firestore;
    private FirestoreRecyclerAdapter<Class, MyViewHolder> firestoreRecyclerAdapter;
    private Query query;


    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_registration);

        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");

        addClass = (Button) findViewById(R.id.add_class);
        setAttendance = (Button) findViewById(R.id.set_attendance);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firestore = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = firestore.collection("class");
        Toast.makeText(this, user_id, Toast.LENGTH_SHORT).show();
        query = collectionReference.whereEqualTo("id", user_id);

        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassRegistration.this, AddClass.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirestoreRecyclerOptions<Class> options = new FirestoreRecyclerOptions.Builder<Class>()
                .setQuery(query, Class.class)
                .build();
        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<Class, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i, @NonNull Class aClass) {
                Log.i("TAG", aClass.getId());
                myViewHolder.setData(aClass.getCourse_title(), aClass.getCourse_code());
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_registration_adapter,
                        parent, false);
                return new MyViewHolder(view);
            }
        };
        recyclerView.setAdapter(firestoreRecyclerAdapter);
//        firestoreRecyclerAdapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(firestoreRecyclerAdapter != null){
            firestoreRecyclerAdapter.stopListening();
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView course_title;
        TextView course_code;
        View ItemView;// init the item view's

        void setData(String courseTitle, String courseCode) {
            course_title = (TextView) itemView.findViewById(R.id.edit_course_title);
            course_code = (TextView) itemView.findViewById(R.id.edit_course_code);
            course_title.setText(courseTitle);
            course_code.setText(courseCode);
        }


        public MyViewHolder(View itemView) {
            super(itemView);
            ItemView = itemView;
            // get the reference of item view's

        }
    }

    //    private void getClasses(String userid) {
//        firestore.collection("class").whereEqualTo("userId", userid)
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                        Class newClass = new Class(user_id, documentSnapshot.get("course_title").toString(),
//                                documentSnapshot.get("course_code").toString());
//                        Log.i("TAG", newClass.getCourse_code());
//                        listOfClasses.add(newClass);
//                    }
//                } else {
//                    Log.d("TAG", "Error getting documents: ", task.getException());
//                }
//            }
//        });
//    }
}