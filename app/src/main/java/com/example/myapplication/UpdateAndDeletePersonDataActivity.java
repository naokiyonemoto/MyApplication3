package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAndDeletePersonDataActivity extends AppCompatActivity implements DeleteDialogFragment.FragmentResultListener {
    //個別連絡先画面の内容の変更と削除

    private EditText editName;
    private EditText editSubName;
    private EditText editPhoneNumber;
    private EditText editEmail;
    private EditText editCompany;
    private String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_and_delete_person);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        editName = findViewById(R.id.edit_name);
        editSubName = findViewById(R.id.edit_name_sub);
        editPhoneNumber = findViewById(R.id.edit_phone_number);
        editEmail = findViewById(R.id.edit_email);
        editCompany = findViewById(R.id.edit_company);

        Intent intent = getIntent();
        id = intent.getStringExtra("_id");
        String selectedName = intent.getStringExtra("name");
        String selectedSubName = intent.getStringExtra("subName");
        String selectedPhoneNumber = intent.getStringExtra("phoneNumber");
        String selectedEmail = intent.getStringExtra("email");
        String selectedCompany = intent.getStringExtra("company");

        editName.setText(selectedName);
        editSubName.setText(selectedSubName);
        editPhoneNumber.setText(selectedPhoneNumber);
        editEmail.setText(selectedEmail);
        editCompany.setText(selectedCompany);

        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新ボタンの処理
                update();
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //削除ボタンの処理
                DeleteDialogFragment deleteDialogFragment = DeleteDialogFragment.newInstance(UpdateAndDeletePersonDataActivity.this);
                deleteDialogFragment.show(getSupportFragmentManager(), "DeleteDialogFragment");

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentResult() {
        delete();
    }

    private void update() {
        DatabaseHelper helper = new DatabaseHelper(UpdateAndDeletePersonDataActivity.this);
        SQLiteDatabase db = null;

        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            String sql = "UPDATE person_data SET " +
                    "name = ?," +
                    "sub_name = ?," +
                    "phone_number = ?," +
                    "email = ?," +
                    "company = ?" +
                    " WHERE _id = " + id;

            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, editName.getText().toString());
            statement.bindString(2, editSubName.getText().toString());
            statement.bindString(3, editPhoneNumber.getText().toString());
            statement.bindString(4, editEmail.getText().toString());
            statement.bindString(5, editCompany.getText().toString());

            statement.executeInsert();

            db.setTransactionSuccessful();
            Toast.makeText(UpdateAndDeletePersonDataActivity.this, "更新しました", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(UpdateAndDeletePersonDataActivity.this, "更新に失敗しました", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }

    }

    private void delete() {
        //TODO:削除確認ダイアログを追加する

        DatabaseHelper helper = new DatabaseHelper(UpdateAndDeletePersonDataActivity.this);
        SQLiteDatabase db = null;

        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            String sql = "DELETE FROM person_data WHERE _id = " + id;

            SQLiteStatement statement = db.compileStatement(sql);
            statement.executeInsert();

            db.setTransactionSuccessful();

            Toast.makeText(UpdateAndDeletePersonDataActivity.this, "削除しました", Toast.LENGTH_SHORT).show();
            finish();
        } catch (SQLiteException e) {
            Toast.makeText(UpdateAndDeletePersonDataActivity.this, "削除に失敗しました", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }


    }

}
