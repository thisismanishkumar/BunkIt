package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button b1;
        EditText e1;
        b1=(Button)findViewById(R.id.login1);
        e1=(EditText)findViewById(R.id.password1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString()!=null)
                {
                    if(e1.getText().toString().equals("metasploithaxx"))
                    {
                        MainActivity.ttt=true;
                        Intent intent =new Intent(admin.this,Aftersignup.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(admin.this,"Wrong Password",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(admin.this,"Fill The Password!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
