package com.game.optim.krnol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.game.optim.krnol.ui.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements Field.GameInfo {

    @Override
    public Turn getTurnInfo() {
        return getTurn();
    }

    @Override
    public int getTurnCountInfo() {
        return getTurnCount();
    }

    @Override
    public void setTurn(Turn turn) {
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

    public enum Turn {
        MY, ENEMY
    }

    @BindView(R.id.field)
    Field field;

    public Turn turn;
    public int turnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        field.setGameInfo(this);
        turn = Turn.MY;
    }

    public Turn getTurn() {
        return turn;
    }

    public int getTurnCount() {
        return turnCount;
    }
}
