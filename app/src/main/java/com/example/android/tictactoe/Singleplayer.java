package com.example.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.util.Objects;

public class Singleplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer);
        Objects.requireNonNull(getSupportActionBar()).hide();//hides action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//makes full screen
    }
    public void mainMenu(View view) {
        Intent intent = new Intent(this, mainmenu.class);//takes you to mainMenu when press menu button
        startActivity(intent);
        finish();
    }
}