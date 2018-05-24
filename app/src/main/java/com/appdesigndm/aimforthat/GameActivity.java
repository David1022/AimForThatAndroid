package com.appdesigndm.aimforthat;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportFragmentManager().beginTransaction().add(R.id.gameContainer, new GameFragment()).commit();
    }
}
