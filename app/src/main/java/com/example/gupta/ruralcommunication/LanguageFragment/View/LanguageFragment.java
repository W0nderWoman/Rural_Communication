package com.example.gupta.ruralcommunication.LanguageFragment.View;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gupta.ruralcommunication.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageFragment extends Fragment {

    SurfaceView mCameraSurfaceView;
    CameraSource mCameraSource;
    TextView mLangTextView;
    View mRoot;
    final int CAMERA_REQ_INT=1313;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CAMERA_REQ_INT:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA)!=PackageManager.PERMISSION_DENIED){
                        return;
                    }
                }
                try {
                    mCameraSource.start(mCameraSurfaceView.getHolder());
                }catch (IOException e){
                    e.printStackTrace();
                }
        }
    }
    Context mContext;
    public LanguageFragment(Context context) {
        // Required empty public constructor
        this.mContext=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRoot=inflater.inflate(R.layout.fragment_language, container, false);
        Toast.makeText(getContext(),"Open",Toast.LENGTH_SHORT).show();
        mCameraSurfaceView=mRoot.findViewById(R.id.language_surface_view);
        mLangTextView=mRoot.findViewById(R.id.language_text_view);
        TextRecognizer textRecognizer=new TextRecognizer.Builder(mContext).build();
        if(!textRecognizer.isOperational()){
            Toast.makeText(getContext(),"Not",Toast.LENGTH_SHORT).show();
            //Log.d("reach","reached");
        }else {
            Log.d("reach","reached");
            mCameraSource=new CameraSource.Builder(mContext,textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280,1024)
                    .setAutoFocusEnabled(true)
                    .setRequestedFps(15.0f)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .build();
            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if(items.size() != 0)
                    {
                        mLangTextView.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                for(int i =0;i<items.size();++i)
                                {
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());
                                    stringBuilder.append("\n");
                                }
                                mLangTextView.setText(stringBuilder.toString());
                            }
                        });
                    }
                }
            });
        }
        return mRoot;
    }

}
