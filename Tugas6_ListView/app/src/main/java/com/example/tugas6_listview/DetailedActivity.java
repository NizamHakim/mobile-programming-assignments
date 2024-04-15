package com.example.tugas6_listview;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tugas6_listview.databinding.ActivityDetailedBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

public class DetailedActivity extends AppCompatActivity {
    ActivityDetailedBinding binding;
    DBHelper DB;
    String[] args;
    EditText et_nrp_update, et_nama_update, et_nohp_update;
    ShapeableImageView img_profile_update;
    Uri selected_img_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DB = new DBHelper(this);

        Intent intent = this.getIntent();
        if (intent != null){
            String NRP = intent.getStringExtra("NRP");
            Cursor cursor = DB.readSingle(NRP);
//            int colNRP = cursor.getColumnIndex("NRP");
            int colNama = cursor.getColumnIndex("Nama");
            int colNoHP = cursor.getColumnIndex("NoHP");
            int colImage = cursor.getColumnIndex("Image");

            binding.detailNrp.setText(NRP);
            binding.detailNama.setText(cursor.getString(colNama));
            binding.detailNohp.setText(cursor.getString(colNoHP));
            Glide.with(this).load(Uri.parse(cursor.getString(colImage))).into(binding.imgProfile);

            args = new String[]{NRP, cursor.getString(colNama), cursor.getString(colNoHP), cursor.getString(colImage)};
        }

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog edit_dialog = new Dialog(DetailedActivity.this);
                show_edit_dialog(edit_dialog, args);
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execute_delete(args[0]);
            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void show_edit_dialog(Dialog edit_dialog, String[] args){
        edit_dialog.setContentView(R.layout.edit_dialog);
        edit_dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_box));
        edit_dialog.setCancelable(false);

        img_profile_update = edit_dialog.findViewById(R.id.img_profile_update);
        et_nrp_update = edit_dialog.findViewById(R.id.et_nrp_update);
        et_nama_update = edit_dialog.findViewById(R.id.et_nama_update);
        et_nohp_update = edit_dialog.findViewById(R.id.et_nohp_update);

        et_nrp_update.setText(args[0]);
        et_nama_update.setText(args[1]);
        et_nohp_update.setText(args[2]);
        Glide.with(this).load(Uri.parse(args[3])).into(img_profile_update);
        selected_img_uri = Uri.parse(args[3]);

        Button btn_cancel_update = edit_dialog.findViewById(R.id.btn_cancel_update);
        Button btn_update = edit_dialog.findViewById(R.id.btn_update);
        FloatingActionButton btn_img_update = edit_dialog.findViewById(R.id.btn_img_update);

        btn_cancel_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_dialog.dismiss();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execute_update(args[0]);
                edit_dialog.dismiss();
            }
        });
        btn_img_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
            }
        });

        edit_dialog.show();
    }

    ActivityResultLauncher<PickVisualMediaRequest> launcher = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri o) {
            if (o == null) {
                Toast.makeText(DetailedActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
            } else {
                Glide.with(getApplicationContext()).load(o).into(img_profile_update);
                selected_img_uri = o;
            }
        }
    });

    private void execute_update(String identifier){
        String[] newData = new String[]{
                et_nrp_update.getText().toString(),
                et_nama_update.getText().toString(),
                et_nohp_update.getText().toString(),
                selected_img_uri.toString()
        };
        Boolean updated = DB.updateMhs(identifier, newData);
        if (updated){
            Toast.makeText(this, "Update succeed", Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(DetailedActivity.this, DetailedActivity.class);
            intent.putExtra("NRP", newData[0]);
            overridePendingTransition(0, 0);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void execute_delete(String identifier){
        Boolean deleted = DB.deleteMhs(identifier);
        if (deleted) {
            Toast.makeText(this, "Delete succeed", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Delete failed", Toast.LENGTH_SHORT).show();
        }
    }
}