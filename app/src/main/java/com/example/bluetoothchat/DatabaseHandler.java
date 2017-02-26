package com.example.bluetoothchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "ChatManager";

    // Contacts table name
    private static final String TABLE_NAME = "Person";

    // Contacts Table Columns names
    private static final String KEY_PERSONNAME = "personName";
    private static final String KEY_COMMUNICATION = "id";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TIMESTAMP = "timestamp";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("inside","inside on create");
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_PERSONNAME + " TEXT," + KEY_COMMUNICATION + " INTEGER," + KEY_MESSAGE + " TEXT,"
                + KEY_TIMESTAMP + " TIMESTAMP" + ")";
        Log.e("hello ",CREATE_CONTACTS_TABLE);
        String CREATE_PERSONS_TABLE = "CREATE TABLE WHICHPERSONS ( person TEXT PRIMARY KEY)";
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_PERSONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.e("inside onUpgrade","insinidnd");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS WHICHPERSONS");
        // Create tables again
        onCreate(db);
    }

    void addChat(ChatColumn chat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PERSONNAME, chat.getPersonName()); // Contact Name
        values.put(KEY_COMMUNICATION, chat.getCommunication());
        values.put(KEY_MESSAGE,chat.getMessage());
        values.put(KEY_TIMESTAMP,chat.getTimeStamp());
        // Inserting Row
        Log.e("the chat column is ", chat.getPersonName() + " " + Integer.toString(chat.getCommunication()) + " " + chat.getMessage() + " " + chat.getTimeStamp() );
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    void addPerson(String deviceName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("person",deviceName);
        Log.e("WHICHPERSONS",deviceName);
        db.insert("WHICHPERSONS", null, values);
        db.close();

    }

    public List<ChatColumn> getAllContacts(String personName) {
        List<ChatColumn> chats = new ArrayList<ChatColumn>();
        // Select All Query
        String selectQuery = "SELECT  personName, id, message,timestamp FROM " + TABLE_NAME + " where personName = '" + personName + "' order by timestamp asc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ChatColumn chatHistory = new ChatColumn();
                Log.e("order is ",cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3));
                chatHistory.setPersonName(cursor.getString(0));
                chatHistory.setCommunication(Integer.parseInt(cursor.getString(1)));
                chatHistory.setMessage(cursor.getString(2));
                chatHistory.setTimeStamp(cursor.getString(3));
                // Adding contact to list
                chats.add(chatHistory);
            } while (cursor.moveToNext());
        }
        // return contact list
        return chats;
    }

    public List<String> getAllPersons()
    {
        List<String> nameOfPersons = new ArrayList<String>();
        String selectQuery = "SELECT person from WHICHPERSONS";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String person = cursor.getString(0);
                // Adding contact to list
                nameOfPersons.add(person);
            } while (cursor.moveToNext());
        }
        // return contact list
        return nameOfPersons;
    }
}
