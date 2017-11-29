package tech.secure.protectron;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import tech.secure.protectron.basic_user.BaseActivity;
import tech.secure.protectron.basic_user.NewShiftActivity;
import tech.secure.protectron.database_helpers.DBHelper;
import tech.secure.protectron.objects.Location;

public class LocationActivity extends AppCompatActivity {

    private final String TAG = "LocationActivity";

    DBHelper db;
    Cursor cus;
    SimpleCursorAdapter cAdapter;

    Bundle extras;
    long user_id;
    long location_id;

    Location location;

    TextView mLocationName;
    TextView mLocationCity;
    TextView mLocationAddress;

    Button mAddShiftButton;

    ListView mShiftList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        extras = getIntent().getExtras();
        location_id = extras.getLong("location_id");
        //user_id = extras.getLong("user_id");

        db = new DBHelper(this);

        location = db.getLocationById(location_id);

        mLocationName = (TextView) findViewById(R.id.location_name);
        mLocationCity = (TextView) findViewById(R.id.location_city);
        mLocationAddress = (TextView) findViewById(R.id.location_address);

        mAddShiftButton = (Button) findViewById(R.id.new_shift_button);

        mShiftList = (ListView) findViewById(R.id.shift_list_view);

        //cus.moveToFirst();
        mLocationName.setText(location.getName());
        mLocationCity.setText(location.getCity());
        mLocationAddress.setText(location.getAddress());

        mAddShiftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewShift = new Intent(getBaseContext(), NewShiftActivity.class);
                iNewShift.putExtra("location_id", location_id);
                //iNewShift.putExtra("user_id", user_id);
                startActivity(iNewShift);
            }
        });


    }
}
