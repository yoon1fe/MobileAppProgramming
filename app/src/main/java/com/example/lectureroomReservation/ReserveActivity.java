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
                System.out.println("324 fragment");
                fr = new Schedule_324Fragment();
                break;
            case R.id.btn_342:
                fr = new Schedule_342Fragment();
                System.out.println("342 fragment");
                break;
            case R.id.btn_345:
                fr = new Schedule_345Fragment();
                System.out.println("345 fragment");
                break;
            case R.id.btn_348:
                fr = new Schedule_348Fragment();
                System.out.println("348 fragment");
                break;
            case R.id.btn_351:
                fr = new Schedule_351Fragment();
                System.out.println("351 fragment");
                break;
        }
        fm = getSupportFragmentManager();
        fm.popBackStack();
        ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fr);
        ft.commit();

//        fm = getSupportFragmentManager();
//        ft = fm.beginTransaction();
//        ft.replace(R.id.frameLayout, fr);
//        fm.popBackStack();
    }


    public void setmOnBackPressedListener(View.OnClickListener listener){
            super.onBackPressed();
    }



}