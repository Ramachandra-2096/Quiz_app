package com.example.braintraining;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button b, b0, b1, b2, b3, again;
    TextView q, correct, sc, time;
    int correct_location, score = 0, played = 0,i=0,j=1;
    ArrayList<Integer> ans = new ArrayList<>();
    MediaPlayer mediaPlayer;

    public void ChooseAnswer(View view) {
        if (String.valueOf(correct_location).equals(view.getTag().toString())) {
            correct.setText("Correct!!");
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.correct);
            mediaPlayer.start();
            score++;
        } else {
            correct.setText("Wrong!!");
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.wrong);
            mediaPlayer.start();
        }

        played++;
        sc.setText(score + "/" + played);
        newquestion();
    }

    public void newquestion() {
        Random r = new Random();
        int a = r.nextInt(21);
        int b = r.nextInt(21);
        q.setText(a + "+" + b);
        correct_location = r.nextInt(4);
        ans.clear();
        for (int i = 0; i < 4; i++) {
            if (i == correct_location) {
                ans.add(a + b);
            } else {
                int wrong = r.nextInt(41);
                while (wrong == a + b) {
                    wrong = r.nextInt(41);
                }
                ans.add(wrong);
            }
        }
        b0.setText(String.valueOf(ans.get(0)));
        b1.setText(String.valueOf(ans.get(1)));
        b2.setText(String.valueOf(ans.get(2)));
        b3.setText(String.valueOf(ans.get(3)));
    }

    public void Playagain(View view) {
        score = 0;
        played = 0;
        b0.setVisibility(View.VISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        q.setVisibility(View.VISIBLE);
        time.setText("30s");
        correct.setText("");
        sc.setText(score + "/" + played);
        newquestion();
        again.setVisibility(View.INVISIBLE);
        if (i == 1) {
            CountDownTimer countDownTimer = new CountDownTimer(40100, 1000) {
                @Override
                public void onTick(long l) {
                    time.setText(String.valueOf(l / 1000) + "s");
                    if (l / 1000 ==2& j==1) {
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.count);
                        mediaPlayer.start();
                        j=0;
                    }
                }

                @Override
                public void onFinish() {
                    correct.setText("DONE");
                    again.setVisibility(View.VISIBLE);
                    i = 1;
                    mediaPlayer.stop();
                    b0.setVisibility(View.INVISIBLE);
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    q.setVisibility(View.INVISIBLE);
                }
            }.start();
        }
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        again = findViewById(R.id.again);

        q = findViewById(R.id.que);
        correct = findViewById(R.id.textView);
        sc = findViewById(R.id.score);
        time = findViewById(R.id.time);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.setVisibility(View.INVISIBLE);
                b0.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                time.setVisibility(View.VISIBLE);
                correct.setVisibility(View.VISIBLE);
                sc.setVisibility(View.VISIBLE);
                q.setVisibility(View.VISIBLE);
                i=1;
                Playagain(findViewById(R.id.textView));
            }

        });


    }
}
