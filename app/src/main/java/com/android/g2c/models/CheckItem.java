package com.android.g2c.models;

import com.google.firebase.database.DatabaseReference;

import java.util.Date;

public class CheckItem{
    private String item;
    private DatabaseReference reference;

    public CheckItem(){
    }

    CheckItem( String item, Date createdDateTime ){
        this.item = item;

    }

    public String getitem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public DatabaseReference getReference() {
        return reference;
    }

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }
}