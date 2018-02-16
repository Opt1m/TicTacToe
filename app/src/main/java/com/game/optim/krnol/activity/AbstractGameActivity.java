package com.game.optim.krnol.activity;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.game.optim.krnol.R;
import com.game.optim.krnol.network.ConnectToRoom;
import com.game.optim.krnol.ui.Turn;
import com.game.optim.krnol.ui.AfterTurnListener;
import com.game.optim.krnol.ui.GameInfo;
import com.game.optim.krnol.ui.AbstractField;


import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AbstractGameActivity extends AppCompatActivity implements GameInfo, AfterTurnListener{

    @BindView(R.id.field)
    AbstractField field;

    public Turn turn;
    public int turnCount = 0;
    protected AlertDialog dialog;
    private ConnectToRoom database;
    private HashMap<String, String> turns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        turns = new HashMap<>();
        turns.put("start", "0");
        turn = Turn.MY;
        field.setGameInfo(this);
        field.setAfterTurnListener(this);
        dialog = new AlertDialog.Builder(this)
                .setPositiveButton("Рестарт", (v, c) -> field.restart())
                .setNegativeButton("Выход", null)
                .create();

        database = new ConnectToRoom(this);
        database.createRoom();
        //database.findRoom();
    }

    @Override
    public Turn getTurnInfo() {
        return turn;
    }

    @Override
    public int getTurnCountInfo() {
        return turnCount;
    }

    @Override
    public void setTurn(Turn turn, int row, int column) {
        field.setTurn(turn, row, column);
        this.turn = turn;
    }

    @Override
    public void incrementTurn() {
        turnCount++;
    }

    @Override
    public void restartCount() {
        turnCount = 0;
    }

    @Override
    public void afterTurn(int row, int column) {
        turns.put(String.valueOf(turnCount), String.valueOf(row) + String.valueOf(column));
        setTurn(turn.equals(Turn.MY) ? Turn.ENEMY : Turn.MY, row, column);
        database.setTurn(turns);
        incrementTurn();
    }

    @Override
    public void afterTurn(HashMap<String, String> turns) {
        this.turns = turns;
        if (turn.equals(Turn.ENEMY)) {
                turnCount = turns.size();
                incrementTurn();
                this.turn = (turn.equals(Turn.MY) ? Turn.ENEMY : Turn.MY);
                database.setTurn(turns);

        }
    }
}
