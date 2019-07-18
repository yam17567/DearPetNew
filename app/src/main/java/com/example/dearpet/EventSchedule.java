package com.example.dearpet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class EventSchedule extends AppCompatActivity implements View.OnClickListener, CalendarView.OnDateChangeListener {
    CalendarView calendarView;
    EditText theAddText;
    public String date2;
    Button add;
    public int i=0;
    public Event[] events = new Event[30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_event_schedule );

        calendarView = (CalendarView)findViewById ( R.id.calendarView );
        theAddText = (EditText)findViewById ( R.id.theAddText );
        add = (Button)findViewById ( R.id.toAddSomething );

        calendarView.setOnDateChangeListener ( this );
        add.setOnClickListener ( this );

    }

    @Override
    public void onClick(View v)
    {
if(add == v)
{
  addEvent ();

}
    }

    @Override
    public void onSelectedDayChange(CalendarView view , int year , int month , int dayOfMonth) {
        String date = dayOfMonth +"/" +month + "/" +year;
        if(theAddText.getText ().toString () != "")
        {
            int in=0;
           while (in<events.length) {
               if (events[in]!=null) {
                   if (events[in].getDate1 () == date) {
                       Toast.makeText ( getApplicationContext () , events[in].getDiscreption () , Toast.LENGTH_SHORT ).show ();
                       in=events.length-1;
                   }
                   in++;
               }
           }
        } else {
            this.date2 =date;
            addEvent ();
        }
    }
    public void addEvent()
    {
        Event e =new Event (this.date2,theAddText.getText ().toString ());
        events[this.i]=e;
        this.i=this.i +1;
        theAddText.setText ( " " );
    }
}
