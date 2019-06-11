package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class updatePersonData extends AppCompatActivity {

    //個別連絡先画面の内容,変更できたりする
    private personData data;
    private EditText editName;
    private EditText editSubName;
    private EditText editPhoneNumber;
    private EditText editEmail;
    private EditText editCompany;

    //更新の処理ができてない

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_updata_person);
        //
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


//        Bundle bundle = getIntent().getExtras();
//        personData data = (personData)bundle.getSerializable("DATA_MODEL");

        Intent intent = getIntent();
        String selectedName = intent.getStringExtra("name");
        String selectedSubName = intent.getStringExtra("subName");
        String selectedPhoneNumber = intent.getStringExtra("phoneNumber");
        String selectedEmail = intent.getStringExtra("email");
        String selectedCompany = intent.getStringExtra("company");


        editName = findViewById(R.id.edit_name);
        editSubName = findViewById(R.id.edit_name_sub);
        editPhoneNumber = findViewById(R.id.edit_phone_number);
        editEmail = findViewById(R.id.edit_email);
        editCompany = findViewById(R.id.edit_company);

//        editName.setText(data.getName());
//        editSubName.setText(data.getSubName());
//        editPhoneNumber.setText(data.getPhoneNumber());

        editName.setText(selectedName);
        editSubName.setText(selectedSubName);
        editPhoneNumber.setText(selectedPhoneNumber);
        editEmail.setText(selectedEmail);
        editCompany.setText(selectedCompany);

        findViewById(R.id.button_updata).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新ボタンの処理
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

}