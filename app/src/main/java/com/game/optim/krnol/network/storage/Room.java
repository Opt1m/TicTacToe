package com.game.optim.krnol.network.storage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Opt1m on 10.02.2018.
 */

public class Room {

    public HashMap<String, String> turns;
    public String isOpen;
    public ArrayList<Player> players;

    public Room() {

    }

    public Room(Player playerOne) {
        players = new ArrayList<>();
        players.add(playerOne);

        turns = new HashMap<>();
        turns.put("start", "0");
        isOpen = "open";
    }

    public HashMap<String, String > getTurns() {
        return turns;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
