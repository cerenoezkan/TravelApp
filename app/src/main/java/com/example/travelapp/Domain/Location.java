package com.example.travelapp.Domain;

import androidx.annotation.NonNull;

public class Location {
    private int ID;
    private String Loc;

    public Location() {
    }

    @NonNull
    @Override
    public String toString() {
        return Loc ;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLoc() {
        return Loc;
    }

    public void setLoc(String loc) {
        Loc = loc;
    }
}
