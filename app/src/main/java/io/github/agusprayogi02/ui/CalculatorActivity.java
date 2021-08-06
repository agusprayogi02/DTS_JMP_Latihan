package io.github.agusprayogi02.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

import io.github.agusprayogi02.R;

public class CalculatorActivity extends AppCompatActivity {

    private GridLayout group;
    private TextView tvPalette;
    private TextView tvHasil;
    private double[] inputs;
    private String[] operators;
    private String inputData = "";
    private String inputData2 = "";
    private String operator = "";
    private String hasilSemua = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        initViews();
    }

    private void initViews() {
        group = findViewById(R.id.calGroup);
        MaterialToolbar tb = findViewById(R.id.tb);
        tb.setNavigationOnClickListener(view -> finish());
        tvPalette = findViewById(R.id.tvTampil);
        tvHasil = findViewById(R.id.tvHistori);
        for (int i = 0; i < group.getChildCount(); i++) {
            Button btn = (Button) group.getChildAt(i);
            btn.setOnClickListener(this::btnOnClick);
        }
    }

    private void btnOnClick(View view) {
        Button btn = (Button) view;
        String btnText = btn.getText().toString();
        String hasil = tvHasil.getText().toString();
        String tampil = tvPalette.getText().toString();

        if (btnText.equalsIgnoreCase("ac")) {
            tvHasil.setText("");
            tvPalette.setText("");
//            inputs.clear();
//            operators.clear();
            clearAll();
            return;
        }
        if (btnText.equalsIgnoreCase("c")) {
            tvPalette.setText("");
//            inputs.clear();
//            operators.clear();
            clearAll();
            return;
        }

        if (tampil.equals("")) {
            switch (btnText) {
                case "/":
                case "X":
                case "-":
                case "+":
                case "=":
                case "%":
                case "√":
                    return;
                case ".":
                    tvPalette.setText("0.");
                    return;
            }
            inputData += btnText;
        } else {
            inputs = new double[2];
            operators = new String[2];
            if (btnText.equals("/") || btnText.equalsIgnoreCase("X") || btnText.equals("%") || btnText.equals("+") || btnText.equals("-") || btnText.equalsIgnoreCase("√")) {
//                inputs.add(Double.parseDouble(tampil));
//                operators.add(tampil);
                inputData2 = inputData;
                inputData = "";
                operator = btnText;
                tvPalette.setText(MessageFormat.format("{0}{1}", tampil, btnText));
                return;
            }
            if (!btnText.equalsIgnoreCase("=")) {
                inputData += btnText;
            }
        }

//        cek jika memanggil =
        if (btnText.equals("=")) {
//            Log.d("ini Sama dengan: ", String.valueOf(inputs.size() ));
//            Log.d("ini Sama dengan: ", String.valueOf(operators.size() ));
            Log.d("ini Sama dengan: ", String.valueOf(inputData));
            Log.d("ini Sama dengan: ", String.valueOf(inputData2));
            Log.d("ini Sama dengan: ", String.valueOf(operator));
//            if (inputs.size() >= 2 && operators.size()  >= 1) {
            if (!inputData2.equals("") && !inputData.equals("")) {
//                inputs.add(Double.parseDouble(inputData));
//                Log.d("ini Sama dengan: ", String.valueOf(inputs.size() ));

                hasil = hasilCekOperator(Double.parseDouble(inputData2), operator.toLowerCase(Locale.ROOT), Double.parseDouble(inputData));
//                for (int i = 0; i < inputs.size() - 1; i++) {
//                    hasil = hasilCekOperator(inputs.get(i), operators.get(i), inputs.get(i+1));
//                }
                tvPalette.setText("");
                hasilSemua += inputData2 + operator + inputData + " = " + hasil + "\n";
                tvHasil.setText(hasilSemua);
                clearAll();
            }
            return;
        }
        tvPalette.setText(MessageFormat.format("{0}{1}", tampil, btnText));
    }

    private void clearAll() {
        inputData2 = "";
        inputData = "";
        operator = "";
    }

    private void cekOperator(double input, String operator, double input1) {

    }

    private String hasilCekOperator(double input, String operator, double input1) {
        switch (operator) {
            case "/":
                return String.valueOf(input / input1);
            case "x":
                return String.valueOf(input * input1);
            case "-":
                return String.valueOf(input - input1);
            case "+":
                return String.valueOf(input + input1);
            case "%":
                return String.valueOf(input % input1);
            case "√":
                double val = 0;
                for (int i = 0; i < input; i++) {
                    val *= input1;
                }
                return String.valueOf(val);
            default:
                return "";
        }
    }
}