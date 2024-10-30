package com.example.vidu1.model;

import java.io.Serializable;

public class SMS implements Serializable {
    private String name;
    private  String messenger;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public SMS(String name, String messenger) {
        this.name = name;
        this.messenger = messenger;
    }

    @Override
    public String toString() {
        return "Tên:"+name+"\nMessenger:"+messenger;
    }
}
