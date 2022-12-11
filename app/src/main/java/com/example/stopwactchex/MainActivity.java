package com.example.stopwactchex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtTeam , edtName;
    private Button btnSave;
    private boolean save =false;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static final String NAME = "name";
    public static final String TEAM = "team";
    private boolean FLAG = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        setUpSharedPrefs();
        checkData();



    }

    private void checkData() {
        boolean f = prefs.getBoolean("FLAG", false);
        if (f) {
            String name = prefs.getString(NAME, "");
            String team = prefs.getString(TEAM, "");
            edtName.setText(name);
            edtTeam.setText(team);
        }
    }


        public void setUpSharedPrefs() {
            prefs = PreferenceManager.getDefaultSharedPreferences(this);
            editor = prefs.edit();

        }

        private void setUpViews() {
            edtName = findViewById(R.id.edtName);
            edtTeam = findViewById(R.id.edtTeam);
            btnSave = findViewById(R.id.btnSave);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    save = true;
                }
            });
        }


        @Override
        protected void onStop() {
            super.onStop();
            if (!save){
                String name = edtName.getText().toString().trim();
                String team = edtTeam.getText().toString();

                editor.putString(NAME, name);
                editor.putString(TEAM,team);
                editor.putBoolean("FLAG",FLAG);

                editor.commit();


            }

        }
    }