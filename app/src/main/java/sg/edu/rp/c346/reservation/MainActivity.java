package sg.edu.rp.c346.reservation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etTelephone;
    EditText etSize;
    CheckBox checkBox;
    EditText etDay;
    EditText etTime;
    Button btReserve;
    Button btReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etTelephone = findViewById(R.id.editTextTelephone);
        etSize = findViewById(R.id.editTextSize);
        checkBox = findViewById(R.id.checkBox);
        etDay = findViewById(R.id.editTextDay);
        etTime = findViewById(R.id.editTextTime);
        btReserve = findViewById(R.id.buttonReserve);
        btReset = findViewById(R.id.buttonReset);


        btReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.result, null);
                String isSmoke = "";
                if (checkBox.isChecked()) {
                    isSmoke = "smoking";
                }
                else {
                    isSmoke = "non-smoking";
                }

                final TextView tvName = viewDialog.findViewById(R.id.textViewName);
                final TextView tvSmoking = viewDialog.findViewById(R.id.textViewSmoking);
                final TextView tvSize = viewDialog.findViewById(R.id.textViewSize);
                final TextView tvDate = viewDialog.findViewById(R.id.textViewDate);
                final TextView tvTime = viewDialog.findViewById(R.id.textViewTime);
                final String finalIsSmoke = isSmoke;

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Confirm Your Order");
                tvName.setText(etName.getText().toString());
                tvSmoking.setText(finalIsSmoke);
                tvSize.setText(etSize.getText().toString());
                tvDate.setText(etDay.getText().toString());
                tvTime.setText(etTime.getText().toString());


                myBuilder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,
                                "Hi, " + etName.getText().toString() + ", you have booked a "
                                        + etSize.getText().toString() + " person(s) "
                                        + finalIsSmoke + " table on " + tvDate.getText().toString() + " " + tvTime.getText().toString()
                                        + ". Your phone number is "
                                        + etTelephone.getText().toString() + ".",
                                Toast.LENGTH_LONG).show();
                    }
                });
                myBuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();


            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
                etTelephone.setText("");
                etSize.setText("");
                checkBox.setChecked(false);

            }
        });

        etDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        etDay.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                };

                Calendar cal1 = Calendar.getInstance();
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), cal1.get(Calendar.DAY_OF_MONTH));
                myDateDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etTime.setText(hourOfDay + ":" + minute);
                    }
                };

                Calendar cal1 = Calendar.getInstance();
                // Create the Time Picker Dialog
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener, cal1.get(Calendar.HOUR_OF_DAY), cal1.get(Calendar.MINUTE), true);

                myTimeDialog.show();
            }
        });
    }
}