package com.example.dearpet;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    TextView hi, PetName, PetGender, PetBirthday,raceT,ShowerCouner,hairCutCounter,TripOrSandBoxOrCageCleanCounter,foodCounter,battery;
    Button UploadFirstImage,GotFood,menuBtn;
    public ImageView photoPet;
    public Animal animal;
    DatabaseService db;
    String user,pass;

    BroadCastBattery broadCastBattery;

    private static final int IMAGE_PICK_CODE=1000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main_screen );


        hi = (TextView) findViewById ( R.id.MassageOfHi );
        PetName = (TextView) findViewById ( R.id.PetName );
        PetGender = (TextView) findViewById ( R.id.PetGender );
        PetBirthday = (TextView) findViewById ( R.id.PetBirthday );
        raceT = (TextView) findViewById ( R.id.raceT );
        ShowerCouner = (TextView)findViewById ( R.id.Shower ) ;
        hairCutCounter=(TextView)findViewById ( R.id.hairCut ) ;
        battery=(TextView)findViewById ( R.id.battery );
         user = getIntent ().getStringExtra ( "user" );
         pass = getIntent ().getStringExtra ( "pass" );

         DateServies dateServies=new DateServies ( "birth" );
        photoPet = (ImageView) findViewById ( R.id.PetImage );
        
        UploadFirstImage = (Button) findViewById ( R.id.UploadFirstImage );
        GotFood = (Button) findViewById ( R.id.GotFood );
        menuBtn = (Button) findViewById ( R.id.PassToMenu );

        UploadFirstImage.setOnClickListener ( this );
        menuBtn.setOnClickListener ( this );
        GotFood.setOnClickListener ( this );


        db = DatabaseService.getInstance ( this.getApplicationContext () );
        this.animal = db.getAnimalByUserAndPass ( user , pass );


        PetName.setText ( animal.getAnimalmName () );
        PetGender.setText ( animal.getAnimalGender () );
        PetBirthday.setText ( animal.getAnimalBirthday () );
        raceT.setText ( animal.getAnimalRace () );

        SimpleDateFormat s =new SimpleDateFormat ( "MM.dd.yyyy" );
        Date r = null,f= null,b=null;
        try {
            r = s.parse(animal.getAnimalLastShower ());
            f = s.parse(animal.getAnimalLastHaircut ());
            b=s.parse ( animal.getAnimalBirthday () );

        } catch (ParseException e) {
            e.printStackTrace ();
        }
        long d=getDaysBetween ( r );
        long q= getDaysBetween ( f );
        long b1= getDaysBetween ( b );
       while(b1>360)
        {
            b1=b1-360;
        }
        ShowerCouner.setText ( "time left for the next Shower" + d + "/ 93 days" );
        if(d>360)
        {
            while(d>360)
            {
                d=d-360;
            }
        }
        if(d>=93) {
            Toast.makeText ( getApplicationContext () , "you need to give your pet shower" , Toast.LENGTH_LONG ).show ();
        }
        if(q>360)
        {
            while(q>360)
            {
                q=q-360;
            }
        }
        if(q>=140) {
            Toast.makeText ( getApplicationContext () , "you need to give your pet a hairCut" , Toast.LENGTH_LONG ).show ();
        }
        hairCutCounter.setText ( "time left for the next hairCut is- "+ q +"/ 140 days" );

        if(b1==0)
        {
            Intent birthdayServiceI= new Intent ( this,DateServies.class );
            startService ( birthdayServiceI ); }


        broadCastBattery=new BroadCastBattery();

    }

    private class BroadCastBattery extends BroadcastReceiver {
        @Override
        public void onReceive(Context context , Intent intent) {
            int battery1 = intent.getIntExtra ( "level" , 0 );
            battery.setText ( "battary statos - "+ String.valueOf ( battery1 ) + " %" );
            if(battery1<14)
            {
                Toast.makeText(getApplicationContext(), "your battery is low", Toast.LENGTH_SHORT).show();
            }}
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadCastBattery,new IntentFilter ( Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadCastBattery);
    }

    @Override
    public void onClick(View v) {

        if(UploadFirstImage == v)
        {
            Intent CamaraIntent = new Intent ( MediaStore.ACTION_IMAGE_CAPTURE );
            startActivityForResult ( CamaraIntent,0 );

        }
       if(GotFood ==v)
        {
            if(this.animal.getAnimalKind ()=="Dog")
            {
                Dog g = db.getDog ( animal.getAnimalId () );
                String massg= "good you gave him another meal. you need to give him "+ g.getMealsAday ()+"meals a day";
                Toast.makeText(getApplicationContext(), massg, Toast.LENGTH_LONG).show();
            }
            if(this.animal.getAnimalKind ()=="Cat")
        {
            Cat c = db.getCat ( animal.getAnimalId () );
            String massg= "good you gave him another meal. you need to give him "+ c.getEatsInAday ()+ "meals a day";
            Toast.makeText(getApplicationContext(), massg, Toast.LENGTH_LONG).show();
        }
            if(this.animal.getAnimalKind ()=="Rabbit")
            {
                Rabbit r= db.getRabbit ( animal.getAnimalId () );
                String massg= "good you gave him another meal. you need to give him "+ r.getMealsAdayR ()+ "meals a day";
                Toast.makeText(getApplicationContext(), massg, Toast.LENGTH_LONG).show();
            }
        }
        if(menuBtn == v)
        {
            Intent i = new Intent(UserActivity.this, MenuActivity.class);
            i.putExtra ( "user",user);
            i.putExtra ( "pass",pass);
            startActivity(i);
        }

    }

    public long getDaysBetween(Date d)
    {
        long diff = Calendar.getInstance().getTime().getTime() - d.getTime();
        long days =  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return days;
    }

    @Override
    protected void onActivityResult(int requestCod, int resultCode , Intent data)
    {
        if(resultCode == RESULT_OK && requestCod == IMAGE_PICK_CODE)
        {
            photoPet.setImageURI ( data.getData () );
        }
        if(resultCode == RESULT_OK && requestCod == 0)
        {
            Bitmap bitmap = (Bitmap)data.getExtras ().get( "data" );
            photoPet.setImageBitmap ( bitmap);
        }
    }



}
