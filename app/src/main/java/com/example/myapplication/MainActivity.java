package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    protected BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ツールバー
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //bottomNavigation
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(MainActivity.this);

        //リストビューの処理
        ListView listView = findViewById(R.id.list_contact);
        final ArrayList<personData> list = new ArrayList<>();

        //テストデータ
        //データ数が合わないとOutOfIndex
        final String[] names = {"山田　太郎", "佐藤　健二", "田中　弘樹", "金城 興亜", "荒井 和久",
                                "森本 秋徳", "石川 憲広", "水谷 数也", "岸 一智", "広瀬 優香"};
        final String[] subNames = {"やまだ　たろう", "さとう　けんじ", "たなか　ひろき", "かねしろ　きょうあ", "あらい　かずひさ",
                                    "もりもと　あきのり", "いしかわ　のりひろ", "みずたに　かずや", "きし　かずとも", "ひろせ　ゆうか"};
        final String[] phoneNumbers = {"08011112222", "09033334444", "08055556666", "09022508132", "09043726244",
                                        "09085241551", "09085241551","08023781536", "09043785321", "07012215676"};
        final String[] email = {"akitisoy0530@example.ne.jp", "sigekimaekawa@dion.ne.jp", "mnm1992@example.ad.jp", "mmt9153@goo.ne.jp", "utumi-tomoko@coara.or.jp",
                                "oigut1980@web.ad.jp", "sangi-tatuo@example.net", "sangi-tatuo@example.net", "aynik1209@nifty.com", "yuukahirose@dion.ne.jp"};
        final String[] company = {"ABC株式会社", "ABC株式会社", "ABC株式会社", "ABC株式会社", "ABC株式会社",
                                    "EFG株式会社", "EFG株式会社", "EFG株式会社", "HIJ株式会社", "KLM株式会社"};

        for(int i = 0; i < names.length ; i++){
            personData items = new personData();
            items.setName(names[i]);
            items.setSubName(subNames[i]);
            items.setPhoneNumber(phoneNumbers[i]);
            items.setEmail(email[i]);
            items.setCompany(company[i]);
            list.add(items);
        }
        //List adapter
        ListAdapter adapter = new ListAdapter(MainActivity.this, 0, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), updatePersonData.class);
                intent.putExtra("name", list.get(position).name);
                intent.putExtra("subName", list.get(position).subName);
                intent.putExtra("phoneNumber", list.get(position).phoneNumber);
                intent.putExtra("email", list.get(position).email);
                intent.putExtra("company", list.get(position).company);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add: {
                Intent intent = new Intent(getApplicationContext(), addPersonData.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_delete:{
                // none
                return true;
            }
            case R.id.action_call:{
                // none
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


}
