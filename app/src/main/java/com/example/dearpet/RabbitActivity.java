package com.example.dearpet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RabbitActivity extends AppCompatActivity implements View.OnClickListener {
    TextView qus1,qus2;
    public EditText NumOfDayCleaningR, MealsDay;
    public static Rabbit r;
    Button ConButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_rabbit );

        qus1 = (TextView)findViewById ( R.id.qus11 );
        qus2 = (TextView)findViewById ( R.id.qus222);
        NumOfDayCleaningR= (EditText)findViewById ( R.id.NumOfDayCleaningR );
        MealsDay = (EditText)findViewById ( R.id.eatsInAday );
        ConButt=(Button)findViewById ( R.id.ContinueB);

        ConButt.setOnClickListener ( this );
    }

    public static Rabbit getR() {
        return r;
    }

    public static void setR(Rabbit r) {
        RabbitActivity.r = r;
    }

    @Override
    public void onClick(View v) {
        if(ConButt == v)
        {
            DatabaseService db = DatabaseService.getInstance(this.getApplicationContext());
            String id =getIntent ().getStringExtra ( "animalId" );
            db.insertRabbit (id,Integer.valueOf ( NumOfDayCleaningR.getText ().toString () ),Integer.valueOf ( MealsDay.getText ().toString () ));
            // OBJECT IN-
            Rabbit r = new Rabbit (id,AddUserActivity.userName.getText ().toString (),
                            AddUserActivity.password.getText().toString(),
                            AddUserActivity.kind.getText().toString(),
                            AddUserActivity.AnimalName.getText().toString(),
                            AddUserActivity.race.getText().toString(),
                            AddUserActivity.Gen,
                            AddUserActivity.birthday.getText().toString(),
                            AddUserActivity.LastHairCut.getText().toString(),
                            AddUserActivity.LastShower.getText().toString(),
                            AddUserActivity.mads,Integer.valueOf ( NumOfDayCleaningR.getText ().toString () ),
                                   Integer.valueOf ( MealsDay.getText ().toString () ));
            this.r=r;
            Intent LoginTryIntent = new Intent( RabbitActivity.this, UserActivity.class);
            LoginTryIntent.putExtra ( "id",id );
            startActivity(LoginTryIntent);
        }
    }


}
