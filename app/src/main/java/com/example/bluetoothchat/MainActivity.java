package com.example.bluetoothchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.Iterator;
import java.util.List;


public class MainActivity extends Activity {

    public static DatabaseHandler database;
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new DatabaseHandler(this);
        List<String> persons = database.getAllPersons();
        Iterator<String> it = persons.iterator();
        while(it.hasNext())
        {
            String device = it.next();
            Log.e("Devices are ", device + " ");
            List<ChatColumn> chats = database.getAllContacts(device);
            Iterator<ChatColumn> its = chats.iterator();
            while(its.hasNext())
            {
                ChatColumn temp = its.next();
                Log.e("Chats are",temp.getPersonName() + " " + Integer.toString(temp.getCommunication()) + " " + temp.getMessage() + " " + temp.getTimeStamp() + " ");
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.scan:
                Intent intent = new Intent(MainActivity.this, BluetoothChat.class);
                startActivity(intent);
                return true;
            case R.id.discoverable:
                return true;
            case R.id.refresh:
                // refresh
                return true;
            case R.id.changeWallpaper:
                // help action
                return true;
            case R.id.checkForUpdates:
                // check for updates action
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
