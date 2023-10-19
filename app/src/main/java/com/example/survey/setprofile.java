package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
public class setprofile extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;
    TextView tname,tage,tprofession,temail;
    EditText name, age, profession, email;
    Button saveUser;
    profile p;

    FirebaseAuth mAuth;
    String namval;
    String uid, and_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setprofile);
        setTitle("Profile Settings");
        mAuth = FirebaseAuth.getInstance();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

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
               namval = name.getText().toString();
                abc();
            }

        });


        and_id= Settings.Secure.getString(getApplicationContext().getContentResolver(),
        Settings.Secure.ANDROID_ID);


    }

    public void abc(){
        String n= name.getText().toString();
        p.setName(n);
        p.setAge(age.getText().toString());
        p.setEmail(email.getText().toString());
        p.setProfession(profession.getText().toString());


        ref=db.getReference("Users").child(uid);
        ref.child("Name").setValue(p.getName());
        ref.child("Age").setValue(p.getAge());
        ref.child("Profession").setValue(p.getProfession());
        ref.child("Email").setValue(p.getEmail());
       // ref.child("Uid").setValue(uid);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}