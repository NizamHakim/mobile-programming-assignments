package com.example.tugas3_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edit_bilangan_1, edit_bilangan_2;
    TextView result_field;
    Button btn_tambah, btn_kurang, btn_kali, btn_bagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_bilangan_1 = findViewById(R.id.edit_bilangan_1);
        edit_bilangan_2 = findViewById(R.id.edit_bilangan_2);
        result_field = findViewById(R.id.result_field);

        btn_tambah = findViewById(R.id.btn_tambah);
        btn_kurang = findViewById(R.id.btn_kurang);
        btn_kali = findViewById(R.id.btn_kali);
        btn_bagi = findViewById(R.id.btn_bagi);

        btn_tambah.setOnClickListener(this);
        btn_kurang.setOnClickListener(this);
        btn_kali.setOnClickListener(this);
        btn_bagi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        float bilangan_1, bilangan_2, result = 0;
        Button btn;

        String str_bilangan_1 = edit_bilangan_1.getText().toString();
        String str_bilangan_2 = edit_bilangan_2.getText().toString();

        if (str_bilangan_1.isEmpty() || str_bilangan_2.isEmpty()) {
            result_field.setText(R.string.error);
            return;
        }

        bilangan_1 = Float.parseFloat(str_bilangan_1);
        bilangan_2 = Float.parseFloat(str_bilangan_2);

        int btn_pressed = v.getId();
        if (btn_pressed == R.id.btn_tambah){
            result = bilangan_1 + bilangan_2;
        } else if (btn_pressed == R.id.btn_kurang) {
            result = bilangan_1 - bilangan_2;
        } else if (btn_pressed == R.id.btn_kali) {
            result = bilangan_1 * bilangan_2;
        } else if (btn_pressed == R.id.btn_bagi) {
            result = bilangan_1 / bilangan_2;
        }

        btn = findViewById(btn_pressed);
        String return_text = bilangan_1 + " " + btn.getText() + " " + bilangan_2 + " = " + result;
        result_field.setText(return_text);
    }
}