package com.valencia.oscar.w2d3_as1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CreateFragment.displayListListenerFromCreateFragment{

    FragmentManager fragmentManager,fragmentManager2;
    ListFragment listFragment;
    static final int CAM_REQUEST = 6666;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.firstContainer,new CreateFragment())
                .commit();

        listFragment = new ListFragment();
        fragmentManager2 = getSupportFragmentManager();
        fragmentManager2.beginTransaction()
                .replace(R.id.secondContainer,listFragment)
                .commit();
    }

    @Override
    public void listButtonClicked() {
        listFragment.displayList();
    }

    @Override
    public void launchCamera() {
        requestCameraPermissions();
        Intent launchCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(launchCamera,CAM_REQUEST);
    }

    public void requestCameraPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.CAMERA}, 1);
            }
        }
    }
}
