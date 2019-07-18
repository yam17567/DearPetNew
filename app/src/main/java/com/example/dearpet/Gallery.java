package com.example.dearpet;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity implements View.OnClickListener {
    public ImageView myImageView;
    public ArrayList<Event> images;
    Button mChooseBtn, camaraBtn;

    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_gallery );

        myImageView = (ImageView)findViewById ( R.id.Image_View );
        mChooseBtn = (Button)findViewById ( R.id.choose_image_btn );
        camaraBtn= (Button)findViewById ( R.id.camraBtn ) ;

        mChooseBtn.setOnClickListener ( this );
        camaraBtn.setOnClickListener ( this );

    }

    @Override
    public void onClick(View v) {

        if( v == mChooseBtn)
        {

                Intent intent = new Intent ( Intent.ACTION_PICK);
                intent.setType ( "image/*" );
                startActivityForResult (intent,IMAGE_PICK_CODE  );
        }
        if(camaraBtn == v)
        {
            Intent CamaraIntent = new Intent ( MediaStore.ACTION_IMAGE_CAPTURE );
            startActivityForResult ( CamaraIntent,0 );
        }
    }




    @Override
    protected void onActivityResult(int requestCod, int resultCode , Intent data)
    {
        if(resultCode == RESULT_OK && requestCod == IMAGE_PICK_CODE)
        {
            myImageView.setImageURI ( data.getData () );
        }
        if(resultCode == RESULT_OK && requestCod == 0)
        {
            Bitmap bitmap = (Bitmap)data.getExtras ().get( "data" );
            myImageView.setImageBitmap ( bitmap);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permission, int [] grantResult)
    {
        switch (requestCode)
        {
            case PERMISSION_CODE:
            {
                if(grantResult.length>0 && grantResult[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent ( Intent.ACTION_PICK);
                    intent.setType ( "image/*" );
                    startActivityForResult (intent,IMAGE_PICK_CODE  );;
                } else {
                    Toast.makeText( this, "permission denied...", Toast.LENGTH_SHORT).show ();}

            }
        }
    }

}
