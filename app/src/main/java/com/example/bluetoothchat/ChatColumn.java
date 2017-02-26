package com.example.bluetoothchat;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatColumn {

    public int communication = 0;
    public String personName = "";
    //0 - sent incomingmessage and 1- recieved incomingmessage
    public String message = "";
    public String timeStamp;
    // sent incomingmessage or recieved incomingmessage

    public ChatColumn(int comm, String mess,String pers)
    {
        this.communication = comm;
        this.message = mess;
        this.personName = pers;
        this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }
    public ChatColumn()
    {
        communication=0;
        message="Data not recieved";
        personName = " ";
        timeStamp = "";
    }

    public String getMessage()
    {
        return message;
    }
    public void setTimeStamp(String time)
    {
        this.timeStamp = time;
    }

    public String getTimeStamp()
    {
        return timeStamp;
    }

    public String getPersonName()
    {
        return personName;
    }
    public void setPersonName(String person)
    {
        this.personName = person;
    }
    public int getCommunication()
    {
        return communication;
    }
    public void setCommunication(int comm)
    {
        this.communication = comm;
    }
    public void setMessage(String mess)
    {
        this.message = mess;
    }
}
