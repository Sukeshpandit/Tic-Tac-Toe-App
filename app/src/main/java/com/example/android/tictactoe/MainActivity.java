package com.example.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int activePlayer = 0; // players turn
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};//initially every cell is empty
//    Here
//    O = 0;
//    X = 1;
//    Null(nothing inside) = 2;
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};//winning positions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();//hides action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//makes full screen
    }

    public void mainMenu(View view) {
        Intent intent = new Intent(this, mainmenu.class);//takes you to mainMenu when press menu button
        startActivity(intent);
        finish();
    }

    @SuppressLint("SetTextI18n")//ignore this
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());//gets image tag when player press image box
        if (gameState[tappedImage] == 2 && gameActive){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f); //entry animation
            if (activePlayer == 0){
                img.setImageResource(R.drawable.o); // adds image O
                activePlayer = 1;
                TextView GamestatusText = findViewById(R.id.gameStatus);
                GamestatusText.setText(" X's turn to play");
            }
            else{
                img.setImageResource(R.drawable.x);//adds image X
                TextView GamestatusText = findViewById(R.id.gameStatus);
                GamestatusText.setText("O's turn to play ");
                activePlayer = 0;
            }
            img.animate().translationYBy(1000f).setDuration(200);//entry animation
        }
//checking winner
        for (int[] winposition:winningPositions){
            if (gameState[winposition[0]] == gameState[winposition[1]] && gameState[winposition[1]] == gameState[winposition[2]] && gameState[winposition[0]]!=2){
//                some one is wom
                gameActive = false;
                String winner;
                if(gameState[winposition[0]] == 0){
                    winner = "O won";
                    Toast toast = Toast.makeText(this,"O has won",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.LEFT,162,320);
                    toast.show();
                    toast.makeText(this,"Press reset",Toast.LENGTH_SHORT).show();

                }
                else{
                    winner = "X won";
                    Toast.makeText(this,"X has won",Toast.LENGTH_LONG).show();
                    Toast.makeText(this,"Press reset",Toast.LENGTH_SHORT).show();

                }
//                updating the status
                TextView GamestatusText = findViewById(R.id.gameStatus);
                GamestatusText.setText(winner);
            }
        }
//        checks if game is draw or not
        boolean draw = true;
        for (int i=0; i<gameState.length;i++){
            if (gameState[i] == 2){
                draw = false;
            }
        }
        if (draw){
            Toast.makeText(this,"DRAW",Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void reset(View view){
         gameActive = true;//makes game active again
         activePlayer = 0; // players turn
        ((ImageView)findViewById(R.id.imageview0)).setImageResource(0);//makes image src empty again
        ((ImageView)findViewById(R.id.imageview1)).setImageResource(0);//makes image src empty again
        ((ImageView)findViewById(R.id.imageview2)).setImageResource(0);//makes image src empty again
        ((ImageView)findViewById(R.id.imageview3)).setImageResource(0);//makes image src empty again
        ((ImageView)findViewById(R.id.imageview4)).setImageResource(0);//makes image src empty again
        ((ImageView)findViewById(R.id.imageview5)).setImageResource(0);//makes image src empty again
        ((ImageView)findViewById(R.id.imageview6)).setImageResource(0);//makes image src empty again
        ((ImageView)findViewById(R.id.imageview7)).setImageResource(0);//makes image src empty again
        ((ImageView)findViewById(R.id.imageview8)).setImageResource(0);//makes image src empty again
        Arrays.fill(gameState, 2); //again fills the gamestate with 2 elements
        TextView GamestatusText = findViewById(R.id.gameStatus);
        GamestatusText.setText("O's turn to play ");

    }
}