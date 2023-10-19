package com.example.survey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StatisticActivity2 extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref, ref_re;
    RecyclerView recyclerView;
    MyAdapter adapter;
    Button ok;
    String uid;
    FirebaseRecyclerOptions<Locations> options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic2);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String and_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
               Settings.Secure.ANDROID_ID);
        setTitle("Earlier Moments");
        Calendar calendar1 = Calendar.getInstance();
        String date1 = DateFormat.getDateInstance(DateFormat.FULL).format(calendar1.getTime());
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(StatisticActivity2.this);
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        ok=findViewById(R.id.btnOk1);
        db = FirebaseDatabase.getInstance( );
        ref = db.getReference( ).child("Locations").child(and_id).child(date1);
       // Query query = db.getReference();
        options= new FirebaseRecyclerOptions.Builder<Locations>().setQuery(ref, Locations.class).build();
        adapter = new MyAdapter(options);
        recyclerView.setAdapter(adapter);

        ok.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent( StatisticActivity2.this, MainActivity.class ));
                SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
                String FirstTime1 = preferences.getString("FirstTimeInstall2", "");

                if(FirstTime1.equals("Yes")){
                    DatabaseReference reff=FirebaseDatabase.getInstance().getReference().child(uid).child("Days").child("SevenDay");
                    reff.addValueEventListener(new ValueEventListener( ) {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                SimpleDateFormat sdf1 = new SimpleDateFormat("EEE");
                                Date d1 = new Date();
                                String dayOfTheWeek1 = sdf1.format(d1);
                                String Day = snapshot.getValue().toString();
                                if(dayOfTheWeek1.equals(Day)){
                                    startActivity(new Intent( StatisticActivity2.this, Scroll2.class ));
                                }
                                else{
                                startActivity(new Intent( StatisticActivity2.this, Scroll1.class ));}

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else{
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("FirstTimeInstall2","Yes");
                    editor.apply();
                   // SimpleDateFormat sdf1 = new SimpleDateFormat("EEE");
                    //Date d1 = new Date();
                    //String dayOfTheWeek1 = sdf1.format(d1);
                    SimpleDateFormat dateFormat= new SimpleDateFormat("EEE");
                    Calendar currentCal = Calendar.getInstance();
                    String currentdate=dateFormat.format(currentCal.getTime());
                    currentCal.add(Calendar.DATE, 6);
                    String toDate=dateFormat.format(currentCal.getTime());
                    DatabaseReference ref1= FirebaseDatabase.getInstance().getReference().child(uid).child("Days").child("FirstDay");
                    ref1.setValue(currentdate);
                    DatabaseReference ref2= FirebaseDatabase.getInstance().getReference().child(uid).child("Days").child("SevenDay");
                    ref2.setValue(toDate);
                    Intent intent = new Intent(StatisticActivity2.this,Scroll1.class);
                    startActivity(intent);

                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}