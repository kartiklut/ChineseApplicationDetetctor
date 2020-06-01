package com.example.checkchineseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.media.CamcorderProfile.get;

public class FoundActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;
    String size;
    List<PackageInfo> packagelist;
    String packages="";
    Uri packageURI;
    ArrayList<HashMap<String, String>> mapArrayList;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        textView=findViewById(R.id.textview1);
        listView=findViewById(R.id.listView);
        Intent intent=getIntent();
        arrayList=intent.getStringArrayListExtra("list");

        mapArrayList  = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("MapArrayList");


        size=Integer.toString(arrayList.size());
        String msg=size +" Chinese App found \n on your Device.";
        textView.setText(msg);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, arrayList);
        listView.setAdapter(arrayAdapter);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item_name=(String) parent.getItemAtPosition(position);
                for (Map<String, String> entry : mapArrayList) {
                    for (String key : entry.keySet()) {
                        String package_name= entry.get(key);
                        if(item_name.equalsIgnoreCase(key)){
                            packages=package_name;
                        }
                    }
                }




                packageURI = Uri.parse("package:"+packages);
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
                startActivity(uninstallIntent);
            }
        });


    }
@Override
public  void onBackPressed()
{

}


}
