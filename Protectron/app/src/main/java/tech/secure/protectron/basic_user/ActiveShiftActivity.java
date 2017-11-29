package tech.secure.protectron.basic_user;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tech.secure.protectron.R;
import tech.secure.protectron.database_helpers.DBHelper;
import tech.secure.protectron.objects.Location;
import tech.secure.protectron.objects.Shift;
import tech.secure.protectron.objects.User;

public class ActiveShiftActivity extends AppCompatActivity {

    long user_id;
    long shift_id;

    DBHelper db;

    Bundle extras;

    Shift shift;
    Location location;
    User user;

    TextView mUserName;
    TextView mShiftLocationName;
    TextView mShiftEndTime;

    Button mFinishShiftButton;

    String sShiftEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_shift);

        db = new DBHelper(this);

        extras = getIntent().getExtras();
        user_id = extras.getLong("user_id");
        shift_id = extras.getLong("shift_id");

        shift = db.getShiftById(shift_id);
        location = db.getLocationById(shift.getLocation_id());
        user = db.getUserById(user_id);

        mUserName = (TextView) findViewById(R.id.shift_user_name_view);
        mShiftLocationName = (TextView) findViewById(R.id.shift_location_view);
        mShiftEndTime = (TextView) findViewById(R.id.shift_end_time_view);

        mUserName.setText(user.getName());
        mShiftLocationName.setText(location.getName());

        sShiftEndTime = getString(R.string.shift_end_time_head);

        mShiftEndTime.setText(sShiftEndTime + shift.getEndTime());

        mFinishShiftButton = (Button) findViewById(R.id.finish_shift_button);
        mFinishShiftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.finishUserShift(shift_id, user_id);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_active, menu);

        for(int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spanString.length(), 0); //fix the color to white
            item.setTitle(spanString);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_arrest:
                Intent iNewArrest = new Intent(this, NewArrestActivity.class);
                iNewArrest.putExtra("user_id", user_id);
                iNewArrest.putExtra("shift_id", shift_id);
                startActivity(iNewArrest);
                return super.onOptionsItemSelected(item);
            case R.id.search_arrests:
                Intent iSearchArrest = new Intent(this, SearchArrestActivity.class);
                startActivity(iSearchArrest);
                return super.onOptionsItemSelected(item);
            case R.id.search_alias:
                Intent iSearchAlias = new Intent(this, SearchArrestActivity.class);
                startActivity(iSearchAlias);
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
