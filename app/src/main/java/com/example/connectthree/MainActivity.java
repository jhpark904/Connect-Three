package com.example.connectthree;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    boolean isRed = true;
    int[] gameState = {3,3,3,3,3,3,3,3,3};
    int[][] winnings = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;

    public void reset(View view) {
        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 3;
        }
        gameActive = true;
        isRed = true;
        Button b = (Button) findViewById(R.id.button);
        TextView t = (TextView) findViewById(R.id.textView);
        b.animate().alpha(0);
        t.animate().alpha(0);
        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i < grid.getChildCount(); i++) {
            ImageView place = (ImageView) grid.getChildAt(i);
            place.setImageDrawable(null);
       }


    }

    public void dropIn(View view) {
        ImageView place  = (ImageView) view;
        Button b = (Button) findViewById(R.id.button);
        TextView t = (TextView) findViewById(R.id.textView);
        int pos = Integer.parseInt(place.getTag().toString());
        if (gameState[pos] == 3 && gameActive) {
            place.setTranslationY(-1500);
            if (isRed) {
                isRed = false;
                place.setImageResource(R.drawable.red);
                gameState[pos] = 0;
            } else {
                isRed = true;
                place.setImageResource(R.drawable.yellow);
                gameState[pos] = 1;
            }

            for (int[] wins : winnings) {
                String winner = "";
               if(gameState[wins[0]] != 3 && gameState[wins[0]] == gameState[wins[1]] && gameState[wins[1]] == gameState[wins[2]]) {
                   b.animate().alpha(1);
                   gameActive = false;
                   if (isRed) {
                       winner = "Yellow";
                   } else {
                       winner = "Red";
                   }
                   t.setText(winner + " has won");
                   t.animate().alpha(1);

               }
            }

            for (int i=0; i < gameState.length; i++) {
                if (gameState[i] == 3) {
                    break;
                }
                if (i == 8) {
                    b.animate().alpha(1);
                    t.animate().alpha(1);
                    t.setText("It is a draw");
                }

            }
            place.animate().translationYBy(1500).setDuration(300);
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}