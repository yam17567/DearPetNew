package com.example.dearpet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addUserBtn, signInbtn;
    TextView Hi, user, Pass;
    private EditText userName;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addUserBtn = (Button) findViewById(R.id.AddUser);
        signInbtn = (Button) findViewById(R.id.signInbtn);
        Hi = (TextView) findViewById(R.id.Hello);
        user = (TextView) findViewById(R.id.user);
        Pass = (TextView) findViewById(R.id.pass);
        userName = (EditText) findViewById(R.id.userName);
        Password = (EditText) findViewById(R.id.password);

        signInbtn.setOnClickListener(this);
        addUserBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == addUserBtn) {
            Intent newIntent = new Intent(MainActivity.this, AddUserActivity.class);
            startActivity(newIntent);
        } else if (signInbtn == v) {
            DatabaseService db = DatabaseService.getInstance(this.getApplicationContext());
            Animal animal = db.getAnimalByUserAndPass(userName.getText().toString(), Password.getText().toString());
            if (animal != null) {
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                Intent LoginTryIntent = new Intent(MainActivity.this, UserActivity.class);
                LoginTryIntent.putExtra ( "user",userName.getText().toString());
                LoginTryIntent.putExtra ( "pass",Password.getText().toString());
                startActivity(LoginTryIntent);
            } else {
                Toast.makeText(getApplicationContext(), "bad username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}






