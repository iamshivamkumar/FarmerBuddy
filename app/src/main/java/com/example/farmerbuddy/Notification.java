package com.example.farmerbuddy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notification extends Fragment implements View.OnClickListener {

    ListView listViewNotification;
    DatabaseReference databasenotify;
    String uid;
    List<Notify> notifyList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notification,null);

        uid = FirebaseAuth.getInstance().getUid();

        notifyList = new ArrayList<>();

        listViewNotification = (ListView) view.findViewById(R.id.notificationlistview);

        databasenotify = FirebaseDatabase.getInstance().getReference("Notify").child(uid);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databasenotify.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                notifyList.clear();

                for (DataSnapshot notifySnapshot : dataSnapshot.getChildren()){
                    Notify notify = notifySnapshot.getValue(Notify.class);
                    notifyList.add(notify);
                }

                NotifySeller adapter = new NotifySeller(getActivity(),notifyList);
                listViewNotification.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
