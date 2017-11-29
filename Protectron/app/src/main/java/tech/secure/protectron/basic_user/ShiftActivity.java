package tech.secure.protectron.basic_user;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import tech.secure.protectron.R;
import tech.secure.protectron.database_helpers.DBHelper;
import tech.secure.protectron.objects.Location;
import tech.secure.protectron.objects.Shift;

public class ShiftActivity extends BaseActivity {

    DBHelper db;

    Bundle extras;
    long shift_id;
    long user_id;

    Location location;
    Shift shift;

    TextView mLocationName;
    TextView mLocationCity;
    TextView mLocationAddress;

    TextView mShiftDate;
    TextView mShiftTime;
    TextView mShiftEndTime;

    Button mStartShift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift);

        extras = getIntent().getExtras();
        shift_id = extras.getLong("shift_id");
        user_id = extras.getLong("user_id");

        db = new DBHelper(this);

        shift = db.getShiftById(shift_id);
        location = db.getLocationById(shift.getLocation_id());

        mLocationName = (TextView) findViewById(R.id.shift_location_name_view);
        mLocationCity = (TextView) findViewById(R.id.shift_location_city_view);
        mLocationAddress = (TextView) findViewById(R.id.shift_location_address_view);

        mShiftDate = (TextView) findViewById(R.id.shift_date_view);
        mShiftTime = (TextView) findViewById(R.id.shift_time_view);
        mShiftEndTime = (TextView) findViewById(R.id.shift_end_time_view);

        mStartShift = (Button) findViewById(R.id.start_shift_button);

        mLocationName.setText(location.getName());
        mLocationCity.setText(location.getCity());
        mLocationAddress.setText(location.getAddress());

        mShiftDate.setText(shift.getDate());
        mShiftTime.setText(shift.getTime());
        mShiftEndTime.setText(shift.getEndTime());

        mStartShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startShift = new Intent(getBaseContext(), ActiveShiftActivity.class);
                startShift.putExtra("shift_id", shift_id);
                startShift.putExtra("user_id", user_id);
                startActivity(startShift);
                finish();
            }
        });
    }
}
