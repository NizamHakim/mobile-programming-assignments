package com.example.tugas4_crud;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create_fragment extends Fragment {
    EditText createNRP, createName;
    Button createBtn;
    Context context;
    DBHelper DB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_fragment, container, false);

        createNRP = view.findViewById(R.id.nrp_edit_create);
        createName = view.findViewById(R.id.nama_edit_create);
        createBtn = view.findViewById(R.id.create_btn);
        context = getContext();
        DB = new DBHelper(context);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NRP = createNRP.getText().toString();
                String Nama = createName.getText().toString();
                Boolean created = DB.createMhs(NRP, Nama);

                if (created){
                    Toast.makeText(context, NRP + " added to database", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, " Unable to create entry", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}