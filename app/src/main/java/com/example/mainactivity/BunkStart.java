package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class BunkStart extends AppCompatActivity {
     private Button b1,b2,b3;
    SharedPreferences pref;
     private  String TAG="1243";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_start);
        b1=findViewById(R.id.button5);
        b2=findViewById(R.id.button6);
        b3=findViewById(R.id.button7);
        if(MainActivity.ttt)b3.setVisibility(View.VISIBLE);

        DocumentReference docref= FirebaseFirestore.getInstance().collection("polls").document(MainActivity.grp);
        docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        b1.setEnabled(false);
                        b2.setEnabled(true);
                        b3.setEnabled(true);
                        b2.setEnabled(MainActivity.abc);



                    } else {
                        b1.setEnabled(true);
                        b2.setEnabled(false);
                        b3.setEnabled(false);
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    public void bunkInit(View v){
        Intent intent=new Intent(this,BunkInitActivity.class);
        startActivity(intent);
        finish();
    }
    public void poll(View v){
        Intent intent=new Intent(this,PollActivity.class);
        startActivity(intent);
        MainActivity.abc=false;
        finish();
    }
    public void delete(View v){
        FirebaseFirestore.getInstance().collection("polls").document(MainActivity.grp)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(BunkStart.this,"Delete successful",Toast.LENGTH_LONG).show();
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w(TAG, "Error deleting document", e);
                    }
                });
         finish();
    }
}
