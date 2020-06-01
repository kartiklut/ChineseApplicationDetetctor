package com.example.checkchineseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    Button button;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayList<String> arrayListmain;
    String arr[]={"CheckChineseApplication","jwt","flutterapp","UC Turbo","Helo","SHAREit","TikTok","Likee","Kwai","UC Browser","UC Mini","LiveMe","BIGO LIVE","Vigo Video","BeautyPlus","Xender","CamScanner","PUBG MOBILE","PUBG MOBILE LITE","Clash of Kings","Mobile Legends","Club Factory","SHEIN","AppLock","Game of Sultans","Mafia City","Battle of Empires"};
    ArrayList<String> fullyqualifiedlist;
    ArrayList<HashMap> mapArrayList;
    ArrayList<HashMap> mapArrayList_main;
    HashMap<String,String> lmap;
    HashMap<String,String> lmap_main;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        listView=findViewById(R.id.simpleListView);

        mapArrayList=new ArrayList<>();
        mapArrayList_main=new ArrayList<>();
        lmap=new HashMap<>();
        lmap_main=new HashMap<>();
        fullyqualifiedlist=new ArrayList<>();
        arrayList=new ArrayList<>();
        arrayListmain=new ArrayList<>();
        List<PackageInfo> packagelist=getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packagelist.size();i++){
            PackageInfo packageInfo=packagelist.get(i);
            if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)==0){
                String appname=packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                String packages = packageInfo.applicationInfo.packageName;
                arrayList.add(appname);
                lmap.put(appname,packages);
                mapArrayList.add(lmap);
                // Log.d("package",appname);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, arrayList);
                listView.setAdapter(arrayAdapter);




                Log.d("package",packages);
            }
        }



    }



    public void check(View view) {

        for(Map.Entry<String,String> entry:lmap.entrySet()){
                for(int i=0;i<arr.length;i++)
                {
                    if((entry.getKey()).equalsIgnoreCase(arr[i]))
                    {
                        arrayListmain.add(entry.getKey());
                        lmap_main.put(entry.getKey(),entry.getValue());
                        mapArrayList_main.add(lmap_main);
                    }
                }
        }
        if(mapArrayList_main.isEmpty()){
            Toasty.success(this,"No Chinese Application ", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,NotFoundActivity.class);
            startActivity(intent);
        }
        else
        {
            Toasty.warning(this,"Found Chinese Application",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,FoundActivity.class);
            intent.putStringArrayListExtra("list",arrayListmain);
            intent.putExtra("MapArrayList",mapArrayList_main);
           // Log.d("size is : ",Integer.toString(fullyqualifiedlist.size()));
            startActivity(intent);
        }
    }

}
