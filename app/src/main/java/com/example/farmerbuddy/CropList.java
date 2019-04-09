package com.example.farmerbuddy;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CropList extends ArrayAdapter<Crop> {

    private Activity context;
    private List<Crop> cropList;

    public CropList(Activity context,List<Crop> cropList){
        super(context,R.layout.list_layout,cropList);
        this.context = context;
        this.cropList =cropList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.namecrop);
        TextView textViewQuan = (TextView)listViewItem.findViewById(R.id.quan);
        TextView textViewAdd = (TextView)listViewItem.findViewById(R.id.add);
        TextView textViewDet = (TextView)listViewItem.findViewById(R.id.det);
        TextView textViewPh = (TextView)listViewItem.findViewById(R.id.ph);

        Crop crop = cropList.get(position);

        textViewName.setText(crop.getCropname());
        textViewQuan.setText(crop.getQuantities());
        textViewAdd.setText(crop.getAddres());
        textViewDet.setText(crop.getDetail());
        textViewPh.setText(crop.getPhone());

        return listViewItem;

    }
}