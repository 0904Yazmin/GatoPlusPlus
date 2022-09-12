package com.example.gatoplusplus_1;

import androidx.appcompat.app.AppCompatActivity;
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
                resetGame(); }
        });
    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (Turno1) {
            ((Button) v).setText("X");}
        else {
            ((Button) v).setText("O"); }
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
                return true;
            }
            if (field[i][1].equals(field[i][2]) && field[i][1].equals(field[i][3]) && field[i][1].equals(field[i][4]) && !field[i][1].equals("")) {
                return true;
            }
            if (field[i][2].equals(field[i][3]) && field[i][2].equals(field[i][4]) && field[i][2].equals(field[i][5]) && !field[i][2].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 6; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && field[0][i].equals(field[3][i]) && !field[0][i].equals("")) {
                return true;
            }
            if (field[1][i].equals(field[2][i]) && field[1][i].equals(field[3][i]) && field[1][i].equals(field[4][i]) && !field[1][i].equals("")) {
                return true;
            }
            if (field[2][i].equals(field[3][i]) && field[2][i].equals(field[4][i]) && field[2][i].equals(field[5][i]) && !field[2][i].equals("")) {
                return true; }
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
    }

    private void resetGame() {
        j1Puntos = 0;
        j2Puntos = 0;
        puntosEmpates = 0;
        updatePointsText();
        resetBoard();
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