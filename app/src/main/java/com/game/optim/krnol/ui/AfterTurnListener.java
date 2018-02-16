package com.game.optim.krnol.ui;

import java.util.HashMap;

/**
 * Created by AntipovAS on 05.02.18.
 */

public interface AfterTurnListener {
    void afterTurn(int row, int column);
    void afterTurn(HashMap<String, String> turns);
}
