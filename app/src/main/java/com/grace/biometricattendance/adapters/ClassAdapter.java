package com.grace.biometricattendance.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grace.biometricattendance.models.Class;
import com.grace.biometricattendance.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.MyViewHolder> {
    ArrayList<Class> classes;
    Context context;

    public ClassAdapter(Context context, ArrayList<Class> classes) {
        this.context = context;
        this.classes = classes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_class_registration, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.course_title.setText(classes.get(position).getCourse_title());
        holder.course_code.setText(classes.get(position).getCourse_code());

    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView course_title;
        TextView course_code;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            course_title = (TextView) itemView.findViewById(R.id.course_title);
            course_code = (TextView)itemView.findViewById(R.id.course_code);
        }
    }
}
