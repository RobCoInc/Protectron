package tech.secure.protectron.basic_user;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import tech.secure.protectron.LocationActivity;
import tech.secure.protectron.R;
import tech.secure.protectron.database_helpers.DBHelper;

import static android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER;

public class ApplyForShiftActivity extends AppCompatActivity {

    Bundle extras;
    long user_id;

    DBHelper db;

    SimpleCursorAdapter cAdapter;
    Cursor cursor;

    private final String TAG = "ApplyForShiftActivity";

    ListView mShiftsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_shift);

        db = new DBHelper(this);

        extras = getIntent().getExtras();
        user_id = extras.getLong("user_id");

        mShiftsListView = (ListView) findViewById(R.id.available_shifts_list_view);

        cursor = db.readAvailableShifts();
        cAdapter = new SimpleCursorAdapter(this, R.layout.support_simple_spinner_dropdown_item, cursor, new String[]{"shift_date", "shift_start_time"}, new int[]{android.R.id.text1, android.R.id.text2}, FLAG_REGISTER_CONTENT_OBSERVER);

        mShiftsListView.setAdapter(cAdapter);
        mShiftsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: " + l);
                db.reserveShift(l);
                db.newUserShift(l, user_id);
                Toast.makeText(getBaseContext(), "Shift has been added to your list", Toast.LENGTH_SHORT).show();
                updateList();
            }
        });
    }

    void updateList()
    {
        cursor = db.readAvailableShifts();
        cAdapter = new SimpleCursorAdapter(this, R.layout.support_simple_spinner_dropdown_item, cursor, new String[]{"shift_date", "shift_start_time"}, new int[]{android.R.id.text1, android.R.id.text2}, FLAG_REGISTER_CONTENT_OBSERVER);

        mShiftsListView.setAdapter(cAdapter);
        mShiftsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: " + l);
                db.reserveShift(l);
                db.newUserShift(l, user_id);
                Toast.makeText(getBaseContext(), "Shift has been added to your list", Toast.LENGTH_SHORT).show();
                updateList();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }
}
