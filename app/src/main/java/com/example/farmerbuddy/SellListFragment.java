package com.example.farmerbuddy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SellListFragment extends Fragment implements View.OnClickListener {
    ListView listViewCrop;
    DatabaseReference databaseCrop;
    List<Crop> cropList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sell_list,null);

        cropList = new ArrayList<>();

        listViewCrop = (ListView) view.findViewById(R.id.croplistview);

        databaseCrop = FirebaseDatabase.getInstance().getReference("Crops");

        return view;
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onStart() {
        super.onStart();

        databaseCrop.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                cropList.clear();

                for (DataSnapshot cropSnapshot : dataSnapshot.getChildren()){
                    Crop crop = cropSnapshot.getValue(Crop.class);
                    cropList.add(crop);
                }

                CropList adapter = new CropList(getActivity(),cropList);
                listViewCrop.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
