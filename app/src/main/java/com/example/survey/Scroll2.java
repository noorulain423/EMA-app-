
package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class Scroll2 extends AppCompatActivity {
    RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8,rg9,rg10;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DataSnapshot sp;
    String chi, uid;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    String date = sdf.format(calendar.getTime());
    ScrollView sv;
    Button button;
    int r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10;
   // Result1 result1;
    Result1 result = new Result1(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll2);
        setTitle("Perceived Stress Questionnaire");
        sv= findViewById(R.id.sview2);
        sv.fullScroll(View.FOCUS_DOWN);
        rg1=findViewById(R.id.Rg1);
        rg2=findViewById(R.id.Rg2);
        rg3=findViewById(R.id.Rg3);
        rg4=findViewById(R.id.Rg4);
        rg5=findViewById(R.id.Rg5);
        rg6=findViewById(R.id.Rg6);
        rg7=findViewById(R.id.Rg7);
        rg8=findViewById(R.id.Rg8);
        rg9=findViewById(R.id.Rg9);
        rg10=findViewById(R.id.Rg10);
        button=(Button)findViewById(R.id.Result);

//        uid = Settings.Secure.getString(getApplicationContext().getContentResolver(),
//                Settings.Secure.ANDROID_ID);
       uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        // myRef = database.getReference("Responses");
       // myRef = database.getReference().child(uid).child("Responses").child("PSS10");
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                myRef = database.getReference().child(uid).child("Responses").child("PSS10").child("Q1");
               r1= getresponse(rg1,r1);


            }
        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                myRef = database.getReference().child(uid).child("Responses").child("PSS10").child("Q2");
               r2= getresponse(rg2,r2);


            }
        });
        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                myRef = database.getReference().child(uid).child("Responses").child("PSS10").child("Q3");
                r3= getresponse(rg3,r3);
            }
        });
        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                myRef = database.getReference().child(uid).child("Responses").child("PSS10").child("Q4");
                r4= getpositiveresponse(rg4,r4);
            }
        });
        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                myRef = database.getReference().child(uid).child("Responses").child("PSS10").child("Q5");
                r5= getpositiveresponse(rg5,r5);
            }
        });
        rg6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                myRef = database.getReference().child(uid).child("Responses").child("PSS10").child("Q6");
                r6= getresponse(rg6,r6);
            }
        });
        rg7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                myRef = database.getReference().child(uid).child("Responses").child("PSS10").child("Q7");
                r7= getpositiveresponse(rg7,r7);
            }
        });
       rg8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               myRef = database.getReference().child(uid).child("Responses").child("PSS10").child("Q8");
               r8= getpositiveresponse(rg8,r8);
           }
       });
       rg9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               myRef = database.getReference().child(uid).child("Responses").child("PSS10").child("Q9");
               r9= getresponse(rg9,r9);
           }
       });
       rg10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               myRef =database.getReference().child(uid).child("Responses").child("PSS10").child("Q10");
               r10= getresponse(rg10,r10);
           }
       });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultt();

            }
        });
    }
    public void resultt(){
        result.setResultPSS10(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10);
        int value = result.getResult();
        myRef = database.getReference().child(uid).child("Responses").child("PSS10").child("Result");
        myRef.setValue(value);
        String sl= result.StressLevelPSS10();
        myRef = database.getReference().child(uid).child("Responses").child("PSS10").child("Stress level");
        myRef.setValue(sl);
        Intent intent = new Intent(Scroll2.this, ResultDisplay.class);
        intent.putExtra("result",value);
        intent.putExtra("StressLvl",sl);
        startActivity(intent);



    }

    public int getresponse(RadioGroup x,int ro){

        int r = 0;
        r =x.getCheckedRadioButtonId();
        RadioButton radioButton =findViewById(r);
        String S= radioButton.getText().toString();
        switch(S){
            case "Never":
                // do operations specific to this selection
                myRef.setValue("0");
                ro=0;
                break;
            case "Almost Never":
                // do operations specific to this selection
                myRef.setValue("1");
                ro=1;
                break;
            case "Sometimes":
                // do operations specific to this selection
                myRef.setValue("2");
                ro=2;
                break;
            case "Fairly Often":
                // do operations specific to this selection
                myRef.setValue("3");
                ro=3;
                break;
            case "Very Often":
                // do operations specific to this selection
                myRef.setValue("4");
                ro=4;
                break;}
        return ro;
    }
    public int getpositiveresponse(RadioGroup rg, int ro){
        int r = 0;
        r =rg.getCheckedRadioButtonId();
        RadioButton radioButton =findViewById(r);
        String S= radioButton.getText().toString();
        switch(S){
            case "Never":
                // do operations specific to this selection
                myRef.setValue("0");
                ro=4;
                break;
            case "Almost Never":
                // do operations specific to this selection
                myRef.setValue("1");
                ro=3;
                break;
            case "Sometimes":
                // do operations specific to this selection
                myRef.setValue("2");
                ro=2;
                break;
            case "Fairly Often":
                // do operations specific to this selection
                myRef.setValue("3");
                ro=1;
                break;
            case "Very Often":
                // do operations specific to this selection
                myRef.setValue("4");
                ro=0;
                break;}

        return ro;

    }
}