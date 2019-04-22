package com.example.farmerbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
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
    public static final String cropname ="cropname";
    public static final String username ="username";
    public static final String userid = "Userid";

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sell_list,null);

        cropList = new ArrayList<>();

        listViewCrop = (ListView) view.findViewById(R.id.croplistview);

        databaseCrop = FirebaseDatabase.getInstance().getReference("Crops");

        listViewCrop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Crop crop = cropList.get(position);

                Intent intent = new Intent(getActivity(), Buy.class);
                intent.putExtra(username,crop.getUsername());
                intent.putExtra(cropname,crop.getCropname());
                intent.putExtra(userid,crop.getId());
                startActivity(intent);

            }
        });

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