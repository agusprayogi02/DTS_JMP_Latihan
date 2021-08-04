package io.github.agusprayogi02.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Objects;
import io.github.agusprayogi02.R;

public class CekBilanganActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_bilangan);
        init();
    }

    public void init() {
        MaterialToolbar tb = findViewById(R.id.tbBil);
        TextInputLayout inp1 = findViewById(R.id.inpBil1);
        TextInputLayout inp2 = findViewById(R.id.inpBil2);
        TextView hasil = findViewById(R.id.hasilCek);
        Button cek = findViewById(R.id.btnCekNow);


        cek.setOnClickListener(view -> {
            String str1 = Objects.requireNonNull(inp1.getEditText()).getText().toString();
            String str2 = Objects.requireNonNull(inp2.getEditText()).getText().toString();

            if (str1.equals("")) {
                Toast.makeText(this, "Bilangan 1 Harus diisi!", Toast.LENGTH_SHORT).show();
                inp1.requestFocus();
                return;
            } else if (str2.equals("")) {
                Toast.makeText(this, "Bilangan 2 Harus diisi!", Toast.LENGTH_SHORT).show();
                inp2.requestFocus();
                return;
            }

            hideKeyboard(this);

            int num1 = Integer.parseInt(str1);
            int num2 = Integer.parseInt(str2);
            String hasilCek = "";

            if (num1 == num2) {
                hasilCek = num1 + " Sama Dengan " + num2;
            } else if (num1 > num2) {
                hasilCek = num1 + " Lebih Besar dari " + num2;
            } else if (num1 < num2) {
                hasilCek = num1 + " Lebih Kecil dari " + num2;
            }
            hasil.setText(hasilCek);
        });
        tb.setNavigationOnClickListener(view -> finish());
    }

    public  void hideKeyboard(Activity activity) {
        View v = activity.getWindow().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}