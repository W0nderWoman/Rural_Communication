package com.example.gupta.ruralcommunication.ProblemFragment.Presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.gupta.ruralcommunication.ProblemFragment.Model.ProblemModelObject;
import com.example.gupta.ruralcommunication.ProblemFragment.Model.ProblemObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Vasudev on 3/8/2018.
 */

public class ProblemPresenter implements ProblemPresenterCalls {
    @Override
    public void fetchProblems(Context context, Activity activity) {
        Log.d("Reached","reach");
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getReference("Problems");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<Object, Object> map = (HashMap<Object, Object>) dataSnapshot.getValue();
                ArrayList<ProblemObject> problems = new ArrayList<>();
                //Ar<Object> list= (List<Object>) map.values( ).toArray();
                Object[] list = map.values().toArray();
                for (int i = 0; i < map.values().toArray().length; i++) {
                    HashMap<String, String> map1 = (HashMap<String, String>) list[i];
                    problems.add(renderProblemsAsObject(map1));
                    Log.d("Reached", "reach");
                }
                Log.d("Reached", "reach");
                EventBus.getDefault().post(new ProblemModelObject(problems));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void repotProblems(Context context, Activity activity) {
//        final FirebaseDatabase database=FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference=database.getReference("Problems");
//        databaseReference.child("Problem7").child("").setValue("")
    }

    private ProblemObject renderProblemsAsObject(HashMap<String,String> map ){
        return new ProblemObject(map.get("Title"),map.get("Status"),map.get("Description"),map.get("Date"));
    }
}
