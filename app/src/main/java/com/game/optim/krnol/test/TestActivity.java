package com.game.optim.krnol.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.game.optim.krnol.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference rooms = database.getReference("Rooms");

        ArrayList<String> playersName = new ArrayList<>();
        playersName.add("Anton");
        playersName.add("Vitya");

        TestClass value = new TestClass(1, playersName);
        rooms.child("firstRoom").setValue(value);
    }

    public static class TestClass {
        int roomId;
        ArrayList<String> playersName;

        public TestClass(int roomId, ArrayList<String> playersName) {
            this.roomId = roomId;
            this.playersName = playersName;

        }

    }

}
