package com.edu.sicnu.cs.zzy.datastorage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_SharedPreferences;
    private Button btn_File;
    private Button btn_ExternalFile;
    private Button btn_DataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }



        btn_SharedPreferences = findViewById(R.id.btn_SharedPreferences);
        btn_File = findViewById(R.id.btn_File);
        btn_ExternalFile = findViewById(R.id.btn_externalFile);
        btn_DataBase = findViewById(R.id.btn_DataBase);

        btn_DataBase.setOnClickListener(this);
        btn_ExternalFile.setOnClickListener(this);
        btn_File.setOnClickListener(this);
        btn_SharedPreferences.setOnClickListener(this);
    }

    //要求必须允许开启权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_SharedPreferences:
                intent = new Intent(MainActivity.this,SharedPreferencesActivity.class);
                break;
            case R.id.btn_File:
                intent = new Intent(MainActivity.this,FileActivity.class);
                break;
            case R.id.btn_externalFile:
                intent = new Intent(MainActivity.this,FileActivity.class);
                break;
            case R.id.btn_DataBase:
                intent = new Intent(MainActivity.this,DataBaseActivity.class);
                break;
        }
        startActivity(intent);
    }
}
