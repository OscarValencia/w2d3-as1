package com.valencia.oscar.w2d3_as1;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    TextView listTV;
    RecordsDB MyRecords;

    public ListFragment() {
        // Required empty public constructor
    }

    public void displayList(){
        Cursor cursor = null;
        SQLiteDatabase db = MyRecords.getWritableDatabase();
        StringBuilder result = new StringBuilder();
        String sqlSelect = "SELECT * FROM "+MyRecords.getTABLE_NAME();
        cursor = db.rawQuery(sqlSelect,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            result.append("{name:'"+cursor.getString(cursor.getColumnIndex(MyRecords.getCOLUMN_2()))+
                    "',email:'"+cursor.getString(cursor.getColumnIndex(MyRecords.getCOLUMN_3()))+
                    "',age:"+cursor.getString(cursor.getColumnIndex(MyRecords.getCOLUMN_4()))+
                    "}\n");
            cursor.moveToNext();
        }

        listTV.setText(result);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        MyRecords = new RecordsDB(view.getContext(), "MyRecords", null, 1);
        listTV = view.findViewById(R.id.listTV);
        return view;
    }
}
