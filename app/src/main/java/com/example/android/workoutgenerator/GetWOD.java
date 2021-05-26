package com.example.android.workoutgenerator;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GetWOD extends ArrayAdapter<Complex>{

    public GetWOD(Activity context, ArrayList<Complex> workouts) {

        super(context, 0, workouts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                   R.layout.list_item, parent, false);
        }

        Complex currentWorkout = getItem(position);

        TextView desc = (TextView)  listItemView.findViewById(R.id.description_list_view);

        desc.setText(currentWorkout.getMetcon());



        return listItemView;
    }



}
