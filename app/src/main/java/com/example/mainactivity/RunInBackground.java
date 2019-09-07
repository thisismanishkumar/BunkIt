package com.example.mainactivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RunInBackground extends AsyncTask {


    @Override
    protected Object doInBackground(Object[] objects) {

        Task<DocumentSnapshot> documentSnapshotTask = FirebaseFirestore.getInstance().
                collection((String) objects[0]).document((String) objects[1]).get();

        String obj=null;

        try {
            DocumentSnapshot documentSnapshot = Tasks.await(documentSnapshotTask);

            obj = new String();
            obj=(documentSnapshot.get("table").toString());

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
