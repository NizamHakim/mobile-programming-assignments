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
import android.widget.LinearLayout;
import android.widget.Toast;

public class update_fragment extends Fragment {

    EditText updateNRP, updateNama;
    LinearLayout btnContainer, updateNamaContainer;
    Button findBtn, cancelBtn, updateBtn;
    Context context;
    DBHelper DB;
    String oldNRP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_fragment, container, false);

        updateNRP = view.findViewById(R.id.nrp_edit_update);
        updateNama = view.findViewById(R.id.nama_edit_update);
        btnContainer = view.findViewById(R.id.btn_container);
        updateNamaContainer = view.findViewById(R.id.update_nama_container);
        findBtn = view.findViewById(R.id.update1_btn);
        cancelBtn = view.findViewById(R.id.update2_btn);
        updateBtn = view.findViewById(R.id.update3_btn);
        context = getContext();
        DB = new DBHelper(context);

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NRP = updateNRP.getText().toString();
                Cursor cursor = DB.readMhs(NRP);
                if (cursor != null){
                    int col = cursor.getColumnIndex("Nama");
                    String Nama = cursor.getString(col);
                    updateNama.setText(Nama);
                    updateNamaContainer.setVisibility(View.VISIBLE);
                    btnContainer.setVisibility(View.VISIBLE);
                    findBtn.setVisibility(View.GONE);
                    oldNRP = NRP;
                } else {
                    Toast.makeText(context, NRP + " not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNama.setText("");
                updateNamaContainer.setVisibility(View.GONE);
                btnContainer.setVisibility(View.GONE);
                findBtn.setVisibility(View.VISIBLE);
                oldNRP = null;
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNRP = updateNRP.getText().toString();
                String newNama = updateNama.getText().toString();
                Boolean updated = DB.updateMhs(oldNRP, newNRP, newNama);
                if (updated){
                    Toast.makeText(context, "Update succeed", Toast.LENGTH_SHORT).show();
                    updateNama.setText("");
                    oldNRP = null;
                    updateNamaContainer.setVisibility(View.GONE);
                    btnContainer.setVisibility(View.GONE);
                    findBtn.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}