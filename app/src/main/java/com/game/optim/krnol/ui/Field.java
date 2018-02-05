package com.game.optim.krnol.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

public class Field extends GridLayout{

    Cell[][] cells;
    Context context;

    public Field(Context context, AttributeSet attr) {
        super(context, attr);
        this.context = context;
        init();
    }


    /*Cell.onClick click = () -> {
            if (getResult()) {
                AlertDialog dialog = new AlertDialog.Builder(GameActivity.this)
                        .setTitle("Победа")
                        .setPositiveButton("Рестарт", (dialog1, which) -> restart())
                        .setNegativeButton("Выход", null)
                        .create();
                dialog.show();
            }
        };

        for(int i = 0; i < cells.size(); i++) {
            cells.get(i).setOnClick(click);
        }

        public boolean getResult() {
        boolean result = false;
        if (checkState(oneOne, oneTwo, oneThree) ||
                checkState(twoOne, twoTwo, twoThree) ||
                checkState(threeOne, threeTwo, threeThree) ||
                checkState(oneOne, twoOne, threeOne) ||
                checkState(oneTwo, twoTwo, threeTwo) ||
                checkState(oneThree, twoThree, threeThree) ||
                checkState(oneOne, twoTwo, threeThree) ||
                checkState(oneThree, twoTwo, threeOne)) {
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
    }*/
    private void init() {
        cells = new Cell[3][3];
        setColumnCount(3);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cells[i][j] = new Cell(context);
                addView(cells[i][j]);
            }
        }
    }
}
