package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView wordTv,timer;
     EditText wordEnteredTv;
     Button validate, newGame;

    Dbhelper dbhelper;

    String currentword;

    Random random;

    String[] gamingword =  {"myword"};

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordTv = (TextView) findViewById(R.id.wordTv);

        timer = (TextView) findViewById(R.id.timer);

        wordEnteredTv = (EditText) findViewById(R.id.wordEnteredTv);

        validate = (Button) findViewById(R.id.validate);

        newGame = (Button) findViewById(R.id.newGame);

        dbhelper = new Dbhelper(this);

        random = new Random();

        newGame();

        // initialize timer Duration;

        long duration = TimeUnit.MINUTES.toMillis(1);

        // countdown timer;

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                // convert milli to sec and mintue;
                String sDuration = String.format(Locale.ENGLISH , "%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(l)
                        , TimeUnit.MILLISECONDS.toSeconds(l) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                // set time on textview

                timer.setText(sDuration);
            }

            @Override
            public void onFinish() {
                timer.setVisibility(View.GONE);
                newGame();


                // display toast to end;
                Toast.makeText(MainActivity.this, "Session Expired", Toast.LENGTH_SHORT).show();

            }
        }.start();

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userwordenter = wordEnteredTv.getText().toString();

                if (userwordenter.equalsIgnoreCase(currentword)){

                    Toast.makeText(MainActivity.this, "Awesome", Toast.LENGTH_SHORT).show();
                    newGame();
                }
                else {
                    Toast.makeText(MainActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();

                }

            }
        });
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });

    }
private String suffleword(String shufword){

    List<String> letters = Arrays.asList(shufword.split(""));

    Collections.shuffle(letters);

    String shuffled = "";

    for (String letter : letters){

        shuffled += letter;
    }
    return shuffled;
}
        private void newGame(){
try {
    List<String> list = dbhelper.fetch2();
    String[] array = list.toArray(new String[0]);


    currentword = array[random.nextInt(array.length)];

    wordTv.setText(suffleword(currentword));

    wordEnteredTv.setText("");


} catch (Exception e) {
    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
}


}
}