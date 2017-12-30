package com.example.android.viewpager;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class AutoCompleteDbAdapter4 {



    public static final String TABLE_PRODUCTS = "stomach";
    public static final String COLUMN_ID = "_id";

    public class DataBaseHelper extends SQLiteOpenHelper {
        private Context mContext;
        private static final String DB_NAME = "apr2017.sqlite";
        private static final String DB_PATH ="/data/data/com.example.android.viewpager/databases/";
        public static final String TABLE_PRODUCTS = "stomach";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_METADATA = "android_metadata";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DRASTIKI = "drastiki";
        public static final String COLUMN_PROTO = "proto";
        public static final String COLUMN_DIO = "dio";
        /*public static final String COLUMN_POSOTITA = "posotita";*/
        public static final String COLUMN_ANAFORA = "anafora";





        public SQLiteDatabase mDb;

        public DataBaseHelper(Context context) throws IOException {
            super(context, DB_NAME, null, 1);
            this.mContext=context;
            boolean dbexist = checkdatabase();
            if (dbexist) {
                System.out.println("Database exists");
                /*Toast.makeText(context.getApplicationContext(),"DBexists",Toast.LENGTH_LONG).show();*/
                opendatabase();
            } else {
                System.out.println("Database doesn't exist");
                /*Toast.makeText(context.getApplicationContext(),"DBNOT",Toast.LENGTH_LONG).show();*/

                createdatabase();
            }
        }

        public void createdatabase() throws IOException {
            boolean dbexist = checkdatabase();
            if(dbexist) {
                System.out.println(" Database exists.");
            } else {
                this.getReadableDatabase();
                try {
                    copydatabase();
                    /*Toast.makeText(mContext.getApplicationContext(),"DBcopied",Toast.LENGTH_LONG).show();*/

                } catch(IOException e) {
                    throw new Error("Error copying database");

                }
            }
        }

        private boolean checkdatabase() {

            boolean checkdb = false;
            try {
                String myPath = DB_PATH + DB_NAME;
                File dbfile = new File(myPath);
                checkdb = dbfile.exists();
            } catch(SQLiteException e) {
                System.out.println("Database doesn't exist");
            }
            return checkdb;
        }

        private void copydatabase() throws IOException {
            //Open your local db as the input stream
            InputStream myinput = mContext.getAssets().open(DB_NAME);
            /*Toast.makeText(mContext.getApplicationContext(),"DBInput",Toast.LENGTH_LONG).show();*/


            // Path to the just created empty db
            String outfilename = DB_PATH + DB_NAME;

            //Open the empty db as the output stream
            OutputStream myoutput = new FileOutputStream(outfilename);

            // transfer byte to inputfile to outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myinput.read(buffer))>0) {
                myoutput.write(buffer,0,length);
            }

            //Close the streams
            myoutput.flush();
            myoutput.close();
            myinput.close();
        }

        public void opendatabase() throws SQLException {
            //Open the database
            String mypath = DB_PATH + DB_NAME;
            mDb = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READONLY);
            /*Toast.makeText(mContext.getApplicationContext(),"DBOpened",Toast.LENGTH_LONG).show();*/



        }

        public synchronized void close() {
            if(mDb != null) {
                mDb.close();
            }
            super.close();
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            final String DATABASE_CREATE_STATES =
                    /*"create table if not exists " + TABLE_PRODUCTS
                            + "(_id integer primary key autoincrement"
                            + ", name text not null"
                            + ", price text not null"
                            + ", description text)";*/
                    "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + "(" +
                            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            COLUMN_NAME + " TEXT " +
                            COLUMN_METADATA + " TEXT " +
                            COLUMN_DESCRIPTION + " TEXT " +
                            COLUMN_ANAFORA + " TEXT " +
                            COLUMN_DRASTIKI + " TEXT " +
                            COLUMN_PRICE + " TEXT " +
                            COLUMN_PROTO + " TEXT " +
                            COLUMN_DIO + " TEXT " +
                            ");";

            db.execSQL(DATABASE_CREATE_STATES);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
            onCreate(db);
            db.close();

        }


    }

    public DataBaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Activity mActivity;

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param activity
     *            the Activity that is using the database
     */
    public AutoCompleteDbAdapter4(Activity activity) throws IOException {
        this.mActivity = activity;
        mDbHelper = this.new DataBaseHelper(activity);
        mDb = mDbHelper.getWritableDatabase();
    }

    /**
     * Closes the database.
     */
    public void close() {
        mDbHelper.close();
    }




    public Cursor getMatchingStates(String constraint) throws SQLException {

        String queryString = "SELECT * FROM " + TABLE_PRODUCTS;

        if (constraint != null) {

            constraint = constraint.trim() + "%";
            queryString += " WHERE name LIKE ?";
        }
        String params[] = { constraint };

        if (constraint == null) {
            // If no parameters are used in the query,
            // the params arg must be null.
            params = null;
        }
        try {
            Cursor cursor = mDb.rawQuery(queryString, params);
            if (cursor != null) {
                /*this.mActivity.startManagingCursor(cursor);*/
                cursor.moveToFirst();
                return cursor;

            }
        }
        catch (SQLException e) {
            Log.e("AutoCompleteDbAdapter", e.toString());
            throw e;
        }

        return null;
    }

}

