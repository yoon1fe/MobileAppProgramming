package com.example.lectureroomReservation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.registration.R;

public class ReserveActivity extends AppCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        if(findViewById(R.id.frameLayout) != null){
            if(savedInstanceState != null)
                return;
        }

        Schedule_324Fragment idf = new Schedule_324Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, idf).commit();
    }

    public void clickFragment(View view){
        Fragment fr = null;
        switch(view.getId()){
            case R.id.btn_324:
                fr = new Schedule_324Fragment();
                break;
            case R.id.btn_342:
                fr = new Schedule_342Fragment();
                break;
            case R.id.btn_345:
                fr = new Schedule_345Fragment();
                break;
            case R.id.btn_348:
                fr = new Schedule_348Fragment();
                break;
            case R.id.btn_351:
                fr = new Schedule_351Fragment();
                break;
        }
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fr);
        ft.addToBackStack(null);
        ft.commit();
    }
}
