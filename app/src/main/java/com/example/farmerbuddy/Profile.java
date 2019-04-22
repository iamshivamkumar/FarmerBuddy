package com.example.farmerbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference databaseProfile;
    String first_name,last_name,add,phn;
    EditText fname, lname, address, phonenumber;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fname = (EditText) findViewById(R.id.firstName);
        lname = (EditText) findViewById(R.id.LastName);
        address = (EditText) findViewById(R.id.add);
        phonenumber = (EditText) findViewById(R.id.mobile);

        findViewById(R.id.save).setOnClickListener(this);

        uid = FirebaseAuth.getInstance().getUid();

        databaseProfile = FirebaseDatabase.getInstance().getReference();

    }

    private void data(){
        first_name = fname.getText().toString().trim();
        last_name = lname.getText().toString().trim();
        add = address.getText().toString().trim();
        phn = phonenumber.getText().toString().trim();
        if (first_name.isEmpty()) {
            fname.setError("First name is required");
            fname.requestFocus();
            return;
        }

        if (last_name.isEmpty()) {
            lname.setError("Last name is required");
            lname.requestFocus();
            return;
        }

        if (add.isEmpty()) {
            address.setError("Address is required");
            address.requestFocus();
            return;
        }

        if (phn.isEmpty()) {
            phonenumber.setError("Phone number is required");
            phonenumber.requestFocus();
            return;
        }

        if (!TextUtils.isEmpty(first_name) && !TextUtils.isEmpty(last_name) && !TextUtils.isEmpty(add) && !TextUtils.isEmpty(phn)) {

            ProfileData profileData = new ProfileData(first_name,last_name,add,phn);

            databaseProfile.child("Profile").child(uid).setValue(profileData);

            Intent home = new Intent(Profile.this,HomePage.class);
            startActivity(home);
            finish();

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                data();
                break;
        }
    }
}