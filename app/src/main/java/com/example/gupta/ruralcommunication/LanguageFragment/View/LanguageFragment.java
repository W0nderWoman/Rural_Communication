package com.example.gupta.ruralcommunication.LanguageFragment.View;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
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

    public LanguageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRoot=inflater.inflate(R.layout.fragment_language, container, false);
        Toast.makeText(getContext(),"Open",Toast.LENGTH_SHORT).show();
        mCameraSurfaceView=mRoot.findViewById(R.id.language_surface_view);
        mLangTextView=mRoot.findViewById(R.id.language_text_view);
        TextRecognizer textRecognizer=new TextRecognizer.Builder(getContext()).build();
        if(textRecognizer.isOperational()){

        }else {
            mCameraSource=new CameraSource.Builder(getContext(),textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280,1024)
                    .setAutoFocusEnabled(true)
                    .build();
            mCameraSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    try {
                       if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_DENIED){
                           ActivityCompat.requestPermissions(getActivity(),
                                   new String[]{Manifest.permission.CAMERA},CAMERA_REQ_INT);
                           return;
                       }
                       mCameraSource.start(mCameraSurfaceView.getHolder());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    mCameraSource.stop();
                }
            });
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
