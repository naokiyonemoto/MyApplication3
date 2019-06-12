package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ツールバー
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //bottomNavigation
        BottomNavigationView bottomNavigation;
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(MainActivity.this);

        //リストビューの処理
        final ListView personList = findViewById(R.id.list_contact);

        //DB
        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("person_data", null, null, null, null, null, null);

        List<PersonData> list = new ArrayList<>();
 //       getPersonData(cursor,list);

        PersonData person = new PersonData();
        while (cursor.moveToNext()) {
            person.setName(cursor.getString(cursor.getColumnIndex("name")));
            person.setSubName(cursor.getString(cursor.getColumnIndex("sub_name")));
            person.setPhoneNumber(cursor.getString(cursor.getColumnIndex("phone_number")));
            person.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            person.setCompany(cursor.getString(cursor.getColumnIndex("company")));
            list.add(person);
        }

        db.close();


        personList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                enableWaitHandler(1000L, personList);
                Intent intent = new Intent(getApplicationContext(), UpdatePersonDataActivity.class);

//                intent.putExtra("name", list.get(position).getName());
//                intent.putExtra("subName", list.get(position).getSubName());
//                intent.putExtra("phoneNumber", list.get(position).getPhoneNumber());
//                intent.putExtra("email", list.get(position).getEmail());
//                intent.putExtra("company", list.get(position).getCompany());
                startActivity(intent);
            }
        });

    }

    //BottomNavigationView
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add: {
                Intent intent = new Intent(getApplicationContext(), AddPersonDataActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_delete: {
                //
                return true;
            }
            case R.id.action_call: {
                //
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void enableWaitHandler(long stopTime, final View view) {

        view.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, stopTime);
    }

//    private List<PersonData> getDBData(Cursor cursor,List<PersonData> list){
//        PersonData person = new PersonData();
//        while (cursor.moveToNext()){
//            person.setName(cursor.getString(cursor.getColumnIndex("name")));
//            person.setSubName(cursor.getString(cursor.getColumnIndex("sub_name")));
//            person.setPhoneNumber(cursor.getString(cursor.getColumnIndex("phone_number")));
//            person.setEmail(cursor.getString(cursor.getColumnIndex("email")));
//            person.setCompany(cursor.getString(cursor.getColumnIndex("company")));
//            list.add(person);
//
//        }
//        return list;
//    }
}
