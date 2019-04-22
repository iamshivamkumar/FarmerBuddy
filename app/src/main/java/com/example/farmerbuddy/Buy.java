package com.example.farmerbuddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Buy extends AppCompatActivity implements View.OnClickListener{
    TextView cropnameTextview;
    String addres,phone,uid,username;
    EditText quantity;
    DatabaseReference databaseCrop,databaseProfile;
    private String cropname,userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Intent intent =getIntent();

        userid =intent.getStringExtra(SellListFragment.userid);
        cropname =intent.getStringExtra(SellListFragment.cropname);
        username =intent.getStringExtra(SellListFragment.username);
        quantity = (EditText) findViewById(R.id.quantity);

        cropnameTextview = (TextView)findViewById(R.id.cropname);
        cropnameTextview.setText(cropname);
        findViewById(R.id.buy).setOnClickListener(this);

        uid = FirebaseAuth.getInstance().getUid();

        databaseCrop = FirebaseDatabase.getInstance().getReference();
        databaseProfile = FirebaseDatabase.getInstance().getReference("Profile");

    }

    @Override
    public void onStart() {
        super.onStart();

        databaseProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                addres = (String) dataSnapshot.child(uid).child("address").getValue();

                phone = (String) dataSnapshot.child(uid).child("phone").getValue();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void buy()
    {

        String quantities = quantity.getText().toString().trim();

        if (quantities.isEmpty()) {
            quantity.setError("Quantity is required");
            quantity.requestFocus();
            return;
        }

        if (!TextUtils.isEmpty(quantities) ) {

            Notify notify = new Notify(userid,username,cropname,quantities,addres,phone);

            databaseCrop.child("Notify").child(userid).push().setValue(notify);

            Toast.makeText(getApplicationContext(), "Purchased", Toast.LENGTH_SHORT).show();

            finish();
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy:
                buy();
                break;
        }
    }
}
