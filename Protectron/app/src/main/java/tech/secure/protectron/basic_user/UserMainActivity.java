package tech.secure.protectron.basic_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tech.secure.protectron.R;
import tech.secure.protectron.database_helpers.DBHelper;

public class UserMainActivity extends BaseActivity
{
    DBHelper db;

    long id;

    TextView mUserName;

    Button mLocations;
    Button mApplyForShift;
    Button mShifts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        db = new DBHelper(this);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");

        id = db.getUserIdByEmail(emailFromIntent);

        String username = (db.getUsernameByEmail(emailFromIntent));

        mUserName = (TextView) findViewById(R.id.user_name_text);
        mUserName.setText("Hello, " + username);

        mApplyForShift = (Button) findViewById(R.id.apply_for_shift_button);
        mShifts = (Button) findViewById(R.id.my_shifts_button);


        mApplyForShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent applyForShift = new Intent(getBaseContext(), ShiftLocationListActivity.class);
                applyForShift.putExtra("user_id", id);
                startActivity(applyForShift);
            }
        });

        mShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MyShiftsActivity.class);
                i.putExtra("user_id", id);
                startActivity(i);
            }
        });
    }
}
