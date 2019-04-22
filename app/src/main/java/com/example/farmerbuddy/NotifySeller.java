package com.example.farmerbuddy;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NotifySeller extends ArrayAdapter<Notify> {

    private Activity context;
    private List<Notify> notifySeller;


    public NotifySeller(Activity context,List<Notify> notifySeller){
        super(context,R.layout.notification_list,notifySeller);
        this.context = context;
        this.notifySeller = notifySeller;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.notification_list,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.crop_name);
        TextView textViewUsername = (TextView)listViewItem.findViewById(R.id.username);
        TextView textViewQuan = (TextView)listViewItem.findViewById(R.id.quant);
        TextView textViewAdd = (TextView)listViewItem.findViewById(R.id.addres);
        TextView textViewPh = (TextView)listViewItem.findViewById(R.id.phon);

        Notify notify = notifySeller.get(position);

        textViewName.setText(notify.getCropname());
        textViewUsername.setText(notify.getUsername());
        textViewQuan.setText(notify.getQuantities());
        textViewAdd.setText(notify.getAddres());
        textViewPh.setText(notify.getPhone());


        return listViewItem;

    }
}
