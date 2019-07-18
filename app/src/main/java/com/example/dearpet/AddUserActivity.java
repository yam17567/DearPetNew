package com.example.dearpet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    public static EditText race;
    public static EditText AnimalName;
    public EditText withMads;
    public static EditText birthday;
    public static EditText password;
    public static EditText userName;
    public static EditText kind;
    public static EditText LastShower;
    public static EditText LastHairCut;
    public RadioGroup Gender;
    Button toMoreCountin, SubmitMad;

    public static String mads;
    public static String Gen = "male";


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_add_user );
        userName = (EditText) findViewById ( R.id.OwnerName );
        password = (EditText) findViewById ( R.id.passw );
        race = (EditText) findViewById ( R.id.Race );
        AnimalName = (EditText) findViewById ( R.id.AnimalName );
        birthday = (EditText) findViewById ( R.id.Birthday );
        Gender = (RadioGroup) findViewById ( R.id.ButtonRadioGENDER );
        toMoreCountin = (Button) findViewById ( R.id.buttonContinue );
        kind = (EditText) findViewById ( R.id.KindOfAnimal );
        SubmitMad = (Button) findViewById ( R.id.buttonForMoreM );
        withMads = (EditText) findViewById ( R.id.medicationTaker );
        LastHairCut = (EditText) findViewById ( R.id.HaircutDate );
        LastShower = (EditText) findViewById ( R.id.ShowerDate );

        Gender.setOnCheckedChangeListener ( this );
        toMoreCountin.setOnClickListener ( this );
        SubmitMad.setOnClickListener ( this );
        mads = "";
    }

    @Override
    public void onCheckedChanged(RadioGroup buttonView , int checkedId) {
        if (Gender == buttonView) {
            if (checkedId == R.id.Female1) {
                this.Gen = "female";
            }
            if (checkedId == R.id.Male1) {
                this.Gen = "male";
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (SubmitMad == v) {
            mads += ( withMads.getText ().toString () )+", ";
            withMads.setText ( "" );
        }
        if (toMoreCountin == v) {
            DatabaseService db = DatabaseService.getInstance(this.getApplicationContext());
            String user = userName.getText().toString();
            if(db.isAnimalExist(user)){
                Toast.makeText ( getApplicationContext () , "user already exist" , Toast.LENGTH_SHORT ).show ();
                return;
            }
            String animalId = UUID.randomUUID().toString();
            db.insertAnimal(user,
                    password.getText().toString(),
                    kind.getText().toString(),
                    race.getText().toString(),
                    Gen,
                    birthday.getText().toString(),
                    LastHairCut.getText().toString(),
                    LastShower.getText().toString(),
                    mads,
                    AnimalName.getText().toString(),
                    animalId);

           if (kind.getText ().toString ().toLowerCase().equals ( "dog" )) {
                Intent NewUserIntentD = new Intent ( AddUserActivity.this , DogActivity.class );
               NewUserIntentD.putExtra("animalId", animalId);
                startActivity ( NewUserIntentD );
           } else if (kind.getText ().toString ().toLowerCase().equals ( "cat" )) {
                Intent NewUserIntentD = new Intent ( AddUserActivity.this , CatActivity.class );
               NewUserIntentD.putExtra("animalId", animalId);
                startActivity ( NewUserIntentD );
           } else if (kind.getText ().toString ().toLowerCase().equals ( "rabbit" )) {
                Intent NewUserIntentD = new Intent ( AddUserActivity.this , RabbitActivity.class );
               NewUserIntentD.putExtra("animalId", animalId);
                startActivity ( NewUserIntentD );
           } else {
                Toast.makeText ( getApplicationContext () , "bad kind of pet" , Toast.LENGTH_SHORT ).show ();
            }
        }
    }
}



