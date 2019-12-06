
package com.example.lectureroomReservation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registration.R;

public class LoginActivity extends AppCompatActivity {

    DatabaseOpenHelper helper;
    SQLiteDatabase database;

    EditText  idEditText;
    EditText pwEditText;
    Button loginBtn;
    Button joinBtn;
    String sql;
    Cursor cursor;

    static String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idEditText = (EditText)findViewById(R.id.idText);
        pwEditText = (EditText)findViewById(R.id.pwText);

        loginBtn = (Button)findViewById(R.id.loginButton);
        joinBtn = (Button)findViewById(R.id.joinButton);

        helper = new DatabaseOpenHelper(LoginActivity.this, DatabaseOpenHelper.tableName, null, 1);
        database = helper.getWritableDatabase();

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String id = idEditText.getText().toString();
                String pw = pwEditText.getText().toString();

                if (id.length() == 0 || pw.length() == 0) {
                    Toast toast = Toast.makeText(LoginActivity.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                sql = "SELECT id FROM "+ helper.tableName + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                System.out.println("select id from users where id = " + id);
                System.out.println(cursor.getCount());
                if (cursor.getCount() != 1) {
                    //아이디가 틀렸습니다.
                    Toast toast = Toast.makeText(LoginActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                sql = "SELECT pw FROM " + helper.tableName + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                cursor.moveToNext();
                if (!pw.equals(cursor.getString(0))) {
                    //비밀번호가 틀렸습니다.
                    Toast toast = Toast.makeText(LoginActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    //로그인성공
                    helper.updateUser(database, id, pw, 1);
                    UserId = id;
                    Toast toast = Toast.makeText(LoginActivity.this, "로그인성공", Toast.LENGTH_SHORT);
                    toast.show();
                    //인텐트 생성 및 호출
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                cursor.close();
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent joinIntent = new Intent(LoginActivity.this, JoinActivity.class);
                LoginActivity.this.startActivity(joinIntent);
            }
        });

    }
}