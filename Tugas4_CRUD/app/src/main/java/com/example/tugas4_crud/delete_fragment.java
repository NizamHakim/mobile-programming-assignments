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

public class delete_fragment extends Fragment {

    EditText deleteNRP;
    Button deleteBtn;
    Context context;
    DBHelper DB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delete_fragment, container, false);

        deleteNRP = view.findViewById(R.id.nrp_edit_delete);
        deleteBtn = view.findViewById(R.id.delete_btn);
        context = getContext();
        DB = new DBHelper(context);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NRP = deleteNRP.getText().toString();
                Boolean deleted = DB.deleteMhs(NRP);
                if (deleted){
                    Toast.makeText(context, NRP + " deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Unable to delete entry", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}