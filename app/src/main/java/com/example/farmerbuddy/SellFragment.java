package com.example.farmerbuddy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class SellFragment extends Fragment implements View.OnClickListener {
    private String uid;
    String addres,phone,username,fname,lname;
    EditText cropname, quantity, address, details, phonenumber;
    DatabaseReference databaseCrop,databaseProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sell_page,null);

        cropname = (EditText) view.findViewById(R.id.cropname);
        quantity = (EditText) view.findViewById(R.id.quantity);
        details = (EditText) view.findViewById(R.id.details);


        view.findViewById(R.id.buttonsell).setOnClickListener(this);

        uid = FirebaseAuth.getInstance().getUid();

        databaseProfile=FirebaseDatabase.getInstance().getReference("Profile");

        databaseCrop = FirebaseDatabase.getInstance().getReference("Crops");

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        databaseProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            addres = (String) dataSnapshot.child(uid).child("address").getValue();

            phone = (String) dataSnapshot.child(uid).child("phone").getValue();

            fname = (String) dataSnapshot.child(uid).child("firstname").getValue();

            lname = (String) dataSnapshot.child(uid).child("lastname").getValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addcrop() {

        String cropName = cropname.getText().toString().trim();
        String quantities = quantity.getText().toString().trim();
        String detail = details.getText().toString().trim();
        username = fname+" "+lname;

        if (cropName.isEmpty()) {
            cropname.setError("Crop name is required");
            cropname.requestFocus();
            return;
        }
        if (quantities.isEmpty()) {
            quantity.setError("Quantity is required");
            quantity.requestFocus();
            return;
        }

        if (detail.isEmpty()) {
            details.setError("Details is required");
            details.requestFocus();
            return;
        }

        if (!TextUtils.isEmpty(cropName) && !TextUtils.isEmpty(quantities) && !TextUtils.isEmpty(detail)  ) {

            Crop crop = new Crop(uid, username,cropName, quantities, addres, detail, phone);

            databaseCrop.push().setValue(crop);

            Toast.makeText(getActivity(), "Crop Listed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonsell:
                addcrop();
                break;
        }
    }
}