package com.example.tugas6_listview;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tugas6_listview.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Button btn_show_dialog, btn_submit, btn_cancel;
    Dialog add_dialog;
    EditText et_NRP, et_Nama, et_NoHP;
    ShapeableImageView img_profile;
    FloatingActionButton btn_img;
    ListView listView;
    MhsAdapter mhsAdapter;
    Uri selected_img_uri;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DB = new DBHelper(this);
        listView = findViewById(R.id.listview);
        ArrayList<Mhs> listMhs = new ArrayList<>();
        mhsAdapter = new MhsAdapter(this, R.layout.list_row, listMhs);
        binding.listview.setAdapter(mhsAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mhs mhs = listMhs.get(position);
                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                intent.putExtra("NRP", mhs.NRP);
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        load_all_mhs();

        btn_show_dialog = findViewById(R.id.btn_show_dialog);
        btn_show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_img_uri = null;
                show_dialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mhsAdapter.clear();
        load_all_mhs();
    }

    private void load_all_mhs(){
        Cursor cursor = DB.readAll();
        if (cursor != null){
            cursor.moveToFirst();
            do {
                int colNRP = cursor.getColumnIndex("NRP");
                int colNama = cursor.getColumnIndex("Nama");
                int colNoHP = cursor.getColumnIndex("NoHP");
                int colImage = cursor.getColumnIndex("Image");
                Mhs mhs = new Mhs(cursor.getString(colNRP), cursor.getString(colNama), cursor.getString(colNoHP), cursor.getString(colImage));
                mhsAdapter.add(mhs);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void show_dialog() {
        add_dialog = new Dialog(this);
        add_dialog.setContentView(R.layout.input_dialog);
        add_dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_box));
        add_dialog.setCancelable(false);

        btn_cancel = add_dialog.findViewById(R.id.btn_cancel);
        btn_submit = add_dialog.findViewById(R.id.btn_submit);
        btn_img = add_dialog.findViewById(R.id.btn_img);
        img_profile = add_dialog.findViewById(R.id.img_profile);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_dialog.dismiss();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_mhs();
            }
        });
        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
            }
        });
        add_dialog.show();
    }

    ActivityResultLauncher<PickVisualMediaRequest> launcher = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri o) {
            if (o == null) {
                Toast.makeText(MainActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
            } else {
                Glide.with(getApplicationContext()).load(o).into(img_profile);
                selected_img_uri = o;
            }
        }
    });

    private void add_mhs() {
        et_NRP = add_dialog.findViewById(R.id.et_nrp);
        et_Nama = add_dialog.findViewById(R.id.et_nama);
        et_NoHP = add_dialog.findViewById(R.id.et_nohp);
        String NRP = et_NRP.getText().toString();
        String Nama = et_Nama.getText().toString();
        String NoHP = et_NoHP.getText().toString();
        String Image = "";
        if (selected_img_uri != null) Image = selected_img_uri.toString();

        if (NRP.isEmpty() || Nama.isEmpty() || NoHP.isEmpty()) {
            Toast.makeText(this, "Field may not be empty!", Toast.LENGTH_SHORT).show();
        } else if (Image.isEmpty()) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
        } else {
            Boolean created = DB.createMhs(NRP, Nama, NoHP, Image);
            if (created){
                Toast.makeText(this, "Added " + NRP + " to Database", Toast.LENGTH_SHORT).show();
                Cursor cursor = DB.readSingle(NRP);
                if (cursor != null){
                    int colNRP = cursor.getColumnIndex("NRP");
                    int colNama = cursor.getColumnIndex("Nama");
                    int colNoHP = cursor.getColumnIndex("NoHP");
                    int colImage = cursor.getColumnIndex("Image");
                    Mhs mhs = new Mhs(cursor.getString(colNRP), cursor.getString(colNama), cursor.getString(colNoHP), cursor.getString(colImage));
                    mhsAdapter.add(mhs);
                    cursor.close();
                }
                add_dialog.dismiss();
            } else {
                Toast.makeText(this, "Unable to create entry", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
