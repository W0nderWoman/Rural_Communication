package com.example.gupta.ruralcommunication.ProblemFragment.View;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.gupta.ruralcommunication.ProblemFragment.Model.ProblemModelObject;
import com.example.gupta.ruralcommunication.ProblemFragment.Presenter.ProblemPresenter;
import com.example.gupta.ruralcommunication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProblemReportingFragment extends Fragment {

    RecyclerView mProblemRecyclerView;
    public ProblemReportingFragment() {
        // Required empty public constructor
    }
    View mRoot;
    LottieAnimationView mProblemLottieAnimationView;
    Button mReportProbButton;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRoot= inflater.inflate(R.layout.fragment_problem_reporting, container, false);
        mProblemLottieAnimationView=mRoot.findViewById(R.id.problem_animation);
        mProblemLottieAnimationView.playAnimation();
        mProblemRecyclerView=mRoot.findViewById(R.id.problem_list_view);
        mReportProbButton=mRoot.findViewById(R.id.report_problem_button);
        ProblemPresenter problemPresenter=new ProblemPresenter();
        problemPresenter.fetchProblems(getContext(),getActivity());
        mReportProbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1=inflater.inflate(R.layout.problem_list_view,null);
                BottomSheetDialog sheetDialog=new BottomSheetDialog(getContext());
                sheetDialog.setCanceledOnTouchOutside(true);
                Button reportButton=view1.findViewById(R.id.write_mail_button);
                reportButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent((Intent.ACTION_VIEW), Uri.parse("mailtto:"+"rural.heavean@rucomm.com"));
                        intent.putExtra(Intent.EXTRA_SUBJECT,"New Issue in Concerned Region");
                        startActivity(intent);
                    }
                });
            }
        });
        return mRoot;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void renderProblemsInView(ProblemModelObject problemModelObject){
        Log.d("Reached","reach");
        mProblemRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mProblemRecyclerView.setAdapter(new Adapter(getContext(),problemModelObject.problemObjects));
    }
}
