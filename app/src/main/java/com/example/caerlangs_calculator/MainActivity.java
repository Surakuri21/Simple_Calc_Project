package com.example.caerlangs_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private double firstValue = 0;
    private String operator = "";
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        // Arrays of number button IDs
        int[] numberIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

        // Set Click Listener for Numbers
        View.OnClickListener numListener = v -> {
            Button b = (Button) v;
            if (isNewOp) {
                tvDisplay.setText(b.getText());
                isNewOp = false;
            } else {
                tvDisplay.append(b.getText());
            }
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numListener);
        }

        // Operation Click Listener (Add, Sub, etc)
        View.OnClickListener opListener = v -> {
            Button b = (Button) v;
            firstValue = Double.parseDouble(tvDisplay.getText().toString());
            operator = b.getText().toString();
            isNewOp = true;
        };

        findViewById(R.id.btnAdd).setOnClickListener(opListener);
        findViewById(R.id.btnSub).setOnClickListener(opListener);
        findViewById(R.id.btnMul).setOnClickListener(opListener);
        findViewById(R.id.btnDiv).setOnClickListener(opListener);

        // Clear Button
        findViewById(R.id.btnC).setOnClickListener(v -> {
            tvDisplay.setText("0");
            firstValue = 0;
            isNewOp = true;
        });

        // Equals Button
        findViewById(R.id.btnEqual).setOnClickListener(v -> {
            double secondValue = Double.parseDouble(tvDisplay.getText().toString());
            double result = 0;

            switch (operator) {
                case "+": result = firstValue + secondValue; break;
                case "-": result = firstValue - secondValue; break;
                case "*": result = firstValue * secondValue; break;
                case "/": result = firstValue / secondValue; break;
            }
            tvDisplay.setText(String.valueOf(result));
            isNewOp = true;
        });
    }
}