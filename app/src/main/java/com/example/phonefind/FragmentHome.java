package com.example.phonefind;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentHome extends Fragment {

    Button btn_saveDevice, btn_findDevice;
    TextView tv_name;

    FirebaseUser mFirebaseUser;
    DatabaseReference mDatabaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        btn_saveDevice = (Button) rootView.findViewById(R.id.btn_save_device);
        btn_findDevice = (Button) rootView.findViewById(R.id.btn_find_device);
        tv_name = (TextView) rootView.findViewById(R.id.et_name);

        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        tv_name.setText(mFirebaseUser.getEmail());

        Toast.makeText(getContext(), mFirebaseUser.getEmail(), Toast.LENGTH_SHORT);


        btn_saveDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),SaveDeviceActivity.class);
                startActivity(i);
            }
        });

        btn_findDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),LocationActivity.class);
                startActivity(i);
            }
        });


        return rootView;

    }
}
