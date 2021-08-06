package io.github.agusprayogi02.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Random;

import io.github.agusprayogi02.R;

public class GamePuzzleActivity extends AppCompatActivity {

    private int emptyX = 3;
    private int emptyY = 3;
    private GridLayout group;
    private Button[][] buttons;
    private int[] tiles;
    private final String[] hurufValid = new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", ""
    };
    private final String[] huruf = new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", ""
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_puzzle);

        buatBtnHuruf();
        buatRandom();
        loadDataToView();
        MaterialToolbar tb = findViewById(R.id.tbGame);
        tb.setNavigationOnClickListener(view -> finish());
        tb.setOnMenuItemClickListener(this::menuSelected);
    }

    private void loadDataToView() {
        emptyX = 3;
        emptyY = 3;
        for (int i = 0; i < group.getChildCount(); i++) {
            buttons[i / 4][i % 4].setText(huruf[i]);
            buttons[i / 4][i % 4].setBackgroundResource(android.R.color.holo_blue_dark);
            buttons[i / 4][i % 4].setClickable(true);
        }
        buttons[emptyX][emptyY].setText("");
        buttons[emptyX][emptyY].setBackgroundResource(android.R.color.transparent);
    }

    private void buatRandom() {
        int n = 15;
        Random random = new Random();
        while (n > 1) {
            int randNum = random.nextInt(n--);
            String tmpHuruf = huruf[randNum];
            huruf[randNum] = huruf[n];
            huruf[n] = tmpHuruf;
            int tmpTiles = tiles[randNum];
            tiles[randNum] = tiles[n];
            tiles[n] = tmpTiles;
        }
        if (isSelesai())
            buatBtnHuruf();
    }

    private boolean isSelesai() {
        int count = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < i; j++) {
                if (tiles[j] > tiles[i])
                    count++;
            }
        }
        return count % 2 == 0;
    }

    private void buatBtnHuruf() {
        group = findViewById(R.id.group_board);
        buttons = new Button[4][4];
        tiles = new int[16];
        for (int i = 0; i < group.getChildCount(); i++) {
            Button btn = (Button) group.getChildAt(i);
            btn.setText(huruf[i]);
            tiles[i] = i + 1;
            if (huruf[i].equals("")) {
                btn.setBackgroundResource(android.R.color.transparent);
            } else {
                btn.setBackgroundResource(android.R.color.holo_blue_dark);
            }
            btn.setOnClickListener(this::buttonOnClick);
            buttons[i / 4][i % 4] = btn;
        }
    }

    public boolean menuSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ulang:
                buatRandom();
                loadDataToView();
                break;
            case R.id.menu_keluar:
                finish();
                break;
        }
        return true;
    }

    private void buttonOnClick(View view) {
        Button btn = (Button) view;
        int x = btn.getTag().toString().charAt(0) - '0';
        int y = btn.getTag().toString().charAt(1) - '0';

        if ((Math.abs(emptyX - x) == 1 && emptyY == y) || (Math.abs(emptyY - y) == 1 && emptyX == x)) {
            buttons[emptyX][emptyY].setText(btn.getText());
            buttons[emptyX][emptyY].setBackgroundResource(android.R.color.holo_blue_dark);
            btn.setText("");
            btn.setBackgroundResource(android.R.color.transparent);
            emptyX = x;
            emptyY = y;
            Log.d("huruf", hurufValid[0]);
        }
        cekWin();
    }

    private void cekWin() {
        boolean isWin = false;
        if (emptyX == 3 && emptyY == 3)
            for (int i = 0; i < group.getChildCount(); i++) {
                if (buttons[i / 4][i % 4].getText().equals(hurufValid[i])) {
                    isWin = true;
                } else {
                    isWin = false;
                    break;
                }
            }
        if (isWin) {
            Toast.makeText(this, "You Wint It!", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < group.getChildCount(); i++) {
                Button btn = (Button) group.getChildAt(i);
                btn.setClickable(false);
                buttons[i / 4][i % 4] = btn;
            }
        }
    }
}