
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
    public static final String tableRegistration = "Registration";

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
        String sql_timetable324 = "CREATE TABLE " + tableName324 + " (day integer, time integer)";
        String sql_timetable342 = "CREATE TABLE " + tableName342 + " (day integer, time integer)";
        String sql_timetable345 = "CREATE TABLE " + tableName345 + " (day integer, time integer)";
        String sql_timetable348 = "CREATE TABLE " + tableName348 + " (day integer, time integer)";
        String sql_timetable351 = "CREATE TABLE " + tableName351 + " (day integer, time integer)";
        String sql_registration = "CREATE TABLE " + tableRegistration + "(id text, room integer, day integer, s_time integer, f_time integer)";

        try {
            db.execSQL(sql_user);
            db.execSQL(sql_timetable324);
            db.execSQL(sql_timetable342);
            db.execSQL(sql_timetable345);
            db.execSQL(sql_timetable348);
            db.execSQL(sql_timetable351);
            db.execSQL(sql_registration);
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
    public void insertTimeTable324(SQLiteDatabase db, int day, int time)
    {
        Log.i("tag", "예약을 완료했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName324 + "(day, time)" + "values('" + day + "', '" + time + "')"; //day 0~4 월화수목금을 의미
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }

    public void insertTimeTable342(SQLiteDatabase db, int day, int time)
    {
        Log.i("tag", "예약을 완료했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName342 + "(day, time)" + "values('" + day + "', '" + time + "')"; //day 0~4 월화수목금을 의미
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }

    public void insertTimeTable345(SQLiteDatabase db, int day, int time)
    {
        Log.i("tag", "예약을 완료했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName345 + "(day, time)" + "values('"  + day + "', '" + time + "')"; //day 0~4 월화수목금을 의미
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }

    public void insertTimeTable348(SQLiteDatabase db, int day, int time)
    {
        Log.i("tag", "예약을 완료했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName348 + "(day, time)" + "values('" + day + "', '" + time + "')"; //day 0~4 월화수목금을 의미
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }

    public void insertTimeTable351(SQLiteDatabase db, int day, int time)
    {
        Log.i("tag", "예약을 완료했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName351 + "(day, time)" + "values('" +  day + "', '" + time + "')"; //day 0~4 월화수목금을 의미
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }

    public void insertRegistration(SQLiteDatabase db, String id, int room, int day, int s_time, int f_time)
    {
        Log.i("tag", "예약명단에 추가");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableRegistration + "(id, room, day, s_time, f_time)" + "values('" + id + "','" + room + "','" +day + "', '" + s_time + "', '" + f_time + "')"; //day 0~4 월화수목금을 의미
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }

    public void deleteRegistration(SQLiteDatabase db, String id, int room, int day, int s_time, int f_time)
    {
        Log.i("tag", "예약명단에서 삭제");
        db.beginTransaction();
        try {
            String sql = "DELETE FROM " + tableRegistration + " WHERE room = 324 and day = " + day + " and s_time = " + s_time + " and f_time = " + f_time;
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }

    public void deleteRoom_324(SQLiteDatabase db, int day, int time)
    {
        Log.i("tag", "324 " +day + " " + time + " 시간표에서 삭제");
        db.beginTransaction();
        try {
            String sql = "DELETE FROM " + tableName324 + " WHERE day = " + day + " and time = " + time;
            System.out.println("DELETE FROM " + tableName324 + " WHERE day = " + day + " and time = " + time);
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }
    public void deleteRoom_342(SQLiteDatabase db, int day, int time)
    {
        Log.i("tag", "342 " +day + " " + time + " 시간표에서 삭제");
        db.beginTransaction();
        try {
            String sql = "DELETE FROM " + tableName342 + " WHERE day = " + day + " and time = " + time;
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }
    public void deleteRoom_345(SQLiteDatabase db, int day, int time)
    {
        Log.i("tag", "345 " +day + " " + time + " 시간표에서 삭제");
        db.beginTransaction();
        try {
            String sql = "DELETE FROM " + tableName345 + " WHERE day = " + day + " and time = " + time;
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }
    public void deleteRoom_348(SQLiteDatabase db, int day, int time)
    {
        Log.i("tag", "348 " +day + " " + time + " 시간표에서 삭제");
        db.beginTransaction();
        try {
            String sql = "DELETE FROM " + tableName348 + " WHERE day = " + day + " and time = " + time;
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            db.endTransaction();
        }
    }
    public void deleteRoom_351(SQLiteDatabase db, int day, int time)
    {
        Log.i("tag", "351 " +day + " " + time + " 시간표에서 삭제");
        db.beginTransaction();
        try {
            String sql = "DELETE FROM " + tableName351 + " WHERE day = " + day + " and time = " + time;
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