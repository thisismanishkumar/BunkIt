package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.github.tlaabs.timetableview.Schedule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BunkInitActivity extends AppCompatActivity {
    private static Calendar d;
    private TextView tv;
    private String TAG="!@#";
    private Map<String,Object> tt;
    private TTExtract tableobj;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private  TextView textView;
    private Button button;
    private static int k;
    private static String s;
    private static int btncount=0;
    private static String str="";
    private int hr,min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_init);
        Calendar newCalendar = Calendar.getInstance();
        btncount=0;
        tt = new HashMap<>();
        tableobj=new TTExtract();
        listView = findViewById(R.id.schList);
        button=findViewById(R.id.bunkStart);
        tv = findViewById(R.id.dateSelect);
        textView=findViewById(R.id.confirmTxt);
        DatePickerDialog StartTime = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                d = newDate;
                init(d);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTime.show();
            }
        });

    }
    public void init(Calendar d) {
        int day = d.get(Calendar.DAY_OF_WEEK);
        tv.setText(new SimpleDateFormat("dd/MM/yyyy").format(d.getTime()));
         k = 0;
        switch (day) {
            case 2:
                k = 0;
                break;
            case 3:
                k = 1;
                break;
            case 4:
                k = 2;
                break;
            case 5:
                k = 3;
                break;
            case 6:
                k = 4;
                break;
        }
        Task<DocumentSnapshot> task=FirebaseFirestore.getInstance().collection("tables").document(MainActivity.grp).get();
                task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {


                            tt = documentSnapshot.getData();
                            s = tt.get("table").toString();
                        tableobj.load(s);
                        loadData(k);

                    }

                });

    }
    public void loadData(int k){

        ArrayList<Schedule> list=tableobj.getAllSchedulesInStickers();
        ArrayList<String> schedule=new ArrayList<>();
        min=1000;
        for(Schedule i:list){
            if(i.getDay()==k) {
                schedule.add("Class: " + i.getClassTitle() + " Start: " + i.getStartTime().getHour() + ":" + i.getStartTime().getMinute() + " End: " + i.getEndTime().getHour() + ":" + i.getEndTime().getMinute());
                int hr=i.getStartTime().getHour();
                if(hr<min)min=hr;
            }
        }

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, schedule);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        button.setVisibility(View.VISIBLE);
    }
    public void buttonClicked(View v){
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }

        String[] outputStrArr = new String[selectedItems.size()];

        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
        }
        if(btncount==0){
            btncount=1;
            button.setBackgroundColor(Color.RED);
            button.setTextColor(Color.WHITE);
            button.setText("Confirm Bunk!");
            textView.setText("");
            str="";
            for(String i:outputStrArr){
                str+=i+"\n";
            }
            textView.setText(str);
            textView.setTextColor(Color.RED);
        }
        else{
            Map<String,Object> poll=new HashMap<>();
            poll.put("details",str);
            poll.put("for",1);
            poll.put("against",0);
            poll.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
            poll.put("minHr",hr);
            FirebaseFirestore.getInstance().collection("polls").document(MainActivity.grp).set(poll).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d(TAG, "DocumentSnapshot successfully written!");
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
            finish();

        }

    }
}
