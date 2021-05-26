package com.example.android.workoutgenerator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.workoutgenerator.data.WodDbHelper;

import com.example.android.workoutgenerator.data.WodButton.WorkoutEntry;

import java.util.ArrayList;


public class ActionCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        databaseInfo();



    }


    private void databaseInfo() {

        WodDbHelper mDbHelper = new WodDbHelper(this);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                WorkoutEntry._ID,
                WorkoutEntry.COLUMN_WO_DESCRIPTION,
                WorkoutEntry.COLUMN_WO_RX,
                WorkoutEntry.COLUMN_WO_TYPE,
                WorkoutEntry.COLUMN_WO_SAVE
        };

        Cursor cursor = db.query(
                WorkoutEntry.TABLE_NAME,
                projection, null, null, null, null, null
        );

        cursor.moveToFirst();


        final ArrayList<Complex> complexes = new ArrayList<>();

        try {
            for (int i = 0; i < cursor.getCount(); ++i){
                int descColumnIndex = cursor.getColumnIndex(WorkoutEntry.COLUMN_WO_DESCRIPTION);
                String desc = cursor.getString(descColumnIndex);
                int rxColumnIndex = cursor.getColumnIndex(WorkoutEntry.COLUMN_WO_RX);
                String rx = cursor.getString(rxColumnIndex);
                int typeColumnIndex = cursor.getColumnIndex(WorkoutEntry.COLUMN_WO_TYPE);
                String type = cursor.getString(typeColumnIndex);
                int savedColumnIndex = cursor.getColumnIndex(WorkoutEntry.COLUMN_WO_SAVE);
                int saved = cursor.getInt(savedColumnIndex);

                if (saved == 1){
                    Complex currentWorkout = new Complex(desc, rx, type, saved);
                    complexes.add(currentWorkout);
                }

                cursor.moveToNext();
            }


            GetWOD adapter = new GetWOD(this, complexes);

            GridView gridView = (GridView) findViewById(R.id.saved_list);


            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    Intent intent = new Intent(ActionCreate.this, Action.class);
                    Complex detailComplex = complexes.get(position);
                    intent.putExtra("Workout", detailComplex);
                    startActivity(intent);
                }
            });

        } finally {

            cursor.close();
        }
    }


}
