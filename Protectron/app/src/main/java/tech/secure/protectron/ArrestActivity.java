package tech.secure.protectron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import tech.secure.protectron.database_helpers.DBHelper;
import tech.secure.protectron.objects.Arrest;
import tech.secure.protectron.objects.Description;

public class ArrestActivity extends AppCompatActivity {

    Description description;
    Arrest arrest;

    DBHelper db;

    Bundle extras;

    long description_id;

    TextView mArrestDateView;
    TextView mArrestTimeView;

    TextView mDescriptionNameView;
    TextView mDescriptionHeightView;
    TextView mDescriptionWeightView;
    TextView mDescriptionHairColorView;
    TextView mDescriptionEyeColorView;
    TextView mDescriptionSkinColorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrest);

        db = new DBHelper(this);
        extras = getIntent().getExtras();

        description_id = extras.getLong("arrest_id");

        description = db.getDescriptionById(description_id);
        arrest = db.getArrestById(description.getArrestId());

        mArrestDateView = (TextView) findViewById(R.id.arrest_date_text_view);
        mArrestTimeView = (TextView) findViewById(R.id.arrest_time_text_view);

        mDescriptionNameView = (TextView) findViewById(R.id.description_name_view);
        mDescriptionHeightView = (TextView) findViewById(R.id.description_height_view);
        mDescriptionWeightView = (TextView) findViewById(R.id.description_weight_view);
        mDescriptionHairColorView = (TextView) findViewById(R.id.description_hair_color_view);
        mDescriptionEyeColorView = (TextView) findViewById(R.id.description_eye_color_view);
        mDescriptionSkinColorView = (TextView) findViewById(R.id.description_skin_color_view);

        mArrestDateView.setText("Date of the arrest: " + arrest.getDate());
        mArrestTimeView.setText("Time of the arrest: " + arrest.getTime());

        mDescriptionNameView.setText("Name: " + description.getName());
        mDescriptionHeightView.setText("Height: " + String.valueOf(description.getHeight()) + " cm");
        mDescriptionWeightView.setText("Weight: " + String.valueOf(description.getWeight()) + " lbs");
        mDescriptionHairColorView.setText("Hair Color: " + description.getHairColor());
        mDescriptionEyeColorView.setText("Eye Color: " + description.getEyeColor());
        mDescriptionSkinColorView.setText("Ethnicity: " + description.getSkinColor());
    }
}
