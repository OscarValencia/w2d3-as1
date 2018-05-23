package com.valencia.oscar.w2d3_as1;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = CreateFragment.class.getSimpleName()+"_TAG";
    EditText nameET,emailET,ageET;
    Button photoBtn, saveBtn, listBtn;
    TextView alertsTV;
    RecordsDB MyRecords;
    private displayListListenerFromCreateFragment displayListListener;

    public CreateFragment() {
        // Required empty public constructor
    }

    public interface displayListListenerFromCreateFragment{
        void listButtonClicked();
        void launchCamera();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =
         inflater.inflate(R.layout.fragment_create, container, false);
        MyRecords = new RecordsDB(view.getContext(), "MyRecords", null, 1);

        nameET = view.findViewById(R.id.nameET);
        emailET = view.findViewById(R.id.emailET);
        ageET = view.findViewById(R.id.ageET);
        photoBtn = view.findViewById(R.id.photoBtn);
        saveBtn = view.findViewById(R.id.saveBtn);
        listBtn = view.findViewById(R.id.listBtn);
        alertsTV = view.findViewById(R.id.alertsTV);

        photoBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            displayListListener = (displayListListenerFromCreateFragment) getActivity();
        }catch(ClassCastException  e){
            Log.d(TAG,"The host activity must implement 'displayListListenerFromCreateFragment' interface from 'CreateFragment' class");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photoBtn:
                displayListListener.launchCamera();
                break;
            case R.id.saveBtn:
                saveRecord();
                break;
            case R.id.listBtn:
                displayListListener.listButtonClicked();
                break;
        }
    }

    public void saveRecord(){

        SQLiteDatabase db = MyRecords.getWritableDatabase();
        String tmpPicturePath ="";

        if(nameET.getText().toString().length()>0 &&
                emailET.getText().toString().length()>0 &&
                ageET.getText().toString().length()>0){
            if(db!=null){
                String sqlInsert = "INSERT INTO "+MyRecords.getTABLE_NAME()+
                        "("+MyRecords.getCOLUMN_2()+","+MyRecords.getCOLUMN_3()+","+MyRecords.getCOLUMN_4()+","+MyRecords.getCOLUMN_5()+") " +
                        "VALUES('"+nameET.getText().toString()+
                        "','"+emailET.getText().toString()+
                        "',"+Integer.parseInt(ageET.getText().toString())+
                        ",'"+tmpPicturePath+"')";
                Log.d(TAG,sqlInsert);
                try{
                    db.execSQL(sqlInsert);
                    db.close();
                    nameET.setText("");
                    emailET.setText("");
                    ageET.setText("");

                }catch(Exception e){
                    Log.d(TAG,e.toString());
                }
                Log.d(TAG,"Record stored!");
                alertsTV.setText("Record stored!");
            }else{
                Log.d(TAG,"DB has not been opened");
            }
        }else{
            alertsTV.setText("Fill out all the fields.");
        }
    }


}
