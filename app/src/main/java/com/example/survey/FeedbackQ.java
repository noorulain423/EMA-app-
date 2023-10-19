package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class FeedbackQ extends AppCompatActivity {
    TextView tv1, tv2;
    Button no, no1,yes, yes1, exit;
    Calendar calendar;
    String date, and_id, q1, q2,uid;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_q);
        setTitle("Feedback Questions");
        tv1= findViewById(R.id.fbQ1);
        tv2=findViewById(R.id.fbQ2);
        no=findViewById(R.id.btnNo);
        no1=findViewById(R.id.btnNo2);
        yes= findViewById(R.id.btnYes);
        yes1=findViewById(R.id.btnYes2);
        exit=findViewById(R.id.btnExit);
//        and_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
//                Settings.Secure.ANDROID_ID);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        calendar = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        date = sdf1.format(calendar.getTime());

        FirebaseDatabase db = FirebaseDatabase.getInstance( );
        ref = db.getReference(uid).child("QuestionAboutApp").child(date);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        Log.d("day",dayOfTheWeek);
        changeCount(dayOfTheWeek);

        no.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                ref.child(q1).setValue("No");
               no.setEnabled(false);
               no.setBackgroundColor(R.drawable.googleg_disabled_color_18);
            }
        });
        yes.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                ref.child(q1).setValue("Yes");
                yes.setEnabled(false);
                yes.setBackgroundColor(R.drawable.googleg_disabled_color_18);
            }
        });
        no1.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                ref.child(q2).setValue("No");
                no1.setEnabled(false);
                no1.setBackgroundColor(R.drawable.googleg_disabled_color_18);
            }
        });
        yes1.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                ref.child(q2).setValue("Yes");
                yes1.setEnabled(false);
                yes1.setBackgroundColor(R.drawable.googleg_disabled_color_18);
            }
        });
        exit.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( FeedbackQ.this, MainActivity.class ));
            }
        });
    }

    private void changeCount(String day) {
        switch (day) {
        case "Mon":
            tv1.setText("Did you enjoy completing the survey?");
            q1 = "EnjoyedCompletingAssessment";
            tv2.setText("4 + 5 = 10");
            q2="mathsQ9";
            break;
        case "Tue":
            tv1.setText("Do you feel comfortable while using this app?");
            q1 = "ComfortableUsing";
            tv2.setText("Are you enjoying filling the survey?");
            q2="EnjoyFillingSurvey";
            break;
        case "Wed":
            tv1.setText("This App is easy to use?");
            q1 = "EasyToUse";
            tv2.setText("6 * 2 = 8");
            q2="mathsQ12";
            break;
        case "Thu":
            tv1.setText("Do you find completing the survey burdensome?");
            q1 = "Burdensome";
            tv2.setText("3 - 2 = 1");
            q2="mathsQ1";
            break;
        case "Fri":
            tv1.setText("This App is difficult to use?");
            q1 = "DifficultToUse";
            tv2.setText("2 + 2 + 4 = 8");
            q2="mathsQ8";
            break;
        case "Sat":
            tv1.setText("While filling the survey, do you feel being judged by someone?");
            q1 = "FeltJudged";
            tv2.setText("Found completing the survey boring");
            q2="Boring";
            break;
        case "Sun":
            tv1.setText("Is your overall impression of using this app positive?");
            q1 = "PositiveImpression";
            tv2.setText("Is your overall impression of using this app negative?");
            q2="NegativeImpression";
            break;
        }
    }
}