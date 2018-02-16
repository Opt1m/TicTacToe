package com.game.optim.krnol.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

/**
 * Created by Opt1m on 08.02.2018.
 */

public class AbstractField extends GridLayout {

    private static final int fieldSize = 3;

    protected Cell[][] cells;
    protected Context context;

    protected Turn turn;
    protected int turnCount;
    protected AfterTurnListener afterTurnListener;

    protected GameInfo gameInfo;

    public AbstractField(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public AbstractField(Context context, AttributeSet attr) {
        super(context, attr);
        this.context = context;
        init();
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public void setEnableCells(boolean isEnable) {
        for(int i =0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                cells[i][j].setEnabled(isEnable);
            }
        }
    }

    public void setAfterTurnListener(AfterTurnListener afterTurnListener) {
        this.afterTurnListener = afterTurnListener;

        for(int i =0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                cells[i][j].setAfterTurnListener(afterTurnListener);
            }
        }
    }

    public void setTurn(Turn turn, int row, int column) {
        cells[row][column].setState(turn);
    }

    protected void init() {
        cells = new Cell[fieldSize][fieldSize];
        setColumnCount(fieldSize);


        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                cells[i][j] = new Cell(context);
                cells[i][j].setAfterTurnListener(null);
                cells[i][j].setCoordinates(i, j);
                addView(cells[i][j]);
            }
        }


    }

    public boolean getResult() {
        return  isWin(cells[0][0], cells[0][1], cells[0][2]) ||
                isWin(cells[1][0], cells[1][1], cells[1][2]) ||
                isWin(cells[2][0], cells[2][1], cells[2][2]) ||
                isWin(cells[0][0], cells[1][0], cells[2][0]) ||
                isWin(cells[0][1], cells[1][1], cells[2][1]) ||
                isWin(cells[0][2], cells[1][2], cells[2][2]) ||
                isWin(cells[0][0], cells[1][1], cells[2][2]) ||
                isWin(cells[0][2], cells[1][1], cells[2][0]);
    }

    private boolean isWin(Cell... cells) {
        boolean status = true;
        Cell.State state = cells[0].getState();

        for (Cell cell : cells) {
            if (!cell.getState().equals(Cell.State.EMPTY)) {
                if (state != cell.getState()) {
                    status = false;
                    break;
                }
            } else {
                status = false;
                break;
            }
        }
        return status;
    }

    public void restart() {
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                cells[i][j].restart();
            }
        }
    }
}
