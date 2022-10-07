package com.android.g2c.helpers;

import androidx.annotation.NonNull;

import com.android.g2c.models.CheckItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved=null;
    static ArrayList<String> checklist;

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
        checklist=new ArrayList<>();
    }

    //SAVE
    public Boolean save(CheckItem checkItem)
    {
        if(checkItem==null)
        {
            saved=false;
        }else {

            try
            {
                db.child("CheckList").push().setValue(checkItem);
                saved=true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }

        }

        return saved;
    }



    //READ
    public ArrayList<String> retrieve()
    {
        db.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                fetchData(dataSnapshot);
            }
        });

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return checklist;
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        checklist.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            String item=ds.getValue(CheckItem.class).getitem();
            checklist.add(item);
        }
    }

}