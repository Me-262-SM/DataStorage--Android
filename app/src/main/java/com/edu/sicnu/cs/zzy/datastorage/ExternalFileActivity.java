package com.edu.sicnu.cs.zzy.datastorage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalFileActivity extends AppCompatActivity {
    private Button btn_save;
    private Button btn_load;
    private EditText et_name;
    private TextView tv_show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_file);

        btn_save = findViewById(R.id.btn_save);
        btn_load = findViewById(R.id.btn_load);
        et_name = findViewById(R.id.et_name);
        tv_show = findViewById(R.id.tv_show);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(et_name.getText().toString());
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_show.setText(read());
            }
        });
    }


    private void save (String string){
        FileOutputStream fileOutputStream = null;
        try {
            //fileOutputStream = openFileOutput("test.txt",MODE_PRIVATE);

            File dir = new File(Environment.getExternalStorageDirectory(),"zzy");
            if(!dir.exists()){
                dir.mkdirs();
            }

            File file = new File(dir,"test.txt");
            if(!file.exists()){
                file.createNewFile();
            }

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(string.getBytes());
        }  catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String read()  {
        FileInputStream fileInputStream = null;
        try {
            //fileInputStream = openFileInput("test.txt");
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"zzy","test.txt");

            fileInputStream = new FileInputStream(file);
            byte[] buff = new byte[1024];
            StringBuilder stringBuilder = new StringBuilder("");
            int len = 0;
            while((len = fileInputStream.read(buff)) > 0){
                stringBuilder.append(new String(buff,0,len));
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
