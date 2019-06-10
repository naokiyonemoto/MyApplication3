package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ツールバー
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //リストビューの処理
        ListView listView = findViewById(R.id.list_contact);
        final ArrayList<personData> list = new ArrayList<>();

        //テストデータ
//        final String[] names = {"山田　太郎", "佐藤　健二", "田中　弘樹"};
//        final String[] subNames = {"やまだ　たろう", "さとう　けんじ", "たなか　ひろき"};
//        final String[] phoneNumbers = {"08011112222", "09033334444", "08055556666"};
//
//        for(int i = 0; i < names.length ; i++){
//            personData items = new personData();
//            items.name = names[i];
//            items.subName = subNames[i];
//            items.phoneNumber = phoneNumbers[i];
//            list.add(items);
//        }
        //List adapter

        ListAdapter adapter = new ListAdapter(MainActivity.this, 0, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), updatePerson.class);
//                intent.putExtra("name", list.get(position).name);
//                intent.putExtra("subName",list.get(position).subName);
//                intent.putExtra("phoneNumber",list.get(position).phoneNumber);

                startActivity(intent);
            }
        });

        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addPerson.class);
                startActivity(intent);
            }
        });

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


}
