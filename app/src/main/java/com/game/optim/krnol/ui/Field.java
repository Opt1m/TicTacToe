package com.game.optim.krnol.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

import com.game.optim.krnol.GameActivity;

public class Field extends GridLayout implements OnAfterTurnClickListener {

    public interface GameInfo {
        GameActivity.Turn getTurnInfo();

        int getTurnCountInfo();

        void setTurn(GameActivity.Turn turn);

        void incrementTurn();

        void restartCount();
    }

    private Cell[][] cells;
    private Context context;
    private GameInfo gameInfo;
    private AlertDialog dialog;

    public Field(Context context, AttributeSet attr) {
        super(context, attr);
        this.context = context;
        init();
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public boolean getResult() {
        boolean result = false;
        if (checkState(cells[0][0], cells[0][1], cells[0][2]) ||
                checkState(cells[1][0], cells[1][1], cells[1][2]) ||
                checkState(cells[2][0], cells[2][1], cells[2][2]) ||
                checkState(cells[0][0], cells[1][0], cells[2][0]) ||
                checkState(cells[0][1], cells[1][1], cells[2][1]) ||
                checkState(cells[0][2], cells[1][2], cells[2][2]) ||
                checkState(cells[0][0], cells[1][1], cells[2][2]) ||
                checkState(cells[0][2], cells[1][1], cells[2][0])) {
            result = true;
        }

        return result;
    }

    public boolean checkState(Cell... cells) {
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

    private void init() {
        cells = new Cell[3][3];
        setColumnCount(3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell(context);
                cells[i][j].setOnClick(this);
                cells[i][j].setCoordinates(i, j);
                addView(cells[i][j]);
            }
        }

        dialog = new AlertDialog.Builder(context)
                .setPositiveButton("Рестарт", (dialog1, which) -> restart())
                .setNegativeButton("Выход", null)
                .create();
    }

    public void restart() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].restart();
            }
        }
        gameInfo.restartCount();
    }

    @Override
    public void afterTurn(int row, int column) {
        cells[row][column].setState(gameInfo.getTurnInfo());
        gameInfo.setTurn(gameInfo.getTurnInfo().equals(GameActivity.Turn.MY) ?
                GameActivity.Turn.ENEMY : GameActivity.Turn.MY);
        gameInfo.incrementTurn();

        if (getResult()) {
            dialog.setTitle("Победа");
            dialog.show();
        }

        if (gameInfo.getTurnCountInfo() == 9) {
            dialog.setTitle("Ничья");
            dialog.show();
        }
    }
}
