package io.github.agusprayogi02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import io.github.agusprayogi02.ui.CalculatorActivity;
import io.github.agusprayogi02.ui.CekBilanganActivity;
import io.github.agusprayogi02.ui.GamePuzzleActivity;
import io.github.agusprayogi02.ui.LingkaranActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btnLingkaran);
        Button btn2 = findViewById(R.id.btnCek);
        Button btn3 = findViewById(R.id.btnGame);
        Button btn4 = findViewById(R.id.btnCalkulator);
        Toast.makeText(getApplicationContext(),"On Create",Toast.LENGTH_SHORT).show();
        btn1.setOnClickListener(view -> {
            Intent i = new Intent(this, LingkaranActivity.class);
            startActivity(i);
        });
        btn2.setOnClickListener(view -> {
            Intent i = new Intent(this, CekBilanganActivity.class);
            startActivity(i);
        });

        btn3.setOnClickListener(view -> {
            Intent i = new Intent(this, GamePuzzleActivity.class);
            startActivity(i);
        });

        btn4.setOnClickListener(view -> {
            Intent i = new Intent(this, CalculatorActivity.class);
            startActivity(i);
        });

    }
}