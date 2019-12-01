
package com.example.lectureroomReservation;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String tableName = "Users";
    public static final String tableName324 = "Timetable324";
    public static final String tableName342 = "Timetable342";
    public static final String tableName345 = "Timetable345";
    public static final String tableName348 = "Timetable348";
    public static final String tableName351 = "Timetable351";

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("tag","db 생성_db가 없을때만 최초로 실행함");
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
    }

    public void createTable(SQLiteDatabase db){
        String sql_user = "CREATE TABLE " + tableName + " (id text, pw text, conn integer)";
        String sql_timetable324 = "CREATE TABLE " + tableName324 + " (id text, day integer, time integer)";
        String sql_timetable342 = "CREATE TABLE " + tableName342 + " (id text, day integer, time integer)";
        String sql_timetable345 = "CREATE TABLE " + tableName345 + " (id text, day integer, time integer)";
        String sql_timetable348 = "CREATE TABLE " + tableName348 + " (id text, day integer, time integer)";
        String sql_timetable351 = "CREATE TABLE " + tableName351 + " (id text, day integer, time integer)";

        try {
            db.execSQL(sql_user);
            db.execSQL(sql_timetable324);
            db.execSQL(sql_timetable342);
            db.execSQL(sql_timetable345);
            db.execSQL(sql_timetable348);
            db.execSQL(sql_timetable351);
        }catch (SQLException e){
        }
    }

    public void insertUser(SQLiteDatabase db, String id, String pw, int conn){
        Log.i("tag","회원가입을 했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName + "(id, pw, conn)" + "values('"+ id +"', '"+pw+"','" + conn + "')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    public void updateUser(SQLiteDatabase db, String id, String pw, int conn){
        Log.i("tag","유저가 접속하면 실행함");
        db.beginTransaction();
        try {
            String sql = "UPDATE " + tableName + " SET conn = " + conn + " WHERE pw = '" + pw + "' and id = '" + id + "'";
            System.out.println("UPDATE " + tableName + " SET connect = " + conn + " WHERE pw = '" + pw + "' and id = '" + id + "'");
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
    public void insertTimeTable324(SQLiteDatabase db, String id, int day, int time)
    {
        Log.i("tag", "예약을 완료했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName324 + "(id, day, time)" + "values('" + id + "','" + day + "', '" + time + "')"; //day 0~4 월화수목금을 의미
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }

    public void insertTimeTable342(SQLiteDatabase db, String id, int day, int time)
    {
        Log.i("tag", "예약을 완료했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName342 + "(id, day, time)" + "values('" + id + "','" + day + "', '" + time + "')"; //day 0~4 월화수목금을 의미
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }

    public void insertTimeTable345(SQLiteDatabase db, String id, int day, int time)
    {
        Log.i("tag", "예약을 완료했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName345 + "(id, day, time)" + "values('" + id + "','" + day + "', '" + time + "')"; //day 0~4 월화수목금을 의미
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }

    public void insertTimeTable348(SQLiteDatabase db, String id, int day, int time)
    {
        Log.i("tag", "예약을 완료했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName348 + "(id, day, time)" + "values('" + id + "','" + day + "', '" + time + "')"; //day 0~4 월화수목금을 의미
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }

    public void insertTimeTable351(SQLiteDatabase db, String id, int day, int time)
    {
        Log.i("tag", "예약을 완료했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName351 + "(id, day, time)" + "values('" + id + "','" + day + "', '" + time + "')"; //day 0~4 월화수목금을 의미
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }
}