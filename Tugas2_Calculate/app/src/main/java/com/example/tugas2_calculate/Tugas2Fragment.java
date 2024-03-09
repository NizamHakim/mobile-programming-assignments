package com.example.tugas2_calculate;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Tugas2Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tugas2, container, false);

        EditText inputField = view.findViewById(R.id.input_equation_field);
        TextView resultField = view.findViewById(R.id.result_field);

        Button calculateButton = view.findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputField.getText().toString();
                String output = "";

                try {
                    Expression exp = new ExpressionBuilder(input).build();
                    Double result = exp.evaluate();
                    output = String.valueOf(result);
                } catch (Exception e) {
                    output = getResources().getString(R.string.nan);
                } finally {
                    resultField.setText(output);
                }
            }
        });

        Button exitButton = view.findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null){
                    getActivity().finish();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


}