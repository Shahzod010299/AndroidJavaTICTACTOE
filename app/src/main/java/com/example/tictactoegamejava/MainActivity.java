package com.example.tictactoegamejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    // 0 - X
    // 1 - O

    int activePlayer = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // 0 - x o'yinchi
    // 1 - O o'yinchi
    // 2 - null

    //yutish yo'llari
    int[][] winPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    public static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        supportWindow();

    }

    private void supportWindow() {
        Window window = this.getWindow();

        window.setStatusBarColor(ContextCompat.getColor(this, R.color.purple_800));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.purple_800));
    }

    public void playerTap(View view) {

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameActive) {
            gameReset(view);
        }

        if (gameState[tappedImage] == 2) {
            counter++;

            //oxirgi raqam bosilganini tekshiramiz
            if (counter == 9) {
                //oyinni qayta boshlash
                gameActive = false;
            }

            // rasmlar massiviga qaysi rasm bosilsa osha rasm idisini joylash va 0 yoki 1 ga ozgartirtish
            gameState[tappedImage] = activePlayer;

            // rasmga effekt berish
            img.setTranslationY(-2000f);

            //foal oyinchini ozgartirish
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;

                TextView status = findViewById(R.id.status);

                // O - oyinchi - oyinni boshlang
                status.setText("O - oyinchi - oyinni boshlang");

            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;

                TextView status = findViewById(R.id.status);

                // X - oyinchi - oyinni boshlang
                status.setText("X - oyinchi - oyinni boshlang");

            }

            // animatsiya uchun
            img.animate().translationYBy(2000f).setDuration(500);
        }

        int flag = 0;

        // o'yinchi g'alaba qilganini tekshirish
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]]
                    && gameState[winPosition[1]] == gameState[winPosition[2]]
                    && gameState[winPosition[0]] != 2) {

                flag = 1;

                String winnerText;

                // o'yinni qayta boshlash
                gameActive = false;

                if (gameState[winPosition[0]] == 0) {
                    winnerText = "X yutdi";
                } else {
                    winnerText = "O yutdi";
                }

                TextView status = findViewById(R.id.status);
                status.setText(winnerText);

            }
        }

        // o'yin durang bo'lganda
        if (counter == 9 && flag == 0){
            TextView status = findViewById(R.id.status);
            status.setText("Do'stliik g'alaba qozondi");
        }


    }

    private void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;

        // o'yin boshlanganda hammasiga 2 qoyib chiqadi
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        // yangi oyin boshlanganda hamma rasmlarini boshatadi
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X - o'yinchi o'yinni boshlang");

    }
}











