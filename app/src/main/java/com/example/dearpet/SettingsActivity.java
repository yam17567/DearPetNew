package com.example.dearpet;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editPassword,Shower,haircutTime;
    Switch notificationSwitch1,notificationSwitch2;
    Button saveBtn;
    String user,pass;
    public Animal ani;
    DatabaseService db;
    public boolean getN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_setting );

        editPassword =(EditText)findViewById ( R.id.editPass );
        Shower=(EditText)findViewById ( R.id.showerTime );
        haircutTime=(EditText)findViewById ( R.id.haircutTime );
        saveBtn = (Button)findViewById ( R.id.SaveBtn );

        user = getIntent ().getStringExtra ( "user" );
        pass = getIntent ().getStringExtra ( "pass" );

        db = DatabaseService.getInstance ( this.getApplicationContext () );
        this.ani= db.getAnimalByUserAndPass ( user , pass );

        saveBtn.setOnClickListener ( this );


    }

    @Override
    public void onClick(View v) {
        if (v == saveBtn)
        {
            if(editPassword.getText ().toString ()!="")
            {
                ani.setPassword ( editPassword.getText ().toString () );
            }
            if(Shower.getText ().toString ()!="")
            {
                ani.setAnimalLastShower ( Shower.getText ().toString () );
            }
            if(haircutTime.getText ().toString ()!="")
            {
                ani.setAnimalLastHaircut (  haircutTime.getText ().toString ());
            }
        }

    }
}
