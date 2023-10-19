package com.example.survey;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class
MainActivity extends AppCompatActivity {
Button start;
Button setting;

private static final int REQUEST_CODE_LOCATION_PERMISSION =1;
Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start= findViewById(R.id.btnstart);
        setting= findViewById(R.id.btnSettings);

        ScreenTimeBroadcastReceiver
        screenTimeBroadcastReceiver = new ScreenTimeBroadcastReceiver();
        IntentFilter lockFilter = new IntentFilter();
        lockFilter.addAction(Intent.ACTION_SCREEN_ON);
        lockFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(screenTimeBroadcastReceiver, lockFilter);


        FirebaseMessaging.getInstance().subscribeToTopic("All")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "";
                        if (!task.isSuccessful()) {
                            msg = "";
                        }

                    }
                });


        start.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent( MainActivity.this,StatisticActivity2.class));

    }
});

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), setprofile.class);
                view.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResult){
        super.onRequestPermissionsResult(requestCode,permission,grantResult);
        if(requestCode== REQUEST_CODE_LOCATION_PERMISSION && grantResult.length>0){
            if(grantResult[0]== PackageManager.PERMISSION_GRANTED){
             //  startService();
            }
            else {
                Toast.makeText(this,"Location permission denied ",Toast.LENGTH_LONG).show();
            }
        }
    }


   }

