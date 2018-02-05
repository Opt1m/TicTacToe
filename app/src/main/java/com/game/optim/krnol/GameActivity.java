package com.game.optim.krnol;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.game.optim.krnol.ui.Cell;
import com.game.optim.krnol.ui.Field;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {

    public enum Turn {
        MY, ENEMY
    }

    @BindView(R.id.field)
    Field field;

    public static Turn turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        turn = Turn.MY;

    }


    public void restart() {

    }
}
