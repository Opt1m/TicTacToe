package com.game.optim.krnol.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

import com.game.optim.krnol.GameActivity;

public class Cell extends AppCompatButton {

    public enum State {
        EMPTY, NAUGHT, CROSS
    }

    public Cell(Context context) {
        super(context);
        init();
    }

    private State state;
    private OnAfterTurnClickListener click;

    private int column;
    private int row;

    private void init() {
        state = State.EMPTY;
        setOnClickListener(v -> {
            if (isEnabled()) {
                if (state.equals(State.EMPTY)) {
                    setEnabled(false);
                    click.afterTurn(row, column);
                }
            }
        });
    }

    public void setOnClick(OnAfterTurnClickListener onClick) {
        this.click = onClick;
    }

    public void setCoordinates(int row, int column) {
        this.column = column;
        this.row = row;
    }

    public State getState() {
        return state;
    }

    public void setState(GameActivity.Turn turn) {
        String buttonText = turn.equals(GameActivity.Turn.MY) ? "Крестик" : "Нолик";
        state = turn.equals(GameActivity.Turn.MY) ? State.NAUGHT : State.CROSS;
        setText(buttonText);
    }

    public void restart() {
        state = State.EMPTY;
        setText("");
        setEnabled(true);
    }
}
