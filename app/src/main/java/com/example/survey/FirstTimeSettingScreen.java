package com.example.survey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FirstTimeSettingScreen extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;
    TextView tname,tage,tprofession,temail;
    EditText name, age, profession, email;
    Button saveUser;
    profile p;
    FirebaseAuth mAuth;
    String uid , androidid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_setting_screen);
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = preferences.getString("FirstTimeInstall", "");

        if(FirstTime.equals("Yes")){
            Intent intent = new Intent(FirstTimeSettingScreen.this,MainActivity.class);
            startActivity(intent);
        }
        else{
            SharedPreferences.Editor editor= preferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();

        }

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    uid= user.getUid();

                }
                else {
                    Toast.makeText(FirstTimeSettingScreen.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        androidid = Settings.Secure.getString(getApplicationContext().getContentResolver(),
//                Settings.Secure.ANDROID_ID);

        tname = findViewById(R.id.txtname);
        tage = findViewById(R.id.txtage);
        temail = findViewById(R.id.txtemail);
        tprofession= findViewById(R.id.txtprof);


        name =findViewById(R.id.ed_name);
        email=findViewById(R.id.ed_email);
        profession = findViewById(R.id.ed_prof);
        age =findViewById(R.id.ed_age);

        saveUser = (Button) findViewById(R.id.btnsetprofile);
        p= new profile();
        db= FirebaseDatabase.getInstance();
        ref= db.getReference("Users");




        saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abc();
            }

        });

    }
    public void abc(){
        String n= name.getText().toString();
        p.setName(n);
        p.setAge(age.getText().toString());
        p.setEmail(email.getText().toString());
        p.setProfession(profession.getText().toString());


        ref=db.getReference().child(uid).child("ProfileData");
        ref.child("Name").setValue(p.getName());
        ref.child("Age").setValue(p.getAge());
        ref.child("Profession").setValue(p.getProfession());
        ref.child("Email").setValue(p.getEmail());
        //ref.child("Uid").setValue(uid);


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
