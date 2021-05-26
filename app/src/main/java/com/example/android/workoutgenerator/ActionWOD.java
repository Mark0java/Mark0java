package com.example.android.workoutgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;

public class ActionWOD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);



        Intent intent = getIntent();
        String equipSelection1 = intent.getStringExtra("EquipSelection1");
        String movementSelection1 = intent.getStringExtra("MovementSelection1");



        final ArrayList<Complex> сomplex = new ArrayList<>();
        сomplex.add(new Complex("5 rounds for time of:\n" + "Run 400 meters\n" + "15 thrusters", "Men: 95 lb.\nWomen: 65 lb.", "FT", 0));
        сomplex.add(new Complex("15 min Thrusters AMRAP:\n10 Burpees\n10 Sit ups\10 Hand Release Push ups", "AMRAP", 0));
        сomplex.add(new Complex (" 3 rounds for time of:\n" + "50 GHD sit-ups\n" + "25 Dumbbell curls and Thrusters", "FT", 0));
        сomplex.add(new Complex ("3 Rounds For Time:\nRun 800m\n50 Air Squats", "FT", 0));
        сomplex.add(new Complex ("10 Rounds For Time:\n10 Pushups\n10 Sit ups\n10 Squats ", "FT", 0));
        сomplex.add(new Complex("For Time:\n200 Air Squats", "FT", 0));
        сomplex.add(new Complex("5 Rounds For Time:\nRun 200m\n10 Squats\n10 Push Ups", "FT", 0));
        сomplex.add(new Complex("AMRAP in 20 minutes:\n5 Pushups\n10 Situps\n15 Squats", "FT", 0));
        сomplex.add(new Complex("10 min EMOM\nEven Minutes: 20 KB swings\nOdd minutes: 12 burpees", "EMOM", 0));
        сomplex.add(new Complex("Fran:\n21-15-9\nThrusters\nPull-ups", "Men: 95 lbs.\nWomen: 65 lbs", "FT", 0 ));
        сomplex.add(new Complex("For Time:\n" +
                "2 Minutes Double Unders\n" +
                "2 Minutes Situps\n" +
                "Rest 1 min\n" +
                "90 sec Double Unders\n" +
                "90 sec Situps\n" +
                "Rest 1 min\n" +
                "60 sec Double Unders\n" +
                "60 sec Situps", "FT", 0));
        сomplex.add(new Complex("For Time:\n" +
                "60 Pushups\n" +
                "Run 400m\n" +
                "40 Pushups\n" +
                "Run 800m\n" +
                "20 Pushups\n" +
                "Run 1 mile", "FT", 0));



        if (!equipSelection1.equals("Any") && !movementSelection1.equals("Any")) {
            int i = 0;
            while (i < сomplex.size()) {
                String currentWorkout = сomplex.get(i).getMetcon();
                if (!currentWorkout.contains(equipSelection1) ||
                        !currentWorkout.contains(movementSelection1)) {
                    сomplex.remove(i);
                } else {
                    ++i;
                }
            }
        }
        else if (equipSelection1.equals("Any") && !movementSelection1.equals("Any")){
            int i = 0;
            while (i < сomplex.size()) {
                String currentWorkout = сomplex.get(i).getMetcon();
                if (!currentWorkout.contains(movementSelection1)) {
                    сomplex.remove(i);
                } else {
                    ++i;
                }
            }
        }
        else if (!equipSelection1.equals("Any") && movementSelection1.equals("Any")){
            int i = 0;
            while (i < сomplex.size()) {
                String currentWorkout = сomplex.get(i).getMetcon();
                if (!currentWorkout.contains(equipSelection1)) {
                    сomplex.remove(i);
                } else {
                    ++i;
                }
            }
        }

        Collections.shuffle(сomplex);


        GetWOD adapter = new GetWOD(this, сomplex);

        GridView gridView = (GridView) findViewById(R.id.list);


        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(WorkoutActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActionWOD.this, Action.class);
                Complex detailWorkout = сomplex.get(position);
                intent.putExtra("Workout", detailWorkout);
                startActivity(intent);
            }
        });

    }

}


