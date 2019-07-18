package com.example.dearpet;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class DogActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText commands;

    public static void setD(Dog d) {
        DogActivity.d = d;
    }

    public static Dog getD() {
        return d;
    }

    public EditText numOfWalks;
    public EditText numOfWalkers;
    public EditText numOfMealsDay;
    public String comm = "";
    public static Dog d;
    Button MoreCommands, toContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_dog );

        commands = (EditText) findViewById ( R.id.Commands );
        numOfWalks = (EditText) findViewById ( R.id.numOfWalks );
        numOfWalkers = (EditText) findViewById ( R.id.numOfWalkers );
        numOfMealsDay = (EditText) findViewById ( R.id.EatsFoodInDay );
        MoreCommands = (Button) findViewById ( R.id.AddC );
        toContinue = (Button) findViewById ( R.id.buttonToContinue );


        MoreCommands.setOnClickListener ( this );
        toContinue.setOnClickListener ( this );

    }

    @Override
    public void onClick(View v) {
        if (MoreCommands == v) {
            comm += commands.getText ().toString ()+", ";
            commands.setText ( "" );
        }
        if (toContinue == v) {
            Intent LoginTryIntent;
            // DATABASE IN-
            DatabaseService db = DatabaseService.getInstance(this.getApplicationContext());
            String id =getIntent ().getStringExtra ( "animalId" );
            int numwalks = Integer.valueOf (numOfWalks.getText ().toString ());
            int nummeals = Integer.valueOf ( numOfMealsDay.getText ().toString () );
            db.insertDog (id,numwalks,comm,nummeals  );
           // OBJECT IN-
            Dog d = new Dog(id,AddUserActivity.userName.getText ().toString (),
                            AddUserActivity.password.getText().toString(),
                            AddUserActivity.kind.getText().toString(),AddUserActivity.race.getText ().toString (),
                            AddUserActivity.race.getText().toString(),
                            AddUserActivity.Gen,
                            AddUserActivity.birthday.getText().toString(),
                            AddUserActivity.LastHairCut.getText().toString(),
                            AddUserActivity.LastShower.getText().toString(),
                            AddUserActivity.mads,
                            AddUserActivity.AnimalName.getText().toString(),Integer.valueOf (numOfWalks.getText ().toString ()),Integer.valueOf ( numOfMealsDay.getText ().toString () ),
                             comm.toString ());
            this.d =d;
            LoginTryIntent = new Intent ( DogActivity.this , UserActivity.class );
            startActivity ( LoginTryIntent );
        }
    }

}