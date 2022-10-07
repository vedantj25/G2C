package com.android.g2c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String[] textString = {"Air filter cleaning", "Spark plug cleaning", "Brake shoe/pad rubbing", "Front back drum rubbing", "Chain and sprooket adjustment", "Clutch cable freeplay", "Engine Oil check", "Front and rear wheel axle greasing"};

        CustomAdapter adapter = new CustomAdapter(this,  textString);


        listView1 = (ListView)findViewById(R.id.menuList);
        listView1.setAdapter(adapter);
        listView1.setScrollContainer(false);
    }
}

class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private String[]  Title;

    public CustomAdapter(Context context, String[] text1) {
        mContext = context;
        Title = text1;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return Title.length;
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row;
        row = inflater.inflate(R.layout.row, parent, false);
        TextView title;
        ImageView i1;
        i1 = (ImageView) row.findViewById(R.id.imgIcon);
        title = (TextView) row.findViewById(R.id.txtTitle);
        title.setText(Title[position]);
        i1.setImageResource(R.drawable.check_asset);

        return (row);
    }
}