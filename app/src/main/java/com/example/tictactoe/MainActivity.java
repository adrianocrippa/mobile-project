package com.example.tictactoe;

import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private int qty;
    private int player;
    private  int mat[][] = new int[3][3];
    private Button b[] = new Button[9];
    private String winner;
    private String player1;
    private String player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qty = 1;
        player = 1;
        b[0] = findViewById(R.id.bt_1);
        b[1] = findViewById(R.id.bt_2);
        b[2] = findViewById(R.id.bt_3);
        b[3] = findViewById(R.id.bt_4);
        b[4] = findViewById(R.id.bt_5);
        b[5] = findViewById(R.id.bt_6);
        b[6] = findViewById(R.id.bt_7);
        b[7] = findViewById(R.id.bt_8);
        b[8] = findViewById(R.id.bt_9);

        b[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                play(b[0],0,0);

            }
        });

        b[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                play(b[1],0,1);
            }
        });

        b[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[2],0,2);

            }
        });

        b[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[3],1,0);

            }
        });

        b[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[4],1,1);
            }
        });

        b[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[5],1,2);

            }
        });

        b[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[6],2,0);

            }
        });

        b[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[7],2,1);

            }
        });

        b[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[8],2,2);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.newItem:
                clear();

                final EditText editText2 = new EditText(this);
                AlertDialog.Builder secondPlayer = new AlertDialog.Builder(this);
                secondPlayer.setMessage("Enter Player 2 name: ");
                secondPlayer.setTitle("PLAYER 2");
                secondPlayer.setView(editText2);
                secondPlayer.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       player2 = editText2.getText().toString();
                    }
                });
                secondPlayer.create();
                secondPlayer.show();

                final EditText editText1 = new EditText(this);
                AlertDialog.Builder firstPlayer = new AlertDialog.Builder(this);
                firstPlayer.setMessage("Enter Player 1 name: ");
                firstPlayer.setTitle("PLAYER 1");
                firstPlayer.setView(editText1);
                firstPlayer.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        player1 = editText1.getText().toString();
                    }
                });
                firstPlayer.create();
                firstPlayer.show();

                //Toast.makeText(getApplicationContext(),"Initialize new game", Toast.LENGTH_LONG).show();

            break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void play(Button b, int x, int y){
        b.setEnabled(true);
        if (player==1){
            mat[x][y] = 1;
            b.setText("X");
            player=2;
            winner =player1;
            checkPlay(1);
        }
        else {
            mat[x][y] = 2;
            b.setText("O");
            player=1;
            winner=player2;
            checkPlay(2);
        }

        qty++;

    }

    public boolean victory(int x) {

        for (int i=0; i<mat.length; i++) {

            if(mat[i][0]==x && mat[i][1]==x && mat[i][2]==x){
                return true;
            }
            if(mat[0][i]==x && mat[1][i] ==x && mat[2][i]==x){
                return true;
            }
        }
        if (mat[0][0]==x && mat[1][1]==x && mat[2][2]==x){
            return true;
        }
        if (mat[0][2]==x && mat[1][1]==x && mat[2][0]==x){
            return true;
        }
        return false;
    }

    public boolean isTie() {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    return false; // Found an empty cell, so it's not a tie.
                }
            }
        }
        return true; // No empty cells found, it's a tie.
    }

    public void checkPlay(int x){
        if (victory(x)) {
            AlertDialog.Builder winnerAlert = new AlertDialog.Builder(this);
            winnerAlert.setTitle("WINNER");
            winnerAlert.setMessage("Player "+winner+" winner!");
            winnerAlert.setIcon(android.R.drawable.star_on);
            winnerAlert.setPositiveButton("OK", null);
            winnerAlert.create();
            winnerAlert.show();
            endGame();
        } else if (isTie()) {
            AlertDialog.Builder tieAlert = new AlertDialog.Builder(this);
            tieAlert.setTitle("Game Over");
            tieAlert.setMessage("It's a tie!");
            tieAlert.setPositiveButton("OK", null);
            tieAlert.create();
            tieAlert.show();
            endGame();
        }
    }

    public void endGame(){
        for (int i=0; i<9;i++) {
            b[i].setEnabled(false);
        }
    }

    public void clear(){
        for(int i=0; i<9;i++){
            b[i].setEnabled(true);
            b[i].setText("");
        }
        for (int x=0; x<3; x++) {
            for (int y=0; y<3; y++){
                mat[x][y] = 0;
            }
        }

        player=1;
        player1="";
        player2="";
        winner="";
    }
}