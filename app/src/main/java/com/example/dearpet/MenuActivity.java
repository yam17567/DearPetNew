package com.example.dearpet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button galleryBtn,SettingBtn,EventsScheduleBtn,MedicalDiaryBtn;
    String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_menu );

        galleryBtn = (Button)findViewById ( R.id.GalleryBtn );
        SettingBtn = (Button)findViewById ( R.id.SettingBtn );
        EventsScheduleBtn = (Button)findViewById ( R.id.EventsScheduleBtn );
        MedicalDiaryBtn = (Button)findViewById ( R.id.MedicalDiaryBtn );

        user = getIntent ().getStringExtra ( "user" );
        pass = getIntent ().getStringExtra ( "pass" );


        galleryBtn.setOnClickListener ( this );
        SettingBtn.setOnClickListener ( this );
        EventsScheduleBtn.setOnClickListener ( this );
        MedicalDiaryBtn.setOnClickListener ( this );
    }

    @Override
    public void onClick(View v) {
        if(galleryBtn== v)
        {
            Intent i = new Intent( MenuActivity.this, Gallery.class);
            i.putExtra ( "user",user);
            i.putExtra ( "pass",pass);
            startActivity(i);
        }
        if(SettingBtn == v)
        {
            Intent i = new Intent( MenuActivity.this, SettingsActivity.class);
            i.putExtra ( "user",user);
            i.putExtra ( "pass",pass);
            startActivity(i);
        }
        if(EventsScheduleBtn == v)
        {
            Intent i = new Intent( MenuActivity.this, EventSchedule.class);
            i.putExtra ( "user",user);
            i.putExtra ( "pass",pass);
            startActivity(i);
        }
        if(MedicalDiaryBtn==v)
        {
            Intent i = new Intent( MenuActivity.this, MedicalDiaryActivity.class);
            i.putExtra ( "user",user);
            i.putExtra ( "pass",pass);
            startActivity(i);
        }
    }
}
