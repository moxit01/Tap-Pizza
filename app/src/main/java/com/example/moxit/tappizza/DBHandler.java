package com.example.moxit.tappizza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper {




    public DBHandler(Context context) {
        super(context,"DemoDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase SB) {

        SB.execSQL("Create Table UserData(UID int,UFName TEXT,UMName TEXT,ULName TEXT,UContactNo TEXT,UEmailId TEXT,UPassword TEXT)");
    }

    public int InsertUserData(ContentValues CV)
    {
        SQLiteDatabase SB=this.getWritableDatabase();
        SB.insert("UserData",null,CV);
        SB.close();
        return 1;
    }

    //to delete data in database
    public int DeleteUserData(String UEmailId)
    {
        SQLiteDatabase SB=this.getWritableDatabase();
        SB.delete("UserData","UEmailId='"+UEmailId+"'",null);
        SB.close();
        return 1;
    }

    public int UpdateUserData(ContentValues CV,String UEmailId)
    {
        SQLiteDatabase SB=this.getWritableDatabase();
        SB.update("UserData",CV,"UEmailId'"+UEmailId+"'",null);
        SB.close();
        return 1;
    }

    //to select data record from database table
    public ArrayList<HashMap<String,String>>
    UserDataReadFunction(String ReadDataStr)
    {

        //to read data from database
        SQLiteDatabase SB=this.getReadableDatabase();
        Cursor CS=SB.rawQuery(ReadDataStr,null);
        ArrayList<HashMap<String,String>> ArrUserData=new ArrayList<>();
        CS.moveToFirst();
        for(int i=0;i<CS.getCount();i++)
        {
            HashMap<String,String> HM =new HashMap<>();
            HM.put("UFName",CS.getString(CS.getColumnIndex("UFName")));
            HM.put("UMName",CS.getString(CS.getColumnIndex("UMName")));
            HM.put("ULName",CS.getString(CS.getColumnIndex("ULName")));
            HM.put("UContactNo",CS.getString(CS.getColumnIndex("UContactNo")));
            HM.put("UEmailId",CS.getString(CS.getColumnIndex("UEmailId")));
            HM.put("UPassword",CS.getString(CS.getColumnIndex("UPassword")));
            //add in the arraylist
            ArrUserData.add(HM);
            CS.moveToNext();
        }
        return ArrUserData;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}


