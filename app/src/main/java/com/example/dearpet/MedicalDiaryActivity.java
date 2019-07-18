package com.example.dearpet;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MedicalDiaryActivity extends AppCompatActivity implements View.OnClickListener {

    Button ShowMa,AddMads1;
    EditText editTextMads;
    TextView v;
    public Animal a;
    DatabaseService db;
    String user,pass;
    Dialog d;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_medical_diary );

        ShowMa = (Button)findViewById ( R.id.ShowMa );
        AddMads1 = (Button)findViewById ( R.id.AddMads1 );
        editTextMads = (EditText)findViewById ( R.id.editTextMads );
        user= getIntent ().getStringExtra ( "user" );
        pass = getIntent ().getStringExtra ( "pass" );

        db = DatabaseService.getInstance ( this.getApplicationContext () );
        this.a =  db.getAnimalByUserAndPass ( user , pass );
        ShowMa.setOnClickListener ( this );
        AddMads1.setOnClickListener ( this );
        sp=getSharedPreferences("details1",0);


    }

    public void createLoginDialog()
    {


        d= new Dialog(this);
        d.setContentView(R.layout.madsss);
        d.setTitle("mads");
        v=(TextView)d.findViewById (R.id.showMaa  ) ;
        v.setText ( this.a.getAnimalMedications () );
        d.setCancelable(true);
        d.show ();


    }

    @Override
    public void onClick(View v) {
        if(v==ShowMa)
        {
            createLoginDialog ();
        }
        if(AddMads1 == v)
        {
            String mads = this.a.getAnimalMedications ();
            mads += ( editTextMads.getText ().toString () )+", ";
            editTextMads.setText ( "" );
            this.a.setAnimalMedications ( mads );
        }

    }
}
