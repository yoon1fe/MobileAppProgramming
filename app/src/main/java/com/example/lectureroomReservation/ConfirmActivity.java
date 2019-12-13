package com.example.lectureroomReservation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.R;

import java.util.ArrayList;

public class ConfirmActivity extends AppCompatActivity {
    DatabaseOpenHelper helper;
    SQLiteDatabase database;
    String sql;
    Cursor cursor;
    int version = 1;
    ArrayList<String> items;
    ArrayAdapter adapter;
    ListView listview;
    TextView textView;
    String UserId = LoginActivity.UserId;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        items = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items);
        listview = findViewById(R.id.RListView);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setAdapter(adapter);
        textView = findViewById(R.id.info_text);

        helper = new DatabaseOpenHelper(this, DatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        ShowRegistration();

        Button deleteButton = findViewById(R.id.DeleteBtn);
        deleteButton.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int count, checked;
                count = adapter.getCount();

                System.out.println("삭제버튼이 클릭됨");
                System.out.println("count : " + count);

                if (count > 0) {
                    checked = listview.getCheckedItemPosition();

                    System.out.println("checked : " + checked);

                    if (checked > -1 && checked < count) {
                        int temp = 0;
                        //sql = "SELECT * FROM (SELECT * FROM " + helper.tableRegistration +" WHERE id = '" + UserId + "')" + " WHERE ROWNUM = " + temp;
                        sql = "SELECT * FROM " + helper.tableRegistration + " WHERE id = '" + UserId + "'";

                        cursor = database.rawQuery(sql, null);

                        System.out.println("개수: " + cursor.getCount());
                        while (cursor.moveToNext()) {
                            if (temp == checked) {
                                String id = cursor.getString(0);
                                int room = cursor.getInt(1);
                                int day = cursor.getInt(2);
                                int s_time = cursor.getInt(3);
                                int f_time = cursor.getInt(4);

                                switch (room) {
                                    case 324:
                                        for (int i = s_time; i <= f_time; i++) {
                                            helper.deleteRoom_324(database, day, i - 18);
                                        }
                                        break;
                                    case 342:
                                        for (int i = s_time; i <= f_time; i++) {
                                            helper.deleteRoom_342(database, day, i - 18);
                                        }
                                        break;
                                    case 345:
                                        for (int i = s_time; i <= f_time; i++) {
                                            helper.deleteRoom_345(database, day, i - 18);
                                        }
                                        break;
                                    case 348:
                                        for (int i = s_time; i <= f_time; i++) {
                                            helper.deleteRoom_348(database, day, i - 18);
                                        }
                                        break;
                                    case 351:
                                        for (int i = s_time; i <= f_time; i++) {
                                            helper.deleteRoom_351(database, day, i - 18);
                                        }
                                        break;
                                }

                                helper.deleteRegistration(database, id, room, day, s_time, f_time);

                                items.remove(checked);
                                listview.clearChoices();
                                adapter.notifyDataSetChanged();

                                break;
                            } else {
                                temp++;
                            }
                        }

                    }
                }

            }

        });
    }

    void ShowRegistration()
    {
        sql = "SELECT * FROM " + helper.tableRegistration + ", " + helper.tableName + " WHERE Registration.id = " + helper.tableName + ".id and " + helper.tableName + ".conn = 1";
        cursor = database.rawQuery(sql, null);

        String regi_info = null;
        while(cursor.moveToNext())
        {
            System.out.println("regi_info");
            String id = cursor.getString(0);
            int room = cursor.getInt(1);
            int day = cursor.getInt(2);
            int s_time = cursor.getInt(3);
            int f_time = cursor.getInt(4);

            textView.setText(id + "님의 예약현황입니다");

            switch(day)
            {
                case 0: //mon
                    regi_info = "월요일 " + room + "호 " + s_time +":00 - " + f_time + ":00" ;
                    break;
                case 1: //tue
                    regi_info = "화요일 " + room + "호 " + s_time +":00 - " + f_time + ":00" ;
                    break;
                case 2: //wed
                    regi_info = "수요일 " + room + "호 " + s_time +":00 - " + f_time + ":00" ;
                    break;
                case 3: //thu
                    regi_info = "목요일 " + room + "호 " + s_time +":00 - " + f_time + ":00" ;
                    break;
                case 4: //fri
                    regi_info = "금요일 " + room + "호 " + s_time +":00 - " + f_time + ":00" ;
                    break;
            }

            items.add(regi_info);
            adapter.notifyDataSetChanged();
        }
    }


}
