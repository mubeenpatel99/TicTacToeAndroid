package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Mubeen Patel on 24/06/2019
 * */

public class MainActivity extends AppCompatActivity {

    public static final String player1 = "com.example.tictactoe.example.player1";
    public static final String player2 = "com.example.tictactoe.example.player2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGameActivity(view);
            }
        });
    }
    public void openGameActivity(View view){
        EditText player1namefield = findViewById(R.id.player1namefield);
        String p1name = player1namefield.getText().toString();

        EditText player2nmaefield = findViewById(R.id.player2namefield);
        String p2name = player2nmaefield.getText().toString();

        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(player1, p1name);
        intent.putExtra(player2, p2name);
        startActivity(intent);
    }
}