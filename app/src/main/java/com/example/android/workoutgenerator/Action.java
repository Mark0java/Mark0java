package com.example.android.workoutgenerator;

import android.content.Intent;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.text.InputType;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import com.example.android.workoutgenerator.data.WodDbHelper;
import com.example.android.workoutgenerator.data.WodButton.WorkoutEntry;


public class Action extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Intent i = getIntent();
        Complex detailWorkout = (Complex)i.getSerializableExtra("Workout");

        EditText score = (EditText) findViewById(R.id.score_field);
        String woType = detailWorkout.getHowToDo();

        if (woType.equals("FT")){
            score.setInputType(InputType.TYPE_CLASS_DATETIME);
        }
        else if (woType.equals("AMRAP")){
            score.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        else if (woType.equals("EMOM")){
            score.setInputType(InputType.TYPE_CLASS_TEXT);

        }
        else{
            score.setInputType(InputType.TYPE_CLASS_TEXT);
        }



        TextView desc = (TextView) findViewById(R.id.fulldescription);
        desc.setText(detailWorkout.getMetcon());

        TextView rx = (TextView) findViewById(R.id.rx);
        rx.setText(detailWorkout.getOkey());

        int saved = detailWorkout.getLiked();


        if (saved == 0){

        }
        else {
            Button save_button = (Button) findViewById(R.id.save_button);
            save_button.setVisibility(View.GONE);

            Button remove_button = (Button) findViewById(R.id.remove_button);
            remove_button.setVisibility(View.VISIBLE);
        }


        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    public void saveWorkout(View view){


        Intent i = getIntent();
        Complex detailWorkout = (Complex)i.getSerializableExtra("Workout");
        String desc = detailWorkout.getMetcon();
        String rx = detailWorkout.getOkey();
        String type = detailWorkout.getHowToDo();



        detailWorkout.setLiked(1);
        int saved = detailWorkout.getLiked();

        WodDbHelper mDbHelper = new WodDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WorkoutEntry.COLUMN_WO_DESCRIPTION, desc);
        values.put(WorkoutEntry.COLUMN_WO_RX, rx);
        values.put(WorkoutEntry.COLUMN_WO_TYPE, type);
        values.put(WorkoutEntry.COLUMN_WO_SAVE, saved);


        long newRowID = db.insert(WorkoutEntry.TABLE_NAME, null, values);

        if (newRowID == -1){
            Toast.makeText(this, "Error with saving workout", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Workout saved with row id: " + newRowID, Toast.LENGTH_SHORT).show();
        }

        Button save_button = (Button) findViewById(R.id.save_button);
        save_button.setVisibility(View.GONE);

        Button remove_button = (Button) findViewById(R.id.remove_button);
        remove_button.setVisibility(View.VISIBLE);


    }

    public void removeWorkout(View view){


        Intent i = getIntent();
        Complex detailWorkout = (Complex)i.getSerializableExtra("Workout");
        String desc = detailWorkout.getMetcon();
        String rx = detailWorkout.getOkey();
        String type = detailWorkout.getHowToDo();

        detailWorkout.setLiked(1);
        int saved = detailWorkout.getLiked();


        WodDbHelper mDbHelper = new WodDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        String whereClause = WorkoutEntry.COLUMN_WO_DESCRIPTION + "=?";
        String[] whereArgs = new String[] {desc};

        db.delete(WorkoutEntry.TABLE_NAME, whereClause, whereArgs);

        Button save_button = (Button) findViewById(R.id.save_button);
        save_button.setVisibility(View.VISIBLE);

        Button remove_button = (Button) findViewById(R.id.remove_button);
        remove_button.setVisibility(View.GONE);

    }


}
