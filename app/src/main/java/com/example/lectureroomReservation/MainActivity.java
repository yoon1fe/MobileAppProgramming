package com.example.lectureroomReservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.registration.R;

public class MainActivity extends AppCompatActivity {

    Button Reservebtn;

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


    }
}
