package tech.secure.protectron.basic_user;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import tech.secure.protectron.R;
import tech.secure.protectron.database_helpers.DBHelper;

import static android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER;

public class ShiftLocationListActivity extends AppCompatActivity {

    DBHelper db;
    Bundle extras;

    ListView mShiftLocationListView;

    ArrayAdapter adapter;
    SimpleCursorAdapter cAdapter;
    Cursor cursor;

    long user_id;

    private final String TAG = "ShiftLocListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_location_list);

        db = new DBHelper(this);

        extras = getIntent().getExtras();

        user_id = extras.getLong("user_id");

        mShiftLocationListView = (ListView) findViewById(R.id.shift_location_list_view);
        adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, db.getAllLocations());

        cursor = db.readAllLocations();
        cAdapter = new SimpleCursorAdapter(this, R.layout.support_simple_spinner_dropdown_item, cursor, new String[]{"location_name", "location_address"}, new int[]{android.R.id.text1, android.R.id.text2}, FLAG_REGISTER_CONTENT_OBSERVER);

        mShiftLocationListView.setAdapter(cAdapter);
        mShiftLocationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: " + l);
                Intent intent = new Intent(getBaseContext(), ApplyForShiftActivity.class);
                intent.putExtra("location_id", l);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        cursor = db.readAllLocations();
        cAdapter = new SimpleCursorAdapter(this, R.layout.support_simple_spinner_dropdown_item, cursor, new String[]{"location_name", "location_address"}, new int[]{android.R.id.text1, android.R.id.text2}, FLAG_REGISTER_CONTENT_OBSERVER);
        mShiftLocationListView.setAdapter(cAdapter);
        mShiftLocationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: " + l);
                Intent intent = new Intent(getBaseContext(), ApplyForShiftActivity.class);
                intent.putExtra("location_id", l);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
    }
}