package com.example.a15017096.p06taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    EditText etName, etDesc, etDuration;
    Button btnSave, btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etDesc = (EditText)findViewById(R.id.etDescription);
        etName = (EditText)findViewById(R.id.etName);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        etDuration = (EditText)findViewById(R.id.etDuration);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();

                Task newTask = new Task(etName.getText().toString(), etDesc.getText().toString());
                dbHelper db = new dbHelper(AddActivity.this);
                db.insertTask(newTask);
                int requestCode = 123;
                Calendar cal = Calendar.getInstance();
                int duration = Integer.parseInt(etDuration.getText().toString());
                cal.add(Calendar.SECOND, duration);
                Intent intent = new Intent(AddActivity.this, MyReceiver.class);
                intent.putExtra("data",etName.getText().toString());

                PendingIntent pIntent = PendingIntent.getBroadcast(AddActivity.this,
                        requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pIntent);
                setResult(RESULT_OK, i);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
