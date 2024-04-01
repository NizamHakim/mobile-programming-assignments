package com.example.tugas5_modaldialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText dialog_input;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        Button btn_= findViewById(R.id.btn_show_dialog);
        btn_.setOnClickListener(show_dialog);
    }

    View.OnClickListener show_dialog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_show_dialog){
                show_input();
            }
        }
    };

    private void show_input(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View dialog_view = layoutInflater.inflate(R.layout.input_dialog, null);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(dialog_view);

        dialog_input = dialog_view.findViewById(R.id.dialog_input);
        dialog.setCancelable(false).setPositiveButton("Ok", execute).setNegativeButton("Cancel", execute);
        dialog.show();
    }

    DialogInterface.OnClickListener execute = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case -1: result.setText(dialog_input.getText().toString());
                    break;
                case -2:
                    break;
            }
        }
    };
}