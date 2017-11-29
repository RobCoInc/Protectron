package tech.secure.protectron.basic_user;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Locale;

import tech.secure.protectron.R;
import tech.secure.protectron.database_helpers.DBHelper;
import tech.secure.protectron.objects.Shift;

@RequiresApi(api = Build.VERSION_CODES.N)
public class NewShiftActivity extends BaseActivity {
    String TAG = "NewShiftActivity";

    EditText shiftDate;
    EditText shiftTime;
    EditText shiftEndTime;

    Bundle extras;
    long user_id;
    long location_id;

    Shift newShift;

    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shift);

        Button mAddEventBtn = (Button) findViewById(R.id.add_shift_button);

        shiftDate = (EditText) findViewById(R.id.shift_date_edit);
        shiftDate.setText("" + Calendar.DAY_OF_MONTH + "/" + Calendar.MONTH + "/" + Calendar.YEAR + "");

        shiftTime = (EditText) findViewById(R.id.shift_time_edit);
        shiftTime.setText("" + "00" + ":" + "00" + "");

        shiftEndTime = (EditText) findViewById(R.id.shift_end_time_edit);
        shiftEndTime.setText("" + "00" + ":" + "00" + "");

        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateLabel();
            }

        };

        shiftDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewShiftActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        shiftTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(NewShiftActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if(selectedHour < 10 && selectedMinute < 10)
                        {
                            shiftTime.setText("0" + selectedHour + ":0" + selectedMinute);
                        }
                        else if(selectedMinute < 10)
                        {
                            shiftTime.setText(selectedHour + ":0" + selectedMinute);
                        }
                        else if (selectedHour < 10)
                        {
                            shiftTime.setText("0" + selectedHour + ":" + selectedMinute);
                        }
                        else
                        {
                            shiftTime.setText(selectedHour + ":" + selectedMinute);
                        }
                    }
                }, hour, minute, true);
                mTimePicker.setTitle(getString(R.string.select_time));
                mTimePicker.show();
            }
        });

        shiftEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(NewShiftActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if(selectedHour < 10 && selectedMinute < 10)
                        {
                            shiftEndTime.setText("0" + selectedHour + ":0" + selectedMinute);
                        }
                        else if(selectedMinute < 10)
                        {
                            shiftEndTime.setText(selectedHour + ":0" + selectedMinute);
                        }
                        else if (selectedHour < 10)
                        {
                            shiftEndTime.setText("0" + selectedHour + ":" + selectedMinute);
                        }
                        else
                        {
                            shiftEndTime.setText(selectedHour + ":" + selectedMinute);
                        }
                    }
                }, hour, minute, true);
                mTimePicker.setTitle(getString(R.string.select_time));
                mTimePicker.show();
            }
        });

        mAddEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((TextUtils.isEmpty(shiftDate.getText().toString()))) {
                    shiftDate.setError(getString(R.string.missing_date));
                } else if ((TextUtils.isEmpty(shiftTime.getText().toString()))) {
                    shiftTime.setError(getString(R.string.missing_time));
                } else {
                    DBHelper db = new DBHelper(getBaseContext());
                    newShift = new Shift();

                    extras = getIntent().getExtras();
                    location_id = extras.getLong("location_id");
                    //user_id = extras.getLong("user_id");

                    newShift.setDate(shiftDate.getText().toString());
                    newShift.setTime(shiftTime.getText().toString());
                    newShift.setEndTime(shiftEndTime.getText().toString());
                    newShift.setLocation_id(location_id);

                    db.newShift(newShift);
                    finish();
                }
            }
        });
    }

    private void updateDateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);

        shiftDate.setText(sdf.format(myCalendar.getTime()));
    }

}

