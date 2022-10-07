package com.android.g2c.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.android.g2c.MainActivity;
import com.android.g2c.R;
import com.android.g2c.RecyclerViewHolder;
import com.android.g2c.models.CheckItem;
import com.google.firebase.FirebaseCommonRegistrar;

import java.util.ArrayList;


public class CheckListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    Context c;
    ArrayList<CheckItem> checklist;

    public CheckListAdapter(Context c, ArrayList<CheckItem> checklist) {
        this.c = c;
        this.checklist = checklist;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.check_item_view,parent,false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        CheckItem item = checklist.get(position);
        holder.itemTxt.setText(item.getitem());
        holder.itemTxt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(c);
                View v = LayoutInflater.from(c).inflate(R.layout.rem_chk_layout, null);
                dialogBuilder.setView(v);
                AlertDialog dialog = dialogBuilder.create();
                Button deleteButton = v.findViewById(R.id.delItemBtn);

                deleteButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MainActivity.deleteItem(item.getReference());
                                dialog.dismiss();
                            }
                        });
                dialog.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return checklist.size();
    }
}