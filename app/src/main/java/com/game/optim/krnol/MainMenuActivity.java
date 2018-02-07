package com.game.optim.krnol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.solo_game)
    public void soloGame() {


    }

    @OnClick(R.id.multiplayer)
    public void multiplayer() {


    }

    @OnClick(R.id.with_bot)
    public void withBot() {


    }

    @OnClick(R.id.exit)
    public void exit() {


    }
}
