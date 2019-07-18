package com.example.dearpet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CatActivity extends AppCompatActivity implements View.OnClickListener {
    TextView qus1, qus2, qus3;
    public EditText NdayC, eatsInAday, NailsRemider;
    public boolean reminderN;
    public static Cat c;
    DatabaseService d;
    Button ContinueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_cat );

        qus1 = (TextView) findViewById ( R.id.qus1 );
        qus2 = (TextView) findViewById ( R.id.qus22 );
        qus3 = (TextView) findViewById ( R.id.qus33 );
        NdayC = (EditText) findViewById ( R.id.NumOfDayCleaning );
        eatsInAday = (EditText) findViewById ( R.id.EatsFoodInDay );
        NailsRemider = (EditText) findViewById ( R.id.NailsRemider );
        ContinueButton = (Button) findViewById ( R.id.ContinueButton );

        if (NailsRemider.getText ().toString ().equals ( "yes" )) {
            reminderN = true;
        } else {
            reminderN = false;
        }


        ContinueButton.setOnClickListener ( this );
    }

    public static Cat getC() {
        return c;
    }

    public void setC(Cat c) {
        this.c = c;
    }

    @Override
    public void onClick(View v) {
        if(ContinueButton == v)
        {
            DatabaseService db = DatabaseService.getInstance(this.getApplicationContext());
            String id =getIntent ().getStringExtra ( "animalId" );
            db.insertCat (id,Integer.valueOf (NdayC.getText ().toString ()),NailsRemider.getText ().toString (),Integer.valueOf (eatsInAday.getText ().toString ()));
            // OBJECT IN-
            Cat c = new Cat(id,AddUserActivity.userName.getText ().toString (),
                            AddUserActivity.password.getText().toString(),
                            AddUserActivity.kind.getText().toString(),
                            AddUserActivity.AnimalName.getText().toString(),
                            AddUserActivity.race.getText().toString(),
                            AddUserActivity.Gen,
                            AddUserActivity.birthday.getText().toString(),
                            AddUserActivity.LastHairCut.getText().toString(),
                            AddUserActivity.LastShower.getText().toString(),
                            AddUserActivity.mads,
                            Integer.valueOf (NdayC.getText ().toString ()),
                            NailsRemider.getText ().toString (),Integer.valueOf (eatsInAday.getText ().toString ()));
            this.c=c;
            Intent LoginTryIntent = new Intent( CatActivity.this, UserActivity.class);
            LoginTryIntent.putExtra ( "id",c.getAnimalKind () );
            startActivity(LoginTryIntent);
        }
    }






}

