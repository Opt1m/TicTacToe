package com.game.optim.krnol.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.game.optim.krnol.R;
import com.game.optim.krnol.test.TestActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.solo_game)
    public void soloGame() {
        intent = new Intent(this, AbstractGameActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.multiplayer)
    public void multiplayer() {


    }

    @OnClick(R.id.with_bot)
    public void withBot() {
        intent = new Intent(this, TestActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.exit)
    public void exit() {


    }
}
