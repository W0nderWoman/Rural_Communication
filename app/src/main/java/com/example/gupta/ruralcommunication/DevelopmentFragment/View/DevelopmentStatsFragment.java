package com.example.gupta.ruralcommunication.DevelopmentFragment.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.gupta.ruralcommunication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DevelopmentStatsFragment extends Fragment {


    public DevelopmentStatsFragment() {
        // Required empty public constructor
    }
    LottieAnimationView mInfrastructureLottieAnimationView;
    LottieAnimationView mFinanceLottieAnimationView;
    LottieAnimationView mTransportLottieAnimationView;

    View mRoot;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRoot= inflater.inflate(R.layout.fragment_developemt_stats, container, false);
        mInfrastructureLottieAnimationView=mRoot.findViewById(R.id.infrastructure_animation);
        mInfrastructureLottieAnimationView.playAnimation();
        mFinanceLottieAnimationView=mRoot.findViewById(R.id.finance_animation);
        mFinanceLottieAnimationView.playAnimation();
        mTransportLottieAnimationView=mRoot.findViewById(R.id.transport_animation);
        mTransportLottieAnimationView.playAnimation();
        return  mRoot;
    }

}
