package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mubeen Patel on 24/06/2019
 * */


public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private TextView textViewPlayer1Points;
    private TextView textViewPlayer2Points;

    private String Toast1;
    private String Toast2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String player1 = intent.getStringExtra(MainActivity.player1);
        String player2 = intent.getStringExtra(MainActivity.player2);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        textViewPlayer1Points = findViewById(R.id.text_view_p1_points);
        textViewPlayer2Points = findViewById(R.id.text_view_p2_points);


        textViewPlayer1.setText(player1);
        textViewPlayer2.setText(player2);

        Toast1 = player1 + " wins!";
        Toast2 = player2 + " wins!";

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    @Override
    public void onClick(View v) {
        //checks if the button clicked was clicked before or not
        if (!((Button) v).getText().toString().equals("")){
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        }
        else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if ( checkForWin() ){
            if (player1Turn){
                player1Wins();
            }
            else {
                player2Wins();
            }
        }
        else if (roundCount == 9){
            draw();
        }
        else{
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];

        for (int i =0; i < 3; i++){
            for (int j =0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++){
            if ( field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("") ){
                return true;
            }
        }
        for (int i = 0; i < 3; i++){
            if ( field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("") ){
                return true;
            }
        }

        if ( field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("") ){
            return true;
        }

        if ( field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("") ){
            return true;
        }

        return false;
    }

    private void player1Wins(){
        player1Points++;
        Toast.makeText(this, Toast1 , Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins(){
        player2Points++;
        Toast.makeText(this, Toast2 , Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void resetBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame(){
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    private void updatePointsText(){
        textViewPlayer1Points.setText(": " + player1Points);
        textViewPlayer2Points.setText(": " + player2Points);
    }

    @Override
    protected void onSaveInstanceState(@Nullable Bundle outstate) {
        super.onSaveInstanceState(outstate);

        outstate.putInt("roundCount", roundCount);
        outstate.putInt("player1Points", player1Points);
        outstate.putInt("player2Points", player2Points);
        outstate.putBoolean("Player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}

