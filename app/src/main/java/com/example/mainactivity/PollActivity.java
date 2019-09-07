package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PollActivity extends AppCompatActivity {
    private static String TAG="123";
    private TextView tv,tv1;
    private int a,b;
    private ImageButton b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        tv=findViewById(R.id.bunkDetails);
        tv1=findViewById(R.id.status);
        b1=findViewById(R.id.imageButton);
        b2=findViewById(R.id.imageButton2);
        DocumentReference docref=FirebaseFirestore.getInstance().collection("polls").document(MainActivity.grp);
        docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String,Object> map=document.getData();
                        String s=map.get("details").toString(),s1=map.get("for").toString(),s2=map.get("against").toString();
                        tv.setText(s);
                        a=Integer.parseInt(s1);
                        b=Integer.parseInt(s2);
                        tv1.setText("Status: For: "+s1+"\tAgainst:"+s2);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }
    public void pressedFor(View v){
        FirebaseFirestore.getInstance().collection("polls").document(MainActivity.grp).update("for",a+1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully updated!");
                 finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        finish();
                    }
                });


    }
    public void pressedAgainst(View v){
        FirebaseFirestore.getInstance().collection("polls").document(MainActivity.grp).update("against",b+1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully updated!");
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        finish();
                    }
                });
    }
}
