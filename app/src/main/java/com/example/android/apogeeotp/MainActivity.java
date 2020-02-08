package com.example.android.apogeeotp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    Button btnGenerateOtp, btnCheckOtp;
    DatabaseReference rootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGenerateOtp = (Button)findViewById(R.id.generate_otp);
        //btnCheckOtp = (Button)findViewById(R.id.check_otp);


        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();

        btnGenerateOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                int i1 = r.nextInt(10000 - 1000) + 1000;
                TextView textView = (TextView) findViewById(R.id.diplay_otp);
                textView.setText(Integer.toString(i1));

                rootRef.child("CodingEvent").child("CoordOTP").setValue(Integer.toString(i1));

            }
        });

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


    }
}
