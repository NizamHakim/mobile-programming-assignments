package com.example.tugas4_crud;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class read_fragment extends Fragment {

    EditText readNRP;
    Button readBtn;
    TextView resultNRP, resultNama, entryNotFound;
    Context context;
    DBHelper DB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_read_fragment, container, false);

        readNRP = view.findViewById(R.id.nrp_edit_read);
        readBtn = view.findViewById(R.id.read_btn);
        resultNRP = view.findViewById(R.id.result_nrp);
        resultNama = view.findViewById(R.id.result_name);
        entryNotFound = view.findViewById(R.id.entry_not_found);
        context = getContext();
        DB = new DBHelper(context);

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NRP = readNRP.getText().toString();
                Cursor cursor = DB.readMhs(NRP);
                if (cursor != null){
                    int col = cursor.getColumnIndex("Nama");
                    String Nama = cursor.getString(col);
                    String showNRP = "NRP    : " + NRP;
                    String showNama = "Name : " + Nama;
                    resultNRP.setText(showNRP);
                    resultNama.setText(showNama);
                    entryNotFound.setVisibility(View.GONE);
                    resultNRP.setVisibility(View.VISIBLE);
                    resultNama.setVisibility(View.VISIBLE);
                    Toast.makeText(context, NRP + " found", Toast.LENGTH_SHORT).show();
                } else {
                    entryNotFound.setVisibility(View.VISIBLE);
                    resultNRP.setVisibility(View.GONE);
                    resultNama.setVisibility(View.GONE);
                    Toast.makeText(context, NRP + " not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}