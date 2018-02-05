package com.game.optim.krnol.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;

import com.game.optim.krnol.GameActivity;

public class Cell extends AppCompatButton {

    public Cell(Context context) {
        super(context);
    }

    public interface onClick {
        void isWin();
    }
    public enum State {
        EMPTY, NAUGHT, CROSS
    }
    
    private State state;
    private onClick click;

    public Cell(Context context, AttributeSet attrs) {
        super(context, attrs);
       init();
    }

    private void init() {
        state = State.EMPTY;
        setOnClickListener(v -> {
            if (isEnabled()) {
                if (state.equals(State.EMPTY)) {
                    if (GameActivity.turn.equals(GameActivity.Turn.MY)) {
                        setText("Крест");
                        state = State.NAUGHT;
                        GameActivity.turn = GameActivity.Turn.ENEMY;
                    } else {
                        setText("Ноль");
                        state = State.CROSS;
                        GameActivity.turn = GameActivity.Turn.MY;
                    }
                }
                setEnabled(false);
            }
            click.isWin();
        });
    }

    public void setOnClick(onClick onClick) {
        this.click = onClick;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        if (state.equals(State.EMPTY)) {
            setText("");
            setEnabled(true);
        }
    }
}
