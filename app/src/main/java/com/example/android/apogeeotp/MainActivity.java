package com.example.android.apogeeotp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textViewDisplayOtp = (TextView) findViewById(R.id.diplay_otp);
        final EditText editTextEventName = (EditText) findViewById(R.id.event_name);
        final TextView textViewFinalMsg = (TextView) findViewById(R.id.final_msg);
        Button btnGenerateOtp = (Button) findViewById(R.id.generate_otp);

        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();

        btnGenerateOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String eventName = editTextEventName.getText().toString();

                Random r = new Random();
                int i1 = r.nextInt(10000 - 1000) + 1000;
                textViewDisplayOtp.setText(Integer.toString(i1));

                rootRef.child(eventName).child("CoordOTP").setValue(Integer.toString(i1));

                new CountDownTimer(60000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        textViewFinalMsg.setText(String.valueOf(millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        // Called after timer finishes
                        rootRef.child(eventName).child("CoordOTP").setValue("OTP Expired");
                        textViewFinalMsg.setText("OTP Expired");
                        textViewDisplayOtp.setText("Your OTP is:");
                        editTextEventName.setText("");
                        editTextEventName.setHint("Enter Event Name");
                    }
                }.start();
            }
        });
    }
}

//        new CountDownTimer(90000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                TextView textView1 = (TextView) findViewById(R.id.final_msg);
//                textView1.setText(String.valueOf(millisUntilFinished / 1000));
//            }
//
//            public void onFinish() {
//                // Called after timer finishes
//                rootRef.child("CodingEvent").child("CoordOTP").setValue("Code Expired");
//                TextView textView1 = (TextView) findViewById(R.id.final_msg);
//                textView1.setText("Code Expired");
//            }
//        }.start();


//        btnCheckOtp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText text = (EditText)findViewById(R.id.enter_otp);
//                final String valueEntered = text.getText().toString();
//
//                rootRef.child("CodingEvent").child("CoordOTP").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        String valueRecieved = dataSnapshot.getValue(String.class);
//
//                        if(valueEntered.equals(valueRecieved)){
//                            TextView textView1 = (TextView) findViewById(R.id.final_msg);
//                            textView1.setText("Attendance Marked Successfully");
//                            rootRef.child("CodingEvent").child("User").setValue("user_1");
//                        }
//                        else{
//                            TextView textView2 = (TextView) findViewById(R.id.final_msg);
//                            textView2.setText("Incorrect OTP");
//                            EditText text = (EditText)findViewById(R.id.enter_otp);
//                            text.setText("");
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        Toast.makeText(MainActivity.this, "Failed to read value.",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//
//
//            }
//        });

