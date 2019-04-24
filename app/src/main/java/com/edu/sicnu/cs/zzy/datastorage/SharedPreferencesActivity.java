package com.edu.sicnu.cs.zzy.datastorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferencesActivity extends AppCompatActivity {
    private Button btn_save;
    private Button btn_load;
    private EditText et_name;
    private TextView tv_show;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        btn_save = findViewById(R.id.btn_save);
        btn_load = findViewById(R.id.btn_load);
        et_name = findViewById(R.id.et_name);
        tv_show = findViewById(R.id.tv_show);

        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("name",et_name.getText().toString());
                editor.apply();
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_show.setText(sharedPreferences.getString("name","There is nothng"));
            }
        });
    }
}
