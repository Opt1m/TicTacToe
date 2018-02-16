package com.game.optim.krnol.network;

import android.util.Log;

import com.game.optim.krnol.activity.AbstractGameActivity;
import com.game.optim.krnol.network.storage.Player;
import com.game.optim.krnol.network.storage.Room;
import com.game.optim.krnol.ui.AfterTurnListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Opt1m on 11.02.2018.
 */

public class ConnectToRoom {

    private FirebaseDatabase database;
    private DatabaseReference rooms;
    public static String currentRoomId;
    private AfterTurnListener afterTurn;


    public ConnectToRoom(AfterTurnListener afterTurn) {
        database = FirebaseDatabase.getInstance();
        rooms = database.getReference("Rooms");
        this.afterTurn = afterTurn;
    }

    public void createRoom() {
        Player player = new Player("Anton", 123456789, 3, 4, 6);
        long id = System.currentTimeMillis();
        currentRoomId = String.valueOf(id);
        rooms.child(String.valueOf(id)).setValue(new Room(player));
        connect();
    }

    public void findRoom() {
        rooms.getRef().runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                HashMap<String, Room> rooms = (HashMap<String, Room>) dataSnapshot.getValue();
                Set<String> keys = rooms.keySet();

                for (String key : keys) {
                    Room room = dataSnapshot.child(key).getValue(Room.class);
                    if (room.isOpen.equals("open")) {
                        currentRoomId = key;
                        connect();
                        break;
                    }
                }
            }
        });
    }

    public void connect() {
        rooms.child(currentRoomId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Room room = dataSnapshot.getValue(Room.class);
                afterTurn.afterTurn(room.getTurns());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }

    public void setTurn(HashMap<String, String> turns) {
        rooms.child(currentRoomId).child("turns").setValue(turns);
    }
}
