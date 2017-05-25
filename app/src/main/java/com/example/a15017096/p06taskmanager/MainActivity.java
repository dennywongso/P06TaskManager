package com.example.a15017096.p06taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button btnAdd;
    int code = 1;
    ArrayList<Task> al;
    ArrayAdapter<Task> adapter;
    dbHelper db;
    AlarmManager am;
    int requestCode = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv =(ListView)findViewById(R.id.lvTask);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        db = new dbHelper(MainActivity.this);
        al = db.getAllTasks();

        adapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_2, android.R.id.text1, al){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText((position+1)+" "+al.get(position).getName());
                text2.setText(al.get(position).getDesc());
                return view;
            }
        };
        lv.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),AddActivity.class);
                startActivityForResult(intent,code);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if (data != null) {
                if(requestCode == code){
                    al = db.getAllTasks();
                    adapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_2, android.R.id.text1, al){
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                            TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                            text1.setText((position+1)+" "+al.get(position).getName());
                            text2.setText(al.get(position).getDesc());
                            return view;
                        }
                    };
                    lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

}
