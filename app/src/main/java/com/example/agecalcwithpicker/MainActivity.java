package com.example.agecalcwithpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView result;
    Button birthDate;
    Button currentDate;
    Button calculate;
    DatePickerDialog.OnDateSetListener dateSetListener1, dateSetListener2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);
        birthDate = (Button) findViewById(R.id.birthDate);
        currentDate = (Button) findViewById(R.id.currentDate);
        calculate = (Button) findViewById(R.id.calculate);

        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
        //String date = simpleDateFormat.format(Calendar.getInstance().getTime());
        //currentDate.setText(date);

        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener1, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new
                        ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                birthDate.setText(date);


            }

        };


        currentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener2, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new
                        ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String sdate = day + "/" + month + "/" + year;

                currentDate.setText(sdate);
            }
        };

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bdate = birthDate.getText().toString();
                String cdate = currentDate.getText().toString();
                SimpleDateFormat simpleDateFormat1= new SimpleDateFormat("dd/mm/yyyy");
                try {
                    Date date1= simpleDateFormat1.parse(bdate);
                    Date date2= simpleDateFormat1.parse(cdate);
                    long startDate = date1.getTime();
                    long endDate= date2.getTime();

                    if (startDate <= endDate)
                    {
                        Period period= new Period(startDate , endDate, PeriodType.yearMonthDay());
                        int year = period.getYears();
                        int months = period.getMonths();
                        int days = period.getDays();

                        result.setText(year+" Years | "+months+" Months | "+days+" Days");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),
                        "Birth Date should not be greater than Today's Date.", Toast.LENGTH_SHORT).show();
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    }