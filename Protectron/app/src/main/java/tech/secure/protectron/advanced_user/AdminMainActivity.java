package tech.secure.protectron.advanced_user;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import tech.secure.protectron.R;
import tech.secure.protectron.database_helpers.DBHelper;

public class AdminMainActivity extends AdminActivity {

    DBHelper db;

    long id;

    TextView mUserName;

    Button mLocations;
    Button mApplyForShift;
    Button mShifts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        db = new DBHelper(this);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");

        id = db.getUserIdByEmail(emailFromIntent);

        String username = (db.getUsernameByEmail(emailFromIntent));
    }
}