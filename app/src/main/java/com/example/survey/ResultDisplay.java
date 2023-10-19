package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultDisplay extends AppCompatActivity {
    TextView tvR, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);
        setTitle("Survey Result");
        tvR=findViewById(R.id.dr);
        tv4=findViewById(R.id.textView4);
      Intent intent = getIntent();
      int jk= intent.getIntExtra("result",0);
      String lvl=intent.getStringExtra("StressLvl");
        tvR.setText("Your stress score is "+jk);
        tv4.setText("Your stress level is ' "+lvl +" ' ");
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(4000);

                    // After 5 seconds redirect to another intent
                    Intent i=new Intent(getBaseContext(),FeedbackQ.class);
                    startActivity(i);

                    //Remove activity
                    finish();
                }
                catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();
    }
    }
