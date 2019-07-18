package com.example.dearpet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseService extends SQLiteOpenHelper {
    private static DatabaseService sInstance;

    public static final String DATABASENAME = "Database2.db";
    public static final int DATABASEVERSION = 1;
    //**************************************************************************
    public static final String TABLE_NAME_Animals = "Animals";

    public static final String COLUMN_AnimalId = "AnimalId";
    public static final String COLUMN_username = "ownerName";
    public static final String COLUMN_password = "password";
    public static final String COLUMN_AnimalKind = "AnimalKind";
    public static final String COLUMN_AnimalName = "AnimalName";
    public static final String COLUMN_AnimalRace = "animalRace";
    public static final String COLUMN_AnimalGender = "AnimalGender";
    public static final String COLUMN_AnimalBirthday = "AnimalBirthday";
    public static final String COLUMN_AnimalLastHaircut = "AnimalLastHaircut";
    public static final String COLUMN_AnimalLastShower = "AnimalLastShower";
    public static final String COLUMN_mads = "mads";

    public static final String CREATE_TABLE_Animals =
            "CREATE TABLE " + TABLE_NAME_Animals + "("
                    + COLUMN_AnimalId + " VARCHAR, "
                    + COLUMN_username + " VARCHAR PRIMARY KEY, "
                    + COLUMN_password + " VARCHAR, "
                    + COLUMN_AnimalKind + " VARCHAR, "
                    + COLUMN_AnimalName + " VARCHAR, "
                    + COLUMN_AnimalRace + " VARCHAR, "
                    + COLUMN_AnimalGender + " VARCHAR, "
                    + COLUMN_AnimalBirthday + " VARCHAR, "
                    + COLUMN_AnimalLastHaircut + " VARCHAR, "
                    + COLUMN_AnimalLastShower + " VARCHAR, "
                    + COLUMN_mads + " VARCHAR" + ");";

    public void insertAnimal(String ownerName , String password , String animalKind , String animalRace , String animalGender , String animalBirthday , String animalLastHaircut , String animalLastShower , String mads , String AnimalmName,String animalId) {
        ContentValues valuesD = new ContentValues ();
        valuesD.put (COLUMN_AnimalId, animalId );
        valuesD.put (COLUMN_username, ownerName );
        valuesD.put ( COLUMN_password , password );
        valuesD.put ( COLUMN_AnimalKind , animalKind );
        valuesD.put ( COLUMN_AnimalName , AnimalmName );
        valuesD.put ( COLUMN_AnimalRace , animalRace );
        valuesD.put ( COLUMN_AnimalGender , animalGender );
        valuesD.put ( COLUMN_AnimalBirthday ,animalBirthday );
        valuesD.put ( COLUMN_AnimalLastHaircut ,animalLastHaircut  );
        valuesD.put ( COLUMN_AnimalLastShower ,  animalLastShower  );
        valuesD.put ( COLUMN_mads , mads  );

        this.getWritableDatabase ().insert ( DatabaseService.TABLE_NAME_Animals , null , valuesD );
        Log.i ( "data" , "animal " + animalId + "insert to database" );

    }
    public boolean isAnimalExist(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Log.i ( "data" , "Checking " + username + " if in db" );
        Cursor cursor = db.query(TABLE_NAME_Animals, new String[] { COLUMN_AnimalId,
                        COLUMN_username, COLUMN_password,COLUMN_AnimalKind,COLUMN_AnimalName,COLUMN_AnimalRace,COLUMN_AnimalGender,COLUMN_AnimalBirthday,COLUMN_AnimalLastHaircut,COLUMN_AnimalLastShower, COLUMN_mads}, COLUMN_username + "=?",
                new String[] { username }, null, null, null, null);
        if (cursor.getCount() == 0)
            return false;

        return true;
    }
    public Animal getAnimalByUserAndPass(String username, String password)
    {
        Animal animal = null;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT "+COLUMN_AnimalKind+","+COLUMN_AnimalId+" FROM "+TABLE_NAME_Animals+" WHERE "+COLUMN_username+"=? AND "+COLUMN_password+"=?",new String[]{username,password});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            String kind=cursor.getString(0);
            String animalId = cursor.getString(1);
            cursor.close();
            switch(kind.toLowerCase()) {
                case "dog":
                    animal = getDog(animalId);
                    break;
                case "cat":
                    animal = getCat(animalId);
                    break;
                case "rabbit":
                    animal = getRabbit(animalId);
                    break;
            }

        }
        return animal;
    }
    //**************************************************************************
    public static final String TABLE_NAME_Dog = "Dogs";

    public static final String COLUMN_numberOfTrips = "numberOfTrips";
    public static final String COLUMN_commands = "commands";
    public static final String COLUMN_mealsAday = "mealsAday";

    public static final String CREATE_TABLE_Dogs =
            "CREATE TABLE " + TABLE_NAME_Dog + "("
                    + COLUMN_AnimalId + " VARCHAR PRIMARY KEY, "
                    + COLUMN_numberOfTrips + " INTEGER,"
                    + COLUMN_commands + " VARCHAR,"
                    + COLUMN_mealsAday + " INTEGER" + ")";

    public void insertDog(String animalId, int numberOfTrips, String commands, int meals) {
        ContentValues valuesD = new ContentValues ();
        valuesD.put ( COLUMN_AnimalId , animalId );
        valuesD.put ( COLUMN_numberOfTrips , numberOfTrips );
        valuesD.put ( COLUMN_commands , commands );
        valuesD.put ( COLUMN_mealsAday , meals );

        this.getWritableDatabase ().insert ( DatabaseService.TABLE_NAME_Dog , null , valuesD );
        Log.i ( "data" , "dog " + animalId + "insert to database" );
    }
    public Dog getDog(String animalId)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_Animals, new String[] {
                COLUMN_AnimalId,
                        COLUMN_username,
                        COLUMN_password,
                        COLUMN_AnimalKind,
                        COLUMN_AnimalName,
                        COLUMN_AnimalRace,
                        COLUMN_AnimalGender,
                        COLUMN_AnimalBirthday,
                        COLUMN_AnimalLastHaircut,
                        COLUMN_AnimalLastShower,
                        COLUMN_mads}, COLUMN_AnimalId + "=?",
                new String[] { animalId }, null, null, null, null);

        cursor.moveToFirst();
        String username = cursor.getString(1);
        String password = cursor.getString(2);
        String kind = cursor.getString(3);
        String name = cursor.getString(4);
        String race = cursor.getString(5);
        String gender = cursor.getString(6);
        String birthday = cursor.getString(7);
        String hair = cursor.getString(8);
        String shower = cursor.getString(9);
        String mads = cursor.getString(10);

        /*
        Dog part
         */
        cursor = db.query(TABLE_NAME_Dog, new String[] {
                        COLUMN_numberOfTrips,
                        COLUMN_commands,
                        COLUMN_mealsAday}, COLUMN_AnimalId + "=?",
                new String[] { animalId }, null, null, null, null);

        cursor.moveToFirst();
        int trips = cursor.getInt(0);
        String commands = cursor.getString(1);
        int meals = cursor.getInt(2);

        Dog dog = new Dog( animalId , animalId, username, password, kind, name, race, gender, birthday, hair, shower, mads, trips, meals, commands);
        return dog;
    }
    //**************************************************************************
    public static final String TABLE_NAME_Cat = "Cats";

    public static final String COLUMN_CleanSandBoxDay = "CleanSandBoxDay";
    public static final String COLUMN_wantToCutNails = "wantToCutNails";
    public static final String COLUMN_eatsInAday = "eatsInAday";


    public static final String CREATE_TABLE_Cats =
            "CREATE TABLE " + TABLE_NAME_Cat + "("
                    + COLUMN_AnimalId + " VARCHAR PRIMARY KEY, "
                    + COLUMN_CleanSandBoxDay + " INTEGER,"
                    + COLUMN_eatsInAday + " INTEGER,"
                    + COLUMN_wantToCutNails + " VARCHAR" + ")";


    public void insertCat(String animalId, int cleansanboxday, String wanttocutnails, int eatsinaday) {
        ContentValues valuesC = new ContentValues ();
        valuesC.put ( COLUMN_AnimalId , animalId);
        valuesC.put ( COLUMN_CleanSandBoxDay , cleansanboxday);
        valuesC.put ( COLUMN_eatsInAday , eatsinaday);
        valuesC.put ( COLUMN_wantToCutNails , wanttocutnails );

        this.getWritableDatabase ().insert ( DatabaseService.TABLE_NAME_Cat , null , valuesC );
        Log.i ( "data" , "cat " + animalId + "insert to database" );
    }
    public Cat getCat(String animalId)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_Animals, new String[] {
                        COLUMN_AnimalId,
                        COLUMN_username,
                        COLUMN_password,
                        COLUMN_AnimalKind,
                        COLUMN_AnimalName,
                        COLUMN_AnimalRace,
                        COLUMN_AnimalGender,
                        COLUMN_AnimalBirthday,
                        COLUMN_AnimalLastHaircut,
                        COLUMN_AnimalLastShower,
                        COLUMN_mads}, COLUMN_AnimalId + "=?",
                new String[] { animalId }, null, null, null, null);

        cursor.moveToFirst();
        String username = cursor.getString(1);
        String password = cursor.getString(2);
        String kind = cursor.getString(3);
        String name = cursor.getString(4);
        String race = cursor.getString(5);
        String gender = cursor.getString(6);
        String birthday = cursor.getString(7);
        String hair = cursor.getString(8);
        String shower = cursor.getString(9);
        String mads = cursor.getString(10);

        /*
        Cat part
         */
        cursor = db.query(TABLE_NAME_Cat, new String[] {
                        COLUMN_CleanSandBoxDay,
                        COLUMN_wantToCutNails,
                        COLUMN_eatsInAday}, COLUMN_AnimalId + "=?",
                new String[] { animalId }, null, null, null, null);

        cursor.moveToFirst();
        int sandbox = cursor.getInt(0);
        String wanttocutnails = cursor.getString(1);
        int eatsaday = cursor.getInt(2);
        Cat cat = new Cat(animalId, username,password,kind,name,race,gender,birthday,hair,shower,mads,sandbox,wanttocutnails,eatsaday);
        return cat;
    }
    //**************************************************************************
    public static final String TABLE_NAME_Rabbit = "Rabbits";

    public static final String COLUMN_cleanHisCageDay = "cleanHisCageDay";
    public static final String COLUMN_mealsAdayR = "eatsInAday";

    public static final String CREATE_TABLE_Rabbits =
            "CREATE TABLE " + TABLE_NAME_Rabbit + "("
                    + COLUMN_AnimalId + " VARCHAR PRIMARY KEY, "
                    + COLUMN_cleanHisCageDay + "VARCHAR,"
                    + COLUMN_mealsAdayR + "INTEGER" + ")";

    public void insertRabbit(String animalId, int cleanhiscage, int mealsaday) {
        ContentValues valuesR = new ContentValues ();
        valuesR.put ( COLUMN_AnimalId , animalId );
        valuesR.put ( COLUMN_cleanHisCageDay , cleanhiscage );
        valuesR.put ( COLUMN_eatsInAday ,mealsaday );

        this.getWritableDatabase ().insert ( DatabaseService.TABLE_NAME_Rabbit , null , valuesR );
        Log.i ( "data" , "rabbit " + animalId + "insert to database" );
    }

    public Rabbit getRabbit(String animalId)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_Animals, new String[] {
                        COLUMN_AnimalId,
                        COLUMN_username,
                        COLUMN_password,
                        COLUMN_AnimalKind,
                        COLUMN_AnimalName,
                        COLUMN_AnimalRace,
                        COLUMN_AnimalGender,
                        COLUMN_AnimalBirthday,
                        COLUMN_AnimalLastHaircut,
                        COLUMN_AnimalLastShower,
                        COLUMN_mads}, COLUMN_AnimalId + "=?",
                new String[] { animalId }, null, null, null, null);

        cursor.moveToFirst();
        String username = cursor.getString(1);
        String password = cursor.getString(2);
        String kind = cursor.getString(3);
        String name = cursor.getString(4);
        String race = cursor.getString(5);
        String gender = cursor.getString(6);
        String birthday = cursor.getString(7);
        String hair = cursor.getString(8);
        String shower = cursor.getString(9);
        String mads = cursor.getString(10);

        /*
        Rabbit part
         */
        cursor = db.query(TABLE_NAME_Rabbit, new String[] {
                        COLUMN_cleanHisCageDay,
                        COLUMN_eatsInAday}, COLUMN_AnimalId + "=?",
                new String[] { animalId }, null, null, null, null);

        cursor.moveToFirst();
        int cage = cursor.getInt(0);
        int eats = cursor.getInt(2);
        Rabbit rabbit = new Rabbit(animalId, username,password,kind,name,race,gender,birthday,hair,shower,mads,cage,eats);
        return rabbit;
    }
    //**************************************************************************

    public static synchronized DatabaseService getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseService(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseService(Context context) {
        super ( context , DATABASENAME , null , DATABASEVERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("data",CREATE_TABLE_Animals );
        Log.i("data",CREATE_TABLE_Dogs );
        Log.i("data",CREATE_TABLE_Cats );
        Log.i("data",CREATE_TABLE_Rabbits );
        db.execSQL (CREATE_TABLE_Animals);
        db.execSQL (CREATE_TABLE_Dogs);
        db.execSQL (CREATE_TABLE_Cats);
        db.execSQL (CREATE_TABLE_Rabbits);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion) {
        if(oldVersion!=newVersion)
        {
            db.execSQL ( "DROP TABLE IF EXISTS " + TABLE_NAME_Animals );
            db.execSQL ( "DROP TABLE IF EXISTS " + TABLE_NAME_Dog );
            db.execSQL ( "DROP TABLE IF EXISTS " + TABLE_NAME_Cat );
            db.execSQL ( "DROP TABLE IF EXISTS " + TABLE_NAME_Rabbit );
            onCreate ( db );
        }
    }
}


