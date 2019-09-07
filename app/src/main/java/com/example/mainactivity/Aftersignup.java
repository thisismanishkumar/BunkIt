package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Aftersignup extends AppCompatActivity implements LocationListener {
    Button b1, b2, log, atn;
    ImageButton img;
    static int counter;
    static long timer;
    public static String loc = null;
    private LocationManager locationManager;
    private String TAG = "123";
    private static boolean exit;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftersignup);
        final Context baseContext = this.getBaseContext();
        FirebaseApp.initializeApp(this);
        FacebookSdk.sdkInitialize(this);
        FirebaseMessaging.getInstance().subscribeToTopic(MainActivity.grp);
        FirebaseMessaging.getInstance().subscribeToTopic(MainActivity.grp + "Poll");
        counter = 0;
        timer = 0;
        FirebaseAuth auth = FirebaseAuth.getInstance();
        b1 = (Button) findViewById(R.id.chatbox);
        b2 = (Button) findViewById(R.id.timetable);

        log = (Button) findViewById(R.id.logout);

        img = (ImageButton) findViewById(R.id.imageButton);
        atn = (Button) findViewById(R.id.attendence);

        FirebaseFirestore.getInstance().collection("polls").document(MainActivity.grp).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        int minh = Integer.parseInt(document.getData().get("minHr").toString());
                        Calendar cal = Calendar.getInstance();
                        int hh = cal.get(Calendar.HOUR_OF_DAY);
                        int min = cal.get(Calendar.MINUTE);
                        if (hh == minh && min >= 7 && min <= 17) MainActivity.atn1 = true;
                        else MainActivity.atn1 = false;

                    }
                }
            }
        });
        atn.setEnabled(MainActivity.atn1);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        atn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(Aftersignup.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(Aftersignup.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Aftersignup.this, "Permission required to continue", Toast.LENGTH_LONG).show();
                    requestPermission();
                    return;
                } else {
                    Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                    onLocationChanged(location);
                    if (MainActivity.latitude >= 25.492039 && MainActivity.latitude <= 25.494970 && MainActivity.longitude >= 81.862007 && MainActivity.longitude <= 81.865734) {
                        loc = "will SPOIL the BUNK!!! stop him";

                    } else {
                        loc = "is Supporting THE BUNK!!!";
                    }
                    Intent intent = new Intent(Aftersignup.this, FirestoreChatActivity.class);
                    startActivity(intent);


                }


            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - timer > 200)
                    counter = 0;
                else
                    counter++;
                timer = System.currentTimeMillis();
                if (counter == 5) {
                    Toast.makeText(baseContext, "Admin login!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Aftersignup.this, admin.class);
                    startActivity(intent);
                }

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.grp.isEmpty()) {
                    Intent i = new Intent(Aftersignup.this, Groups.class);
                    startActivity(i);
                } else {

                    Intent i = new Intent(Aftersignup.this, FirestoreChatActivity.class);
                    startActivity(i);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Aftersignup.this, TimeTableActivity.class);
                startActivity(intent);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(Aftersignup.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Aftersignup.this, "You Have Signed Out", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });

               /* SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();

                editor.clear();
                editor.commit();
                MainActivity.grp=null;// commit changes*/
                Intent intent = new Intent(Aftersignup.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onLocationChanged(Location location) {
        MainActivity.longitude = location.getLongitude();
        MainActivity.latitude = location.getLatitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_COARSE_LOCATION}, 1);
    }

    public void initBunk(View v) {
        Intent intent = new Intent(this, BunkStart.class);
        startActivity(intent);
    }

    public void onResume() {
        super.onResume();

        FirebaseFirestore.getInstance().collection("polls").document(MainActivity.grp).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        int minh = Integer.parseInt(document.getData().get("minHr").toString());
                        Calendar cal = Calendar.getInstance();
                        int hh = cal.get(Calendar.HOUR_OF_DAY);
                        int min = cal.get(Calendar.MINUTE);
                        if (hh == minh && min >= 7 && min <= 17) MainActivity.atn1 = true;
                        else MainActivity.atn1 = false;

                    }
                }

            }
        });
    }

    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }
}
