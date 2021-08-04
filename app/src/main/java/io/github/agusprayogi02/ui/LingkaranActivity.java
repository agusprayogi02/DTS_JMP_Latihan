package io.github.agusprayogi02.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

import io.github.agusprayogi02.R;

public class LingkaranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lingkaran);
        init();
    }

    public void init() {
        MaterialToolbar tBar = findViewById(R.id.tbLingkaran);
        TextInputLayout inpJari2 = findViewById(R.id.inputJari2);
        MaterialTextView hLuas = findViewById(R.id.hasilLuas);
        MaterialTextView hKeliling = findViewById(R.id.hasilKel);

        Objects.requireNonNull(inpJari2.getEditText()).addOnLayoutChangeListener((view, i, i1, i2, i3, i4, i5, i6, i7) -> {
            String inputR = inpJari2.getEditText().getText().toString();
            double hasilLuas = 0f;
            double hasilKel = 0f;
            if (!inputR.equals("")) {
//                algoritma Luas
                hasilLuas = Math.PI * Math.pow(Double.parseDouble(inputR), 2);
                hLuas.setText("Hasil Luas : " + hasilLuas);

//                algoritma Keliling
                hasilKel = 2 * Math.PI * Float.parseFloat(inputR);
                hKeliling.setText("Hasil Keliling : " + hasilKel);
            }
        });
        tBar.setNavigationOnClickListener(view -> finish());
    }
}