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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellFragment extends Fragment implements View.OnClickListener {

    EditText cropname, quantity, address, details, phonenumber;
    DatabaseReference databaseCrop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.sell_page,null);

        cropname = (EditText) view.findViewById(R.id.cropname);
        quantity = (EditText) view.findViewById(R.id.quantity);
        address = (EditText) view.findViewById(R.id.address);
        details = (EditText) view.findViewById(R.id.details);
        phonenumber = (EditText) view.findViewById(R.id.phonenumber);
        view.findViewById(R.id.buttonsell).setOnClickListener(this);
        databaseCrop = FirebaseDatabase.getInstance().getReference("Crops");

       return view;
    }
    private void addcrop() {

        String cropName = cropname.getText().toString().trim();
        String quantities = quantity.getText().toString().trim();
        String addres = address.getText().toString().trim();
        String detail = details.getText().toString().trim();
        String phone = phonenumber.getText().toString().trim();

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
        if (addres.isEmpty()) {
            details.setError("Address is required");
            details.requestFocus();
            return;
        }
        if (detail.isEmpty()) {
            details.setError("Details is required");
            details.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            phonenumber.setError("Details is required");
            phonenumber.requestFocus();
            return;
        }
        if (!TextUtils.isEmpty(cropName) && !TextUtils.isEmpty(quantities) && !TextUtils.isEmpty(detail) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(addres)) {
            String id = databaseCrop.push().getKey();

            Crop crop = new Crop(id, cropName, quantities, addres, detail, phone);

            databaseCrop.child(id).setValue(crop);

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
