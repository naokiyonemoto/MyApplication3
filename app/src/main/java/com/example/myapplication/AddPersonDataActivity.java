package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPersonDataActivity extends AppCompatActivity {
    //連絡先の追加

    private EditText editName;
    private EditText editSubName;
    private EditText editPhoneNumber;
    private EditText editEmail;
    private EditText editCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        editName = findViewById(R.id.edit_name);
        editSubName = findViewById(R.id.edit_name_sub);
        editPhoneNumber = findViewById(R.id.edit_phone_number);
        editEmail = findViewById(R.id.edit_email);
        editCompany = findViewById(R.id.edit_company);


        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personDataSave();
            }
        });

        Log.d("EnterNameLog", editName.getText().toString());

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //none
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //none
            }

            @Override
            public void afterTextChanged(Editable s) {
                String nameStr = s.toString();
                if (nameStr.length() >= 1) {
                    findViewById(R.id.name_check).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.name_check).setVisibility(View.VISIBLE);
                }

            }
        });

        editPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //none
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //none
            }

            @Override
            public void afterTextChanged(Editable s) {
                String nameStr = s.toString();
                if (nameStr.length() >= 1) {
                    findViewById(R.id.phone_check).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.phone_check).setVisibility(View.VISIBLE);
                }
            }
        });

        //仮：非表示→表示
        findViewById(R.id.button_add).setEnabled(true);
        //TODO:名前と電話番号は確実に入れさせるようにしたい（空の禁止）
        //TODO:editTextの変更された際に※表示、非表示と追加ボタンを有効にしたい
//        if(!(editName.getText().toString().equals("") && editPhoneNumber.getText().toString().equals(""))){
//            findViewById(R.id.button_add).setEnabled(true);
//        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void personDataSave() {
        //DBに保存する
        Log.d("EnterNameLog", editName.getText().toString());
        Log.d("EnterSubNameLog", editSubName.getText().toString());

        DatabaseHelper helper = new DatabaseHelper(AddPersonDataActivity.this);
        SQLiteDatabase db = null;

        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            String sql = "INSERT INTO person_data (name, sub_name, phone_number, email, company) VALUES (?, ?, ?, ?, ?)";

            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, editName.getText().toString());
            statement.bindString(2, editSubName.getText().toString());
            statement.bindString(3, editPhoneNumber.getText().toString());
            statement.bindString(4, editEmail.getText().toString());
            statement.bindString(5, editCompany.getText().toString());

            statement.executeInsert();

            db.setTransactionSuccessful();
            Toast.makeText(this, "登録に成功しました", Toast.LENGTH_SHORT).show();
        } catch (SQLiteException e) {
            Toast.makeText(this, "登録に失敗しました", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }

        Intent intent = new Intent();


        finish();
    }

}
