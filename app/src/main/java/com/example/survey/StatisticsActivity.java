package com.example.survey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class StatisticsActivity extends AppCompatActivity {
    ListView listView;
    Button ok;
    Calendar calendar;

    public ArrayList<String> listaddress, liststeps, listusage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        listView=findViewById(R.id.listView);
        ok=findViewById(R.id.btnOk);
     //   cancel=findViewById(R.id.btnCancel);
        listaddress=new ArrayList<>();
        liststeps=new ArrayList<>();
        listusage=new ArrayList<>();
        String  android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Calendar calendar1 = Calendar.getInstance();
        String date1 = DateFormat.getDateInstance(DateFormat.FULL).format(calendar1.getTime());
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Locations").child(android_id).child(date1);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String addr = ds.child("address").getValue().toString().trim();
                        Log.d( "address", addr);
                        String stepcount = ds.child("steps").getValue().toString().trim();
                        String musage = ds.child("mobile_usage").getValue().toString().trim();
                        listaddress.clear();
                        liststeps.clear();
                        listusage.clear();
                        listaddress.add(addr);
                        liststeps.add(stepcount);
                        listusage.add(musage);
                        MyEmpscrAdapter adapter;
                        adapter = new MyEmpscrAdapter(StatisticsActivity.this, listaddress, liststeps, listusage);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
                String FirstTime1 = preferences.getString("FirstTimeInstall1", "");

                if(FirstTime1.equals("Yes")){
                    DatabaseReference reff=FirebaseDatabase.getInstance().getReference().child(android_id).child("Days").child("FirstDay");
                    reff.addValueEventListener(new ValueEventListener( ) {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                SimpleDateFormat sdf1 = new SimpleDateFormat("EEE");
                                Date d1 = new Date();
                                String dayOfTheWeek1 = sdf1.format(d1);
                                String Day = snapshot.getValue().toString();
                                if(dayOfTheWeek1.equals(Day)){
                                    Intent intent = new Intent(StatisticsActivity.this,Scroll2.class);
                                    startActivity(intent);
                                }
                                else {
                                    Intent intent = new Intent(StatisticsActivity.this,Scroll1.class);
                                    startActivity(intent);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else{
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("FirstTimeInstall1","Yes");
                    editor.apply();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("EEE");
                    Date d1 = new Date();
                    String dayOfTheWeek1 = sdf1.format(d1);
                    DatabaseReference ref1= FirebaseDatabase.getInstance().getReference().child(android_id).child("Days").child("FirstDay");
                    ref1.setValue(dayOfTheWeek1);
                    Intent intent = new Intent(StatisticsActivity.this,Scroll1.class);
                    startActivity(intent);

                }

//                if(day==Calendar.SUNDAY){
//                    startActivity(new Intent( StatisticsActivity.this,Scroll2.class ));
//
//
//                }
//                else {
//                    startActivity(new Intent( StatisticsActivity.this,Scroll1.class ));
//
//                }
            }
        });

    }
}