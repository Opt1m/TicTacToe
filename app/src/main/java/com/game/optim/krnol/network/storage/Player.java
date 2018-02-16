package com.game.optim.krnol.network.storage;

/**
 * Created by Opt1m on 08.02.2018.
 */

public class Player {

    public String name;
    public long userId;
    public int win;
    public int lose;
    public int draw;

    public Player() {

    }

    public Player(String name, long userId, int win, int lose, int draw ) {
        this.name = name;
        this.userId = userId;
        this.win = win;
        this.lose = lose;
        this.draw = draw;
    }
}
