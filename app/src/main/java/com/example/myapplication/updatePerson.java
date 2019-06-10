package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class updatePerson extends AppCompatActivity {

    //個別連絡先画面の内容,変更できたりする
    private personData data;
    private EditText editName;
    private EditText editSubName;
    private EditText editPhoneNumber;
    private EditText editEmail;
    private EditText editCompany;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update_person);
        Bundle bundle = getIntent().getExtras();

//        personData data = (personData)bundle.getSerializable("DATA_MODEL");

        Intent intent = getIntent();
        String selectedName = intent.getStringExtra("name");
        String selectedSubName = intent.getStringExtra("subName");
        String selectedPhoneNumber = intent.getStringExtra("phoneNumber");

        editName = findViewById(R.id.edit_name);
        editSubName = findViewById(R.id.edit_name_sub);
        editPhoneNumber = findViewById(R.id.edit_phone_number);

//        editName.setText(data.getName());
//        editSubName.setText(data.getSubName());
//        editPhoneNumber.setText(data.getPhoneNumber());

        editName.setText(selectedName);
        editSubName.setText(selectedSubName);
        editPhoneNumber.setText(selectedPhoneNumber);

    }


}