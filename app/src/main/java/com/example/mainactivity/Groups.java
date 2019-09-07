package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Groups extends AppCompatActivity {
    Spinner sp;
    Button b1;

    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        sp=(Spinner)findViewById(R.id.spinner2);
        b1=(Button)findViewById(R.id.button3);


b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
         pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("group", sp.getSelectedItem().toString());
        editor.commit();
        MainActivity.grp=pref.getString("group", "");

        if( MainActivity.grp!=null)
        {
            Intent i =new Intent(Groups.this,Aftersignup.class);
            startActivity(i);
            finish();
        }

    }
});


    }
}
