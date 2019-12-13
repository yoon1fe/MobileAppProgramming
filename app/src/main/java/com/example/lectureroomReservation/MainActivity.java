package com.example.lectureroomReservation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.registration.R;


public class MainActivity extends AppCompatActivity {

    DatabaseOpenHelper helper;
    SQLiteDatabase database;
    String sql;
    Cursor cursor;
    int version = 1;

    Button Reservebtn;
    Button Confirmbtn;
    Button Etcbtn;
    Button Noticebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Reservebtn = findViewById(R.id.reserveButton);
        Confirmbtn = findViewById(R.id.confirmButton);
        Etcbtn = findViewById(R.id.cancelButton);
        Noticebtn = findViewById(R.id.noticeButton);


        Reservebtn.setOnClickListener(new View.OnClickListener(){           //예약하기 버튼
            @Override
            public void onClick(View v) {
                Intent reserve_Intent = new Intent(MainActivity.this, ReserveActivity.class);
                MainActivity.this.startActivity(reserve_Intent);
            }
        });

        Confirmbtn.setOnClickListener(new View.OnClickListener(){           //예약확인 버튼
            @Override
            public void onClick(View v)
            {
                Intent confirm_Intent = new Intent(MainActivity.this, ConfirmActivity.class);
                MainActivity.this.startActivity(confirm_Intent);
            }
        });

        Etcbtn.setOnClickListener(new View.OnClickListener(){           // 버튼
            @Override
            public void onClick(View v)
            {
                Intent notice_Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://lms.knu.ac.kr"));
                startActivity(notice_Intent);
            }
        });
        Noticebtn.setOnClickListener(new View.OnClickListener(){           //공지사항
            @Override
            public void onClick(View v) {
                Intent notice_Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://cse.knu.ac.kr/06_sub/02_sub.html"));
                startActivity(notice_Intent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        helper = new DatabaseOpenHelper(this, DatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();
        //cursor.moveToFirst();

        System.out.println("현재 접속 user의 conn = 0으로 바꿔주기");
        sql = "SELECT * FROM " + helper.tableName + " WHERE conn = 1";
        cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();

        if(cursor.getCount() != 0)
        {
            String id = cursor.getString(0);
            String pw = cursor.getString(1);
            int conn = cursor.getInt(2);
            System.out.println(id + " , " + pw + " , " + conn);

            helper.updateUser(database, id, pw, 0);
        }
        else
            System.out.println("conn = 1 인 user가 없음");


        super.onBackPressed();
    }


}
