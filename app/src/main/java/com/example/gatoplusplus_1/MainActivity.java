package com.example.gatoplusplus_1;

import static android.graphics.Color.WHITE;
import static android.graphics.Color.blue;
import static android.graphics.Color.rgb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[6][6];

    private int j2Puntos;
    private TextView jugador1;
    private TextView jugador2;
    private boolean Turno1 = true;
    private boolean turno = true;
    private int cont;
    private int j1Puntos;
    private TextView empates;
    private int puntosEmpates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jugador1= findViewById(R.id.jugador_1);
        jugador2 = findViewById(R.id.jugador_2);
        empates = findViewById(R.id.empate);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this); }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                resetCasillas();
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (Turno1) {
            ((Button) v).setText("X");
            ((Button) v).setBackgroundColor(Color.rgb(253, 216, 53));
            }
        else {
            ((Button) v).setText("O"); ((Button) v).setBackgroundColor(Color.rgb(255, 152, 0));}
        cont++;
        if (checkForWin()) {
            if (Turno1) {
                Ganadorj1();

            } else {
                Ganadorj2();
            }
        } else if (cont == 36) {
            Empates();
        } else {
            Turno1 = !Turno1;
        }
        if (resetCasillas()) {
            ((Button) v).setBackgroundColor(WHITE);
            turno = false;
        }

    }
    private boolean checkForWin() {
        String[][] field = new String[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 6; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && field[i][0].equals(field[i][3]) && !field[i][0].equals("")) {
                buttons[i][0].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[i][1].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[i][2].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[i][3].setBackgroundColor(Color.rgb(221, 44, 0));
                return true;
            }
            if (field[i][1].equals(field[i][2]) && field[i][1].equals(field[i][3]) && field[i][1].equals(field[i][4]) && !field[i][1].equals("")) {
                buttons[i][2].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[i][3].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[i][4].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[i][1].setBackgroundColor(Color.rgb(221, 44, 0));
                return true;
            }
            if (field[i][2].equals(field[i][3]) && field[i][2].equals(field[i][4]) && field[i][2].equals(field[i][5]) && !field[i][2].equals("")) {
                buttons[i][3].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[i][4].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[i][4].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[i][5].setBackgroundColor(Color.rgb(221, 44, 0));
                return true;
            }
        }
        for (int i = 0; i < 6; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && field[0][i].equals(field[3][i]) && !field[0][i].equals("")) {
                buttons[1][i].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[2][i].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[3][i].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[0][i].setBackgroundColor(Color.rgb(221, 44, 0));
                return true;
            }
            if (field[1][i].equals(field[2][i]) && field[1][i].equals(field[3][i]) && field[1][i].equals(field[4][i]) && !field[1][i].equals("")) {
                buttons[3][i].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[2][i].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[4][i].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[0][i].setBackgroundColor(Color.rgb(221, 44, 0));
                return true;
            }
            if (field[2][i].equals(field[3][i]) && field[2][i].equals(field[4][i]) && field[2][i].equals(field[5][i]) && !field[2][i].equals("")) {
                buttons[3][i].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[4][i].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[5][i].setBackgroundColor(Color.rgb(221, 44, 0));
                buttons[2][i].setBackgroundColor(Color.rgb(221, 44, 0));
                return true;
            }

        }
        return false;

    }

    private void Ganadorj1() {
        j1Puntos++;
        Toast.makeText(this, "El Jugador 1 ha ganado", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void Ganadorj2() {
        j2Puntos++;
        Toast.makeText(this, "El Jugador 2 ha ganado", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void Empates() {
        puntosEmpates++;
        Toast.makeText(this, "Los dos jugadores han empatado", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void updatePointsText() {
        jugador1.setText("Jugador 1: " + j1Puntos);
        jugador2.setText("Jugador 2: " + j2Puntos);
        empates.setText("Empates:  " + puntosEmpates);
    }

    private void resetBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                buttons[i][j].setText("");
            }}
        cont= 0;
        Turno1 = true;
        resetCasillas();
    }
    private boolean resetCasillas(){
        return false;
    }

    private void resetGame() {
        j1Puntos = 0;
        j2Puntos = 0;
        puntosEmpates = 0;
        updatePointsText();
        resetBoard();
        resetCasillas();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("ronda", cont);
        outState.putInt("puntos_j1", j1Puntos);
        outState.putInt("puntos_j2", j2Puntos);
        outState.putInt("Puntos_empates", puntosEmpates);
        outState.putBoolean("Turnoj1", Turno1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        cont = savedInstanceState.getInt("ronda");
        j1Puntos = savedInstanceState.getInt("puntos_j1");
        j2Puntos = savedInstanceState.getInt("puntos_j2");
        puntosEmpates = savedInstanceState.getInt("puntosEmpates");
        Turno1 = savedInstanceState.getBoolean("Turno1");
    }


}