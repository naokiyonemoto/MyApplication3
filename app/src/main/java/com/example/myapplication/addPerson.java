package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class addPerson extends AppCompatActivity {

    private personData data;
    private EditText editName;
    private EditText editSubName;
    private EditText editPhoneNumber;
    private EditText editEmail;
    private EditText editCompany;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create_person);

        editName = findViewById(R.id.edit_name);
        editSubName = findViewById(R.id.edit_name_sub);
        editPhoneNumber = findViewById(R.id.edit_phone_number);
        editEmail = findViewById(R.id.edit_email);
        editCompany = findViewById(R.id.edit_company);

//        findViewById(R.id.button_create).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                save();
//            }
//        });
    }

    private void save(){
        data.setName(editName.getText().toString());
        data.setSubName(editSubName.getText().toString());
        data.setPhoneNumber(editPhoneNumber.getText().toString());
        data.setEmail(editEmail.getText().toString());
        data.setCompany(editCompany.getText().toString());

        DataUtil.save(addPerson.this, data);
    }

}
