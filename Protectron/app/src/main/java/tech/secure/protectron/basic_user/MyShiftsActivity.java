package tech.secure.protectron.basic_user;

import android.content.Intent;
import android.database.Cursor;
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

public class MyShiftsActivity extends BaseActivity {

    DBHelper db;

    ListView mShiftListView;

    ArrayAdapter adapter;
    SimpleCursorAdapter cAdapter;
    Cursor cursor;

    long user_id;

    private final String TAG = "MyShiftsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shifts);

        db = new DBHelper(this);

        Bundle extras = getIntent().getExtras();

        user_id = extras.getLong("user_id");

        mShiftListView = (ListView) findViewById(R.id.my_shift_list_view);

        cursor = db.readAllUserShifts(user_id);
        cAdapter = new SimpleCursorAdapter(this, R.layout.support_simple_spinner_dropdown_item, cursor, new String[]{"shift_date", "shift_start_time"}, new int[]{android.R.id.text1, android.R.id.text2}, FLAG_REGISTER_CONTENT_OBSERVER);

        mShiftListView.setAdapter(cAdapter);
        mShiftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: " + l);
                Intent intent = new Intent(getBaseContext(), ShiftActivity.class);
                intent.putExtra("shift_id", l);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        cursor = db.readAllUserShifts(user_id);
        cAdapter = new SimpleCursorAdapter(this, R.layout.support_simple_spinner_dropdown_item, cursor, new String[]{"shift_date", "shift_start_time"}, new int[]{android.R.id.text1, android.R.id.text2}, FLAG_REGISTER_CONTENT_OBSERVER);
        mShiftListView.setAdapter(cAdapter);
        mShiftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: " + l);
                Intent intent = new Intent(getBaseContext(), ShiftActivity.class);
                intent.putExtra("shift_id", l);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
    }
}
