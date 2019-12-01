package com.example.lectureroomReservation;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule_348Fragment extends Fragment {

    FragmentManager fm;
    FragmentTransaction ft;
    int version = 1;
    DatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

    Button[][] btn;
    int day = 5;
    int time = 6;
    int[][] schedule;
    String[] dayStr = {"월요일", "화요일", "수요일", "목요일", "금요일"};
    int chooseDay;
    int flag = 0;
    private Context context;
    String userID;



    private void setCourses(){

    }

    private View.OnClickListener btnListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.confirm)       //확인 눌렀을때
            {
                String text_day = dayStr[chooseDay] + " ";
                String text_time  = "";
                for(int j = 0; j<time; j++)
                {
                    if(schedule[chooseDay][j] == 1 && flag == 0)
                    {
                        int start = j + 18;
                        text_time = start + ":00시 ~ ";
                        flag = 1;
                        helper.insertTimeTable348(database, userID, chooseDay, j);
                    }
                    else if(schedule[chooseDay][j] == 1 && flag == 1)
                    {
                        helper.insertTimeTable348(database, userID, chooseDay, j);
                        if(j == 5)
                        {
                            int end = j + 18;
                            text_time =  text_time + end + ":00시까지 강의실을 대여했습니다";
                            flag = 0;
                        }
                    }
                    else if(schedule[chooseDay][j] == 0 && flag == 1)
                    {
                        int end = j + 17;
                        text_time =  text_time + end + ":00시까지 강의실을 대여했습니다";
                        flag = 0;
                    }
                }

                Toast.makeText(context, text_day + text_time, Toast.LENGTH_SHORT).show();
                System.out.println(text_day+text_time);

                ((ReserveActivity)context).setmOnBackPressedListener(this);

            }
            else if(v.getId() == R.id.cancel)
            {
                ((ReserveActivity)context).setmOnBackPressedListener(this);

            }
            else        //시간표 눌렀을때
            {
                for(int i = 0; i<day; i++)
                {
                    for(int j = 0; j<time; j++)
                    {
                        if(v.getId() == btn[i][j].getId())
                        {
                            if(schedule[i][j] == 1){
                                //이미 예약됐음
                                Toast toast = Toast.makeText(context, "이미 예약되었습니다.", Toast.LENGTH_SHORT);
                                toast.show();
                            }else{
                                chooseDay = i;
                                schedule[i][j] = 1;
                                System.out.println(i + " " + j  +"button이 눌렸음");
                                btn[i][j].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                            }
                        }
                    }
                }
            }
        }
    };

    public Schedule_348Fragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_schedule_324, container, false);
        context = container.getContext();

        helper = new DatabaseOpenHelper(context, DatabaseOpenHelper.tableName, null, version);
        TextView lectureroom = (TextView)view.findViewById(R.id.lectureroom);
        lectureroom.setText("348호");
        database = helper.getWritableDatabase();

        btn = new Button[5][6];
        schedule = new int[5][6];

        btn[0][0] = view.findViewById(R.id.monday18);
        btn[0][1] = view.findViewById(R.id.monday19);
        btn[0][2] = view.findViewById(R.id.monday20);
        btn[0][3] = view.findViewById(R.id.monday21);
        btn[0][4] = view.findViewById(R.id.monday22);
        btn[0][5] = view.findViewById(R.id.monday23);

        btn[1][0] = view.findViewById(R.id.tuesday18);
        btn[1][1] = view.findViewById(R.id.tuesday19);
        btn[1][2] = view.findViewById(R.id.tuesday20);
        btn[1][3] = view.findViewById(R.id.tuesday21);
        btn[1][4] = view.findViewById(R.id.tuesday22);
        btn[1][5] = view.findViewById(R.id.tuesday23);

        btn[2][0] = view.findViewById(R.id.wednesday18);
        btn[2][1] = view.findViewById(R.id.wednesday19);
        btn[2][2] = view.findViewById(R.id.wednesday20);
        btn[2][3] = view.findViewById(R.id.wednesday21);
        btn[2][4] = view.findViewById(R.id.wednesday22);
        btn[2][5] = view.findViewById(R.id.wednesday23);

        btn[3][0] = view.findViewById(R.id.thursday18);
        btn[3][1] = view.findViewById(R.id.thursday19);
        btn[3][2] = view.findViewById(R.id.thursday20);
        btn[3][3] = view.findViewById(R.id.thursday21);
        btn[3][4] = view.findViewById(R.id.thursday22);
        btn[3][5] = view.findViewById(R.id.thursday23);

        btn[4][0] = view.findViewById(R.id.friday18);
        btn[4][1] = view.findViewById(R.id.friday19);
        btn[4][2] = view.findViewById(R.id.friday20);
        btn[4][3] = view.findViewById(R.id.friday21);
        btn[4][4] = view.findViewById(R.id.friday22);
        btn[4][5] = view.findViewById(R.id.friday23);

        sql = "SELECT * FROM " + helper.tableName;
        cursor = database.rawQuery(sql, null);




        while(cursor.moveToNext())
        {
            String id = cursor.getString(0);
            String pw = cursor.getString(1);
            int conn = cursor.getInt(2);
            System.out.println(id + " , " + pw + " , " + conn);
            if(conn == 1)
                userID = id;
        }

//        sql = "SELECT id FROM "+ helper.tableName + " WHERE conn = " + 1 ;
//        cursor = database.rawQuery(sql, null);
//
//        System.out.println("SELECT id FROM "+ helper.tableName + " WHERE conn = " + 1);
//
//        if(cursor.getCount() != 0) // 접속 유저를 찾음
//        {
//            userID = cursor.getString(0);
//        }

        System.out.println("접속 유저는 : " + userID);

        for(int i = 0; i<day; i++)
        {
            for(int j = 0; j<time; j++)
            {
                sql = "SELECT id FROM "+ helper.tableName348 + " WHERE day = " + i +  " and time = " + j ;
                cursor = database.rawQuery(sql, null);

                //System.out.println("select id from timetable where day = " + i + " and time = " + j);
                //System.out.println("결과투플: " + cursor.getCount());

                if(cursor.getCount() != 0)
                {
                    btn[i][j].setBackgroundColor(Color.GRAY);
                    schedule[i][j] =1;
                    btn[i][j].setText("예약완료");
                }
                else
                    schedule[i][j] = 0;
            }
        }

        System.out.println("In fragment 348");
        for(int i = 0; i<day; i++)
        {
            for(int j = 0; j<time; j++)
            {
                this.btn[i][j].setOnClickListener(btnListener);
            }
        }

        Button confirmBtn = view.findViewById(R.id.confirm);
        confirmBtn.setOnClickListener(btnListener);

        Button cancelBtn = view.findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(btnListener);
        return view;
    }


}