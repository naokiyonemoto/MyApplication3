package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddPersonDataActivity extends AppCompatActivity {


    private EditText editName;
    private EditText editSubName;
    private EditText editPhoneNumber;
    private EditText editEmail;
    private EditText editCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persondata);

        editName = findViewById(R.id.edit_name);
        editSubName = findViewById(R.id.edit_name_sub);
        editPhoneNumber = findViewById(R.id.edit_phone_number);
        editEmail = findViewById(R.id.edit_email);
        editCompany = findViewById(R.id.edit_company);


        findViewById(R.id.button_updata).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void save() {
        //TODO：DBに保存したい
        Log.d("EnterNameLog", editName.getText().toString());
        Log.d("EnterSubNameLog", editSubName.getText().toString());

        DatabaseHelper helper = new DatabaseHelper(AddPersonDataActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        //TODO：登録しました的なダイアログを出したい
        //アラートダイアログを使う
        finish();
    }

}
