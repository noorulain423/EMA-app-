package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

public class Scroll1 extends AppCompatActivity {
    NestedScrollView nsv;
    RadioGroup rg1,rg2,rg3,rg4;
    RadioButton b0,b1,b2,b3,b4, b00,b01,b02,b03,b04,b000,b001,b002,b003,b004, b0000,b0001,b0002,b0003,b0004;
    TextView tv1,tv2,tv3,tv4;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String chi, uid;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    String date = sdf.format(calendar.getTime());
    ScrollView  sv;
    //Responses responses;
    Button button;
    int r1,r2,r3,r4;
    Result1 result1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll1);
        setTitle("Perceived Stress Questionnaire");
        sv= findViewById(R.id.sview1);
        sv.fullScroll(View.FOCUS_DOWN);
        rg1=findViewById(R.id.Rg1);
        rg2=findViewById(R.id.Rg2);
        rg3=findViewById(R.id.Rg3);
        rg4=findViewById(R.id.Rg4);
        button=(Button)findViewById(R.id.Result);
//        uid = Settings.Secure.getString(getApplicationContext().getContentResolver(),
//                Settings.Secure.ANDROID_ID);

       uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child(uid).child("Responses").child(date);


        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               getRG1();

            }
        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getRG2();
            }
        });

        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            getRG3();

            }
        });
        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getRG4();

            }
        });
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        result1=new Result1(r1,r2,r3,r4);
        result1.setResult(r1,r2,r3,r4);
        String stresslvl = result1.StresslvlPSS4();
       int value= getscore();
        Intent intent = new Intent(Scroll1.this, ResultDisplay.class);
        intent.putExtra("result",value);
        intent.putExtra("StressLvl",stresslvl);
        startActivity(intent);


    }
});

    }

    public int getscore() {
        Toast toast;
        Toast.makeText(getApplicationContext(),"Records added",Toast.LENGTH_LONG).show();
        int value = result1.getResult();
         myRef.child("Result").setValue(value);
         return value;

    }

    public void getRG1(){
        switch(rg1.getCheckedRadioButtonId()){
            case R.id.rbtn0:
                // do operations specific to this selection
                myRef.child("Q1").setValue("0");
                r1=0;
                break;
            case R.id.rbtn1:
                // do operations specific to this selection
                myRef.child("Q1").setValue("1");
                r1=1;
                break;
            case R.id.rbtn2:
                // do operations specific to this selection
                myRef.child("Q1").setValue("2");
                r1=2;
                break;
            case R.id.rbtn3:
                // do operations specific to this selection
                myRef.child("Q1").setValue("3");
                r1=3;
                break;
            case R.id.rbtn4:
                // do operations specific to this selection
                myRef.child("Q1").setValue("4");
                r1=4;
                break;
        }

    }
    public void getRG2(){
        switch(rg2.getCheckedRadioButtonId()){
            case R.id.rbtn00:
                // do operations specific to this selection
                myRef.child("Q2").setValue("0");
                r2=4;
                break;
            case R.id.rbtn01:
                // do operations specific to this selection
                myRef.child("Q2").setValue("1");
                r2=3;
                break;
            case R.id.rbtn02:
                // do operations specific to this selection
                myRef.child("Q2").setValue("2");
                r2=2;
                break;
            case R.id.rbtn03:
                // do operations specific to this selection
                myRef.child("Q2").setValue("3");
                r2=1;
                break;
            case R.id.rbtn04:
                // do operations specific to this selection
                myRef.child("Q2").setValue("4");
                r2=0;
                break;
        }

    }
    public void getRG3(){
       switch(rg3.getCheckedRadioButtonId()){
            case R.id.rbtn000:
               // do operations specific to this selection
                myRef.child("Q3").setValue("0");
              r3=4;
               break;
          case R.id.rbtn001:
                // do operations specific to this selection
               myRef.child("Q3").setValue("1");
               r3=3;
                break;
            case R.id.rbtn002:
                // do operations specific to this selection
                myRef.child("Q3").setValue("2");
                r3=2;
                break;
            case R.id.rbtn003:
                // do operations specific to this selection
                myRef.child("Q3").setValue("3");
                r3=1;

                break;
            case R.id.rbtn004:
                // do operations specific to this selection
                myRef.child("Q3").setValue("4");
                r3=0;
                break;
      }
    }
    public void getRG4(){
        switch(rg4.getCheckedRadioButtonId()){
            case R.id.rbtn0000:
                // do operations specific to this selection
                myRef.child("Q4").setValue("0");
                r4=0;
                break;
            case R.id.rbtn0001:
                // do operations specific to this selection
                myRef.child("Q4").setValue("1");
                r4=1;
                break;
            case R.id.rbtn0002:
                // do operations specific to this selection
                myRef.child("Q4").setValue("2");
                r4=2;
                break;
            case R.id.rbtn0003:
                // do operations specific to this selection
                myRef.child("Q4").setValue("3");
                r4=3;
                break;
            case R.id.rbtn0004:
                // do operations specific to this selection
                myRef.child("Q4").setValue("4");
                r4=4;
                break;
        }
    }

}