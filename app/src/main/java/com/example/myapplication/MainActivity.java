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

        //DB
        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("person_data", null, null, null, null, null, null);
        cursor.moveToFirst();

        //リストビューの処理
        final ListView personList = findViewById(R.id.list_contact);
//        personList.setAdapter(new SimpleCursorAdapter(this,R.layout.listview_layout,cursor,
//                new String[]{"name", "sub_name", "phone_number"},
//                new int[]{R.id.text_name, R.id.text_sub_name, R.id.text_number}));

//        final ArrayList<PersonData> list = new ArrayList<>();

        //TODO:ファイルからデータを読み込んでリストに入れ込みたい
        //データを複数件入れると、データを上書きしていく（1件だけになる？)
//        PersonData data = (PersonData)DataUtil.load(this); //読み込み
//        Log.d("MainActivity:data", data.getName());
//        Log.d("MainActivity:data", data.getCompany());


//            PersonData items = new PersonData();
//            items.setName(data.getName());
//            list.add(items);


        //テストデータ
//        final String[] names = {"山田　太郎", "佐藤　健二", "田中　弘樹", "金城 興亜", "荒井 和久",
//                "森本 秋徳", "石川 憲広", "水谷 数也", "岸 一智", "広瀬 優香"};
//        final String[] subNames = {"やまだ　たろう", "さとう　けんじ", "たなか　ひろき", "かねしろ　きょうあ", "あらい　かずひさ",
//                "もりもと　あきのり", "いしかわ　のりひろ", "みずたに　かずや", "きし　かずとも", "ひろせ　ゆうか"};
//        final String[] phoneNumbers = {"08011112222", "09033334444", "08055556666", "09022508132", "09043726244",
//                "09085241551", "09085241551", "08023781536", "09043785321", "07012215676"};
//        final String[] email = {"akitisoy0530@example.ne.jp", "sigekimaekawa@dion.ne.jp", "mnm1992@example.ad.jp", "mmt9153@goo.ne.jp", "utumi-tomoko@coara.or.jp",
//                "oigut1980@web.ad.jp", "sangi-tatuo@example.net", "sangi-tatuo@example.net", "aynik1209@nifty.com", "yuukahirose@dion.ne.jp"};
//        final String[] company = {"ABC株式会社", "ABC株式会社", "ABC株式会社", "ABC株式会社", "ABC株式会社",
//                "EFG株式会社", "EFG株式会社", "EFG株式会社", "HIJ株式会社", "KLM株式会社"};
//
//        for (int i = 0; i < names.length; i++) {
//            PersonData items = new PersonData();
//            items.setName(names[i]);
//            items.setSubName(subNames[i]);
//            items.setPhoneNumber(phoneNumbers[i]);
//            items.setEmail(email[i]);
//            items.setCompany(company[i]);
//            list.add(items);
//        }

        //List adapter
//        ListViewAdapter adapter = new ListViewAdapter(MainActivity.this, 0, list);
//        personList.setAdapter(adapter);


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

}
