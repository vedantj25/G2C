package com.android.g2c;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import com.android.g2c.adapters.CheckListAdapter;
import com.android.g2c.helpers.FirebaseHelper;
import com.android.g2c.models.CheckItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DatabaseReference dbRef;
    FirebaseHelper helper;
    RecyclerView chkRV;
    CheckListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecycleView
        chkRV = findViewById(R.id.chkRecView);
        chkRV.setLayoutManager(new LinearLayoutManager(this));

        //Firebase Setup
        dbRef = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(dbRef);

        dbRef.child("CheckList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<CheckItem> checkList = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren())
                {
                    CheckItem item = ds.getValue(CheckItem.class);
                    item.setReference(ds.getRef());
                    checkList.add(item);
                }
                adapter=new CheckListAdapter(MainActivity.this, checkList);
                chkRV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        AlertDialog.Builder addDialog = new AlertDialog.Builder(this);
        addDialog.setView(R.layout.add_chk_layout);
        addDialog.create();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAddDialog();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void displayAddDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        View v = getLayoutInflater().inflate(R.layout.add_chk_layout, null);
        dialogBuilder.setView(v);
        AlertDialog d = dialogBuilder.create();

        EditText itemText = v.findViewById(R.id.chkItemText);
        Button addBtn = v.findViewById(R.id.addItemBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = itemText.getText().toString();

                //SET DATA
                CheckItem s= new CheckItem();
                s.setItem(item);

                //VALIDATE
                if(item.length()>0 && item != null)
                {
                    if(helper.save(s))
                    {
                        itemText.setText("");
//                        adapter=new CheckListAdapter(MainActivity.this,helper.retrieve());
//                        chkRV.setAdapter(adapter);
                        d.dismiss();
                    }
                }else
                {
                    Toast.makeText(MainActivity.this, "Item Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        d.show();
    }

    public static void  deleteItem(DatabaseReference ref){
        if(null != ref){
            ref.removeValue();
        }

    }
}

