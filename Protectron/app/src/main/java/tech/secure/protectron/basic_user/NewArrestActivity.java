package tech.secure.protectron.basic_user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLogTags;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import tech.secure.protectron.R;
import tech.secure.protectron.database_helpers.DBHelper;
import tech.secure.protectron.objects.Arrest;
import tech.secure.protectron.objects.Description;
import tech.secure.protectron.objects.Shift;

public class NewArrestActivity extends AppCompatActivity {

    Arrest newArrest;
    Shift shift;
    Description attachedDescription;

    DBHelper db;

    Bundle extras;
    long user_id;
    long shift_id;

    String hairColor;
    String eyeColor;
    String skinColor;
    String hasTatoo;

    String currentTime;

    TextView mArrestDate;
    TextView mArrestTime;
    EditText mArrestType;

    EditText mDescritionNameInput;
    EditText mHeightInput;
    EditText mWeightInput;

    Spinner mHairColorSpinner;
    Spinner mEyeColorSpinner;
    Spinner mSkinColorSpinner;
    Spinner mHasTatooSpinner;

    ArrayAdapter<CharSequence> mHairColorAdapter;
    ArrayAdapter<CharSequence> mEyeColorAdapter;
    ArrayAdapter<CharSequence> mSkinColorAdapter;
    ArrayAdapter<CharSequence> mHasTatooAdapter;

    Button mSubmitArrestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_arrest);

        db = new DBHelper(this);

        extras = getIntent().getExtras();
        user_id = extras.getLong("user_id");
        shift_id = extras.getLong("shift_id");

        shift = db.getShiftById(shift_id);

        mArrestDate = (TextView) findViewById(R.id.arrest_date_view);
        mArrestTime = (TextView) findViewById(R.id.arrest_time_view);

        mDescritionNameInput = (EditText) findViewById(R.id.description_name_input);
        mHeightInput = (EditText) findViewById(R.id.description_height_input);
        mWeightInput = (EditText) findViewById(R.id.description_weight_input);

        mSubmitArrestButton = (Button) findViewById(R.id.submit_arrest_button);

        mHairColorSpinner = (Spinner) findViewById(R.id.arrest_hair_color_spinner);
        mHairColorAdapter = ArrayAdapter.createFromResource(this,
                R.array.hair_color_array, android.R.layout.simple_spinner_item);
        mHairColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mHairColorSpinner.setAdapter(mHairColorAdapter);
        mHairColorSpinner.setPrompt("Choose hair color...");

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        currentTime = time.format(c.getTime());
        // formattedDate have current date/time
        Toast.makeText(this, currentTime, Toast.LENGTH_SHORT).show();

        mArrestDate.setText(shift.getDate());
        mArrestTime.setText(currentTime);

        mHairColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hairColor = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mEyeColorSpinner = (Spinner) findViewById(R.id.arrest_eye_color_spinner);
        mEyeColorAdapter = ArrayAdapter.createFromResource(this,
                R.array.eye_color_array, android.R.layout.simple_spinner_item);
        mEyeColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEyeColorSpinner.setAdapter(mEyeColorAdapter);
        mEyeColorSpinner.setPrompt("Choose eye color...");

        mEyeColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eyeColor = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSkinColorSpinner = (Spinner) findViewById(R.id.arrest_skin_color_spinner);
        mSkinColorAdapter = ArrayAdapter.createFromResource(this,
                R.array.skin_color_array, android.R.layout.simple_spinner_item);
        mSkinColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSkinColorSpinner.setAdapter(mSkinColorAdapter);
        mSkinColorSpinner.setPrompt("Choose skin color...");

        mSkinColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                skinColor = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*
        mHasTatooSpinner = (Spinner) findViewById(R.id.arrest_has_tatoo_spinner);
        mHasTatooAdapter = ArrayAdapter.createFromResource(this,
                R.array.has_tatoo_array, android.R.layout.simple_spinner_item);
        mHasTatooAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mHasTatooSpinner.setAdapter(mHasTatooAdapter);
        mHasTatooSpinner.setPrompt("Tatoos...");

        mHasTatooSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                hasTatoo = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */

        mSubmitArrestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newArrest = new Arrest();
                attachedDescription = new Description();

                newArrest.setDate(shift.getDate());
                newArrest.setTime(shift.getTime());
                newArrest.setType("GENERIC");
                newArrest.setUserID(user_id);
                newArrest.setLocationID(shift.getLocation_id());

                attachedDescription.setName(mDescritionNameInput.getText().toString());
                attachedDescription.setHeight(Integer.parseInt(mHeightInput.getText().toString()));
                attachedDescription.setWeight(Integer.parseInt(mWeightInput.getText().toString()));
                attachedDescription.setHairColor(hairColor);
                attachedDescription.setEyeColor(eyeColor);
                attachedDescription.setSkinColor(skinColor);

                db.newArrest(newArrest, attachedDescription);
                finish();
                }
        });
    }
}
