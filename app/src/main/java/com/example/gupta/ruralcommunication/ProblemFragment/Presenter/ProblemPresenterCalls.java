package com.example.gupta.ruralcommunication.ProblemFragment.Presenter;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Vasudev on 3/8/2018.
 */

public interface ProblemPresenterCalls {
    void fetchProblems(Context context, Activity activity);
    void repotProblems(Context context,Activity activity);

}
