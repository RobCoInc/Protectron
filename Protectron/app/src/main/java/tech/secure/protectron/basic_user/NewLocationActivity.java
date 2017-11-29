package tech.secure.protectron.basic_user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tech.secure.protectron.R;
import tech.secure.protectron.database_helpers.DBHelper;
import tech.secure.protectron.objects.Location;

public class NewLocationActivity extends BaseActivity {

    EditText inputName;
    EditText inputCity;
    EditText inputAddress;
    Button addNewLocation;

    Location newLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location);

        inputName = (EditText) findViewById(R.id.inputLocationName);
        inputCity = (EditText) findViewById(R.id.inputLocationCity);
        inputAddress = (EditText) findViewById(R.id.inputLocationAddress);

        addNewLocation = (Button) findViewById(R.id.addLocationButton);

        addNewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((TextUtils.isEmpty(inputName.getText().toString()))) {
                    inputName.setError(getString(R.string.missing_name));
                } else if ((TextUtils.isEmpty(inputCity.getText().toString()))) {
                    inputCity.setError(getString(R.string.missing_city));
                } else if ((TextUtils.isEmpty(inputAddress.getText().toString()))) {
                    inputAddress.setError(getString(R.string.missing_address));
                } else {

                    newLocation = new Location();

                    newLocation.setName(inputName.getText().toString());
                    newLocation.setCity(inputCity.getText().toString());
                    newLocation.setAddress(inputAddress.getText().toString());

                    DBHelper db = new DBHelper(getBaseContext());
                    db.newLocation(newLocation);
                    finish();
                }
            }
        });
    }
}
