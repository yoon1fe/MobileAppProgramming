package com.example.lectureroomReservation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.registration.R;

public class MainActivity extends AppCompatActivity {

    Button Reservebtn;
    Button Confirmbtn;
    Button Cancelbtn;
    Button Noticebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Reservebtn = (Button)findViewById(R.id.reserveButton);

        Reservebtn.setOnClickListener(new View.OnClickListener(){           //예약하기 버튼
            @Override
            public void onClick(View v) {
                Intent rIntent = new Intent(MainActivity.this, ReserveActivity.class);
                MainActivity.this.startActivity(rIntent);
            }
        });

        Noticebtn = (Button)findViewById(R.id.noticeButton);

        Noticebtn.setOnClickListener(new View.OnClickListener(){           //공지사항
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://cse.knu.ac.kr/06_sub/02_sub.html"));
                startActivity(intent);
            }
        });

    }




}
