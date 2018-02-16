package com.game.optim.krnol.ui;

/**
 * Created by Opt1m on 07.02.2018.
 */

public interface GameInfo {
    Turn getTurnInfo();

    int getTurnCountInfo();

    void setTurn(Turn turn, int row, int column);

    void incrementTurn();

    void restartCount();
}
