package com.example.checkchineseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoundActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        textView=findViewById(R.id.textview1);
        listView=findViewById(R.id.listView);
        Intent intent=getIntent();
        ArrayList<String> arrayList=intent.getStringArrayListExtra("list");
        String size=Integer.toString(arrayList.size());
        String msg=size +" Chinese App found \n on your Device.";
        textView.setText(msg);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, arrayList);
        listView.setAdapter(arrayAdapter);

    }
    @Override
    public void onBackPressed() {

    }
}
