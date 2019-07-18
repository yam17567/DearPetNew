package com.example.dearpet;

public class Event {

    public String Date1;
    public String discreption;

    public Event(String date2 , String g)
    {
        this.Date1=date2;
        this.discreption=g;
    }

    public String getDate1() {
        return Date1;
    }

    public void setDate1(String date1) {
        Date1 = date1;
    }

    public String getDiscreption() {
        return discreption;
    }

    public void setDiscreption(String discreption) {
        this.discreption = discreption;
    }
}
