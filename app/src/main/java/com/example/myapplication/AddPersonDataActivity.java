package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        //DBに保存する
        //全部上書きされる
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
        }catch (Exception e){
            //エラー時
            Toast.makeText(this, "登録に失敗しました", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }finally {
            if(db != null){
                db.endTransaction();
                db.close();
            }
        }
//        ContentValues values = new ContentValues();
//        values.put("name", editName.getText().toString());
//        values.put("sub_name", editSubName.getText().toString());
//        values.put("phone_number", editPhoneNumber.getText().toString());
//        values.put("email", editEmail.getText().toString());
//        values.put("company", editCompany.getText().toString());
//        long ret;
//        try{
//            ret = db.insert("person_data", null, values);
//        }finally {
//            db.close();
//        }

//            if (ret == -1) {
//                Toast.makeText(this, "登録に失敗しました", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "登録に成功しました", Toast.LENGTH_SHORT).show();
//            }

            Log.d("addDB", "insert column");
            finish();
        }

    }
