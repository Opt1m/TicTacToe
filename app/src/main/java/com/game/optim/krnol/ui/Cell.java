package com.game.optim.krnol.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class Cell extends AppCompatButton {

    public enum State {
        EMPTY, NAUGHT, CROSS
    }

    public Cell(Context context) {
        super(context);
        init();
    }

    private State state;
    private AfterTurnListener afterTurnListener;

    private int column;
    private int row;

    private void init() {
        state = State.EMPTY;
        setOnClickListener(v -> {
            if (isEnabled()) {
                if (state.equals(State.EMPTY)) {
                    setEnabled(false);
                    afterTurnListener.afterTurn(row, column);
                }
            }
        });
    }

    public void setAfterTurnListener(AfterTurnListener onClick) {
        this.afterTurnListener = onClick;
    }

    public void setCoordinates(int row, int column) {
        this.column = column;
        this.row = row;
    }

    public State getState() {
        return state;
    }

    public void setState(Turn turn) {
        String buttonText = turn.equals(Turn.MY) ? "Крестик" : "Нолик";
        state = turn.equals(Turn.MY) ? State.NAUGHT : State.CROSS;
        setText(buttonText);
    }

    public void restart() {
        state = State.EMPTY;
        setText("");
        setEnabled(true);
    }
}
