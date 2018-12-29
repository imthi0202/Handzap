package handzap.handzap;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText vtitle, vdescrip, vRate, vPayment, vJobTerm, vStartDate;
    TextView vtitleLeft, vdescripLeft;
    AlertDialog alertDialog1, alertDialog2, alertDialog3;
    ImageView PostAttach;
    CharSequence[] values = {" No Preference ", " Fixed Budget ", " Hourly Rate "};
    CharSequence[] Payvalues = {" No Preference ", " E-Payment ", " Cash "};
    CharSequence[] JobTermvalues = {" Recurring Job ", " Same Day Job ", " Multi Days Job "};

    private int mYear, mYear1;
    private int mMonth, mMonth1;
    private int mDay, mDay1;
    static final int DATE_DIALOG_ID = 0;
    StringBuilder dat, dat1;
    String dates, dates1;
    String finalDate, finalDate1;
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vtitle = findViewById(R.id.editText00);
        vdescrip = findViewById(R.id.editText22);
        vtitleLeft = findViewById(R.id.textView3);
        vdescripLeft = findViewById(R.id.textView4);

        vRate = findViewById(R.id.edit_rate);
        vPayment = findViewById(R.id.edit_pay);
        vJobTerm = findViewById(R.id.edit_jobterm);
        vStartDate = findViewById(R.id.edit_startdate);
        PostAttach = findViewById(R.id.imageView);

        InitializViewId();


    }

    public void InitializViewId() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        //updateDisplay();

        PostAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, AppActivity.class);
                startActivity(in);
            }
        });
        vStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        vRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateRateRadioButtonGroup();

            }
        });
        vPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreatePaymentRadioButtonGroup();

            }
        });
        vJobTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateJobTermRadioButtonGroup();

            }
        });

        vtitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int aft) {
                vtitleLeft.setText("50 characters left");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // this will show characters remaining
                vtitleLeft.setText(50 - s.toString().length() + " characters left");
            }
        });
        vdescrip.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int aft) {
                vdescripLeft.setText("400 characters left");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // this will show characters remaining
                vdescripLeft.setText(400 - s.toString().length() + " characters left");
            }
        });
        vtitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    vtitleLeft.setText("50 characters left");
                    vtitleLeft.setText(50 - vtitle.getText().toString().length() + " characters left");
                }
            }
        });
        vdescrip.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    vdescripLeft.setText("400 characters left");
                    vdescripLeft.setText(400 - vdescrip.getText().toString().length() + " characters left");
                }
            }
        });


    }

    public void CreateRateRadioButtonGroup() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Rate");

        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch (item) {
                    case 0:
                        vRate.setText("No Preference");
                        // Toast.makeText(MainActivity.this, "First Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        vRate.setText("Fixed Budget");
                        //Toast.makeText(MainActivity.this, "Second Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        vRate.setText("Hourly Rate");
                        //Toast.makeText(MainActivity.this, "Third Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                }
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();

    }

    public void CreatePaymentRadioButtonGroup() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Payment Method");

        builder.setSingleChoiceItems(Payvalues, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch (item) {
                    case 0:
                        vPayment.setText("No Preference");
                        // Toast.makeText(MainActivity.this, "First Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        vPayment.setText("E-Payment");
                        //Toast.makeText(MainActivity.this, "Second Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        vPayment.setText("Cash");
                        //Toast.makeText(MainActivity.this, "Third Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                }
                alertDialog2.dismiss();
            }
        });
        alertDialog2 = builder.create();
        alertDialog2.show();

    }

    public void CreateJobTermRadioButtonGroup() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Job Term");

        builder.setSingleChoiceItems(JobTermvalues, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch (item) {
                    case 0:
                        vJobTerm.setText("Recurring Job");
                        // Toast.makeText(MainActivity.this, "First Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        vJobTerm.setText("Same Day Job");
                        //Toast.makeText(MainActivity.this, "Second Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        vJobTerm.setText("Multi Days Job");
                        //Toast.makeText(MainActivity.this, "Third Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                }
                alertDialog3.dismiss();
            }
        });
        alertDialog3 = builder.create();
        alertDialog3.show();

    }

    private void updateDisplay() {

        dat1 = new StringBuilder()
                // Month is 0 based so add 1
                /*
                 * .append(mYear).append("-") .append(mMonth + 1).append("-")
                 * .append(mDay).append(" ");
                 */
                .append(mDay).append("- ")

                .append(mMonth + 1).append("-").append(mYear).append("");
        dates1 = dat1.toString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        // "yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dates1);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy");
        finalDate1 = timeFormat.format(myDate);
        vStartDate.setText(finalDate1);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay) {
                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        if (year > mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (monthOfYear > mMonth && year == mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (dayOfMonth > mDay && year == mYear
                                && monthOfYear == mMonth)
                            view.updateDate(mYear, mMonth, mDay);
                    }
                };


        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_post) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

}
